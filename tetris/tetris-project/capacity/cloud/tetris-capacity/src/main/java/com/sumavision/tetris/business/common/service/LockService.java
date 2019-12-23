package com.sumavision.tetris.business.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.business.common.dao.TaskInputDAO;
import com.sumavision.tetris.business.common.dao.TaskOutputDAO;
import com.sumavision.tetris.business.common.enumeration.BusinessType;
import com.sumavision.tetris.business.common.po.TaskInputPO;
import com.sumavision.tetris.business.common.po.TaskOutputPO;
import com.sumavision.tetris.business.yjgb.vo.StreamTranscodingVO;
import com.sumavision.tetris.capacity.bo.input.InputBO;
import com.sumavision.tetris.capacity.bo.output.OutputBO;
import com.sumavision.tetris.capacity.bo.request.AllRequest;
import com.sumavision.tetris.capacity.bo.response.AllResponse;
import com.sumavision.tetris.capacity.bo.task.TaskBO;
import com.sumavision.tetris.capacity.config.CapacityProps;
import com.sumavision.tetris.capacity.service.CapacityService;
import com.sumavision.tetris.capacity.service.ResponseService;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;

import javassist.bytecode.analysis.ControlFlow.Catcher;
import redis.clients.jedis.Jedis;

@Transactional(rollbackFor = Exception.class)
@Service
public class LockService {
	
	@Autowired
	private TaskInputDAO taskInputDao;
	
	@Autowired
	private TaskOutputDAO taskOutputDao;
	
	@Autowired
	private CapacityService capacityService;
	
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private CapacityProps capacityProps;

	public void test(int ii){
		TaskInputPO input1 = taskInputDao.findByTaskUuidAndType("123", BusinessType.TEST);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		input1.setCount(ii);
		try{
			//System.out.println("-------"+ii+"======"+taskInputDao.save(input1).getCount());
			Thread.sleep(2000);
		}catch(ObjectOptimisticLockingFailureException e){
			System.out.println("---失败----"+ii);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void test1(String uniq) throws Exception{
		TaskInputPO input = taskInputDao.selectByUniq(uniq);
//		TaskInputPO input = taskInputDao.selectByInput(uniq);
		if(input == null){
			System.out.println("insert");
			input = new TaskInputPO();
			input.setUpdateTime(new Date());
			input.setUniq(uniq);
			input.setType(BusinessType.TEST);
			taskInputDao.save(input);
			
			Thread.sleep(10000);
		}else {
			System.out.println("update");
			input.setUpdateTime(new Date());
			input.setUniq(uniq);
			input.setType(BusinessType.TEST);
			taskInputDao.save(input);
			
//			System.out.println("delete");
//			taskInputDao.delete(input);
			
			Thread.sleep(10000);
		}
	}
	
	/**
	 * 应急广播业务调用<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月27日 下午12:39:00
	 * @param 详细参数见save
	 */
	@Deprecated
	public void saveYjgb(
			String taskUuid, 
			String uniq, 
			InputBO inputBO, 
			List<TaskBO> taskBOs, 
			List<OutputBO> outputBOs, 
			boolean isRecord,
			String recordAddress,
			String recordCallBackUrl,
			String mediaType) throws Exception{
		
		save(taskUuid, uniq, inputBO, taskBOs, outputBOs, BusinessType.YJGB, isRecord, recordAddress, recordCallBackUrl, mediaType);
		
	}
	
	/**
	 * 直播业务调用 <br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月27日 下午12:39:00
	 * @param 详细参数见save
	 */
	@Deprecated
	public void saveLive(
			String taskUuid, 
			String uniq,
			InputBO inputBO, 
			List<TaskBO> taskBOs, 
			List<OutputBO> outputBOs) throws Exception{
		
		save(taskUuid, uniq, inputBO, taskBOs, outputBOs, BusinessType.LIVE, false, null, null, null);
		
	}
	
	/**
	 * 添加任务流程 -- 一个输入，多个任务，多个输出，
	 *             输入计数+1（并发），
	 *             输出直接存储（不管并发）
	 *             说明：联合unique校验insert（ip和port关联）； 数据行锁（乐观锁version）校验update<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月27日 下午12:32:15
	 * @param String taskUuid 任务流程标识
	 * @param String uniq uniq
	 * @param InputBO inputBO 输入协议
	 * @param List<TaskBO> taskBOs 任务协议
	 * @param List<OutputBO> outputBOs 输出协议
	 * @param BusinessType businessType 业务类型
	 * @param boolean isRecord 是否录制
	 * @param String recordAddress 录制路径
	 * @param String recordCallBackUrl 录制回调地址
	 * @param String mediaType 媒体类型（video，audio）
	 */
	@Deprecated
	public void save(
			String taskUuid, 
			String uniq, 
			InputBO inputBO, 
			List<TaskBO> taskBOs, 
			List<OutputBO> outputBOs, 
			BusinessType businessType,
			boolean isRecord,
			String recordAddress,
			String recordCallBackUrl,
			String mediaType) throws Exception{
		
		TaskInputPO input = taskInputDao.findByUniq(uniq);
		
		if(input == null){
			
			List<String> inputIds = new ArrayList<String>();
			List<String> taskIds = new ArrayList<String>();
			List<String> outputIds = new ArrayList<String>();
			
			AllRequest allRequest = new AllRequest();
			
			try {
				input = new TaskInputPO();
				input.setUpdateTime(new Date());
				input.setUniq(uniq);
				input.setTaskUuid(taskUuid);
				input.setInput(JSON.toJSONString(inputBO));
				input.setType(businessType);
				taskInputDao.save(input);
				
				TaskOutputPO output = new TaskOutputPO();
				output.setInputId(input.getId());
				output.setMediaType(mediaType);
				output.setOutput(JSON.toJSONString(outputBOs));
				output.setRecord(isRecord);
				output.setRecordAddress(recordAddress);
				output.setTask(JSON.toJSONString(taskBOs));
				output.setTaskUuid(taskUuid);
				output.setType(businessType);
				output.setUpdateTime(new Date());
				
				taskOutputDao.save(output);

				allRequest.setInput_array(new ArrayListWrapper<InputBO>().add(inputBO).getList());
				allRequest.setTask_array(new ArrayListWrapper<TaskBO>().addAll(taskBOs).getList());
				allRequest.setOutput_array(new ArrayListWrapper<OutputBO>().addAll(outputBOs).getList());
				
				AllResponse allResponse = capacityService.createAllAddMsgId(allRequest, capacityProps.getIp(), capacityProps.getPort());
				
				responseService.allResponseProcess(allResponse);
			
			} catch (ConstraintViolationException e) {
				
				//数据已存在（ip，port校验）
				System.out.println("校验输入已存在");
				Thread.sleep(300);
				save(taskUuid, uniq, inputBO, taskBOs, outputBOs, businessType, isRecord, recordAddress, recordCallBackUrl, mediaType);
			
			} catch (BaseException e){
				
				capacityService.deleteAllAddMsgId(allRequest, capacityProps.getIp(), capacityProps.getPort());

			} catch (Exception e) {
				
				if(!(e instanceof ConstraintViolationException)){
					throw e;
				}
				
			}
			
		}else{
			
			List<String> inputIds = new ArrayList<String>();
			List<String> taskIds = new ArrayList<String>();
			List<String> outputIds = new ArrayList<String>();
			
			AllRequest allRequest = new AllRequest();
			try {
				if(input.getCount().equals(0)){
					input.setInput(JSON.toJSONString(inputBO));
					input.setTaskUuid(taskUuid);
					input.setType(businessType);
				}
				input.setUpdateTime(new Date());
				input.setCount(input.getCount() + 1);
				taskInputDao.save(input);
				
				TaskOutputPO output = new TaskOutputPO();
				output.setInputId(input.getId());
				output.setMediaType(mediaType);
				output.setOutput(JSON.toJSONString(outputBOs));
				output.setRecord(isRecord);
				output.setRecordAddress(recordAddress);
				output.setTask(JSON.toJSONString(taskBOs));
				output.setTaskUuid(taskUuid);
				output.setType(businessType);
				output.setUpdateTime(new Date());
				
				taskOutputDao.save(output);
				
				if(input.getCount().equals(0)){
					
					allRequest.setInput_array(new ArrayListWrapper<InputBO>().add(inputBO).getList());

				}
				
				allRequest.setTask_array(new ArrayListWrapper<TaskBO>().addAll(taskBOs).getList());
				allRequest.setOutput_array(new ArrayListWrapper<OutputBO>().addAll(outputBOs).getList());
				
				AllResponse allResponse = capacityService.createAllAddMsgId(allRequest, capacityProps.getIp(), capacityProps.getPort());
				
				responseService.allResponseProcess(allResponse);
							
			} catch (ObjectOptimisticLockingFailureException e) {
				
				// 版本不对，version校验
				System.out.println("save校验version版本不对");
				Thread.sleep(300);
				save(taskUuid, uniq, inputBO, taskBOs, outputBOs, businessType, isRecord, recordAddress, recordCallBackUrl, mediaType);
				
			} catch (BaseException e){
				
				capacityService.deleteAllAddMsgId(allRequest, capacityProps.getIp(), capacityProps.getPort());

			} catch (Exception e) {
				
				if(!(e instanceof ObjectOptimisticLockingFailureException)){
					throw e;
				}
			}
		}
		
	}
	
	/**
	 * 删除任务流程 -- 输入计数减 一（并发）
	 * 			        输出返回，上层删除（不管并发）
	 * 			   TODO： 数据没有清除，之后起线程清除<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月27日 下午12:28:35
	 * @param String taskUuid 任务流程标识
	 * @return TaskOutputPO 任务输出
	 */
	@Deprecated
	public TaskOutputPO delete(String taskUuid) throws Exception {
		
		TaskOutputPO output = taskOutputDao.findByTaskUuidAndType(taskUuid, null);
		
		if(output != null){
			
			TaskInputPO input = taskInputDao.findOne(output.getInputId());
			
			if(input != null){
				
				try {

					input.setUpdateTime(new Date());
					input.setCount(input.getCount() - 1);
					taskInputDao.save(input);
					
					AllRequest allRequest = new AllRequest();
					
					List<OutputBO> outputs = JSONObject.parseArray(output.getOutput(), OutputBO.class);
					List<TaskBO> tasks = JSONObject.parseArray(output.getTask(), TaskBO.class);
					InputBO inputBO = JSONObject.parseObject(input.getInput(), InputBO.class);
					
					if(input.getCount().equals(0) && input.getInput() != null){
						allRequest.setInput_array(new ArrayListWrapper<InputBO>().add(inputBO).getList());
					}
					if(tasks != null){
						allRequest.setTask_array(new ArrayListWrapper<TaskBO>().addAll(tasks).getList());
					}
					if(outputs != null){
						allRequest.setOutput_array(new ArrayListWrapper<OutputBO>().addAll(outputs).getList());
					}
				
					capacityService.deleteAllAddMsgId(allRequest, capacityProps.getIp(), capacityProps.getPort());
					
					output.setOutput(null);
					output.setTask(null);
					
					taskOutputDao.save(output);
					
				} catch (ObjectOptimisticLockingFailureException e) {
					
					// 版本不对，version校验
					System.out.println("delete校验version版本不对");
					Thread.sleep(300);
					output = delete(taskUuid);
				}
			}
			
		}
		
		return output;
	}
	
}
