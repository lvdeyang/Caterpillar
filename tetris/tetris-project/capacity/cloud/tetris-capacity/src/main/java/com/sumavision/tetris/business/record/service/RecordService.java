package com.sumavision.tetris.business.record.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import com.sumavision.tetris.business.record.vo.RecordVO;
import com.sumavision.tetris.business.record.vo.SourceParamVO;
import com.sumavision.tetris.capacity.bo.input.CommonTsBO;
import com.sumavision.tetris.capacity.bo.input.InputBO;
import com.sumavision.tetris.capacity.bo.input.ProgramAudioBO;
import com.sumavision.tetris.capacity.bo.input.ProgramBO;
import com.sumavision.tetris.capacity.bo.input.ProgramVideoBO;
import com.sumavision.tetris.capacity.bo.input.SourceUrlBO;
import com.sumavision.tetris.capacity.bo.output.BaseMediaBO;
import com.sumavision.tetris.capacity.bo.output.OutputBO;
import com.sumavision.tetris.capacity.bo.output.OutputHlsRecordBO;
import com.sumavision.tetris.capacity.bo.request.AllRequest;
import com.sumavision.tetris.capacity.bo.response.AllResponse;
import com.sumavision.tetris.capacity.bo.task.EncodeBO;
import com.sumavision.tetris.capacity.bo.task.TaskBO;
import com.sumavision.tetris.capacity.bo.task.TaskSourceBO;
import com.sumavision.tetris.capacity.config.CapacityProps;
import com.sumavision.tetris.capacity.service.CapacityService;
import com.sumavision.tetris.capacity.service.ResponseService;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class RecordService {
	
	@Autowired
	private TaskInputDAO taskInputDao;
	
	@Autowired
	private CapacityService capacityService;
	
	@Autowired
	private TaskOutputDAO taskOutputDao;
	
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private CapacityProps capacityProps;
	
	/**
	 * 创建收录<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月3日 上午10:09:20
	 * @param RecordVO record 收录信息
	 * @return String 收录id
	 */
	public String createRecord(RecordVO record) throws Exception{
		
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		
		String url = record.getSourceParam().getUrl();
		
		save(uuid, url, record, BusinessType.RECORD);
		
		return uuid;
	}

	/**
	 * 删除收录<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月3日 上午10:27:18
	 * @param String id 收录标识
	 */
	public void deleteRecord(String id) throws Exception{
		
		TaskOutputPO output = delete(id);
		
		taskOutputDao.delete(output);
	}
	
	/**
	 * 添加任务流程 -- 一个输入，多个任务，多个输出，
	 *             输入计数+1（并发），
	 *             输出直接存储（不管并发）
	 *             说明：联合unique校验insert（url）； 数据行锁（乐观锁version）校验update<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月3日 上午10:00:15
	 * @param String taskUuid 任务标识
	 * @param String uniq 校验标识
	 * @param RecordVO record 收录信息
	 * @param BusinessType businessType 业务类型
	 */
	public void save(
			String taskUuid,
			String uniq,
			RecordVO record,
			BusinessType businessType) throws Exception{
		
		TaskInputPO input = taskInputDao.findByUniq(uniq);
		
		if(input == null){
			
			AllRequest allRequest = new AllRequest();
			InputBO inputBO = new InputBO();
			List<TaskBO> taskBOs = new ArrayList<TaskBO>();
 			OutputBO outputBO = new OutputBO();
			try {
				
				inputBO = record2Input(taskUuid, record);
				
				taskBOs = record2Tasks(taskUuid, inputBO);
				
				outputBO = record2OutputBO(taskUuid, taskBOs, record);
				
				input = new TaskInputPO();
				input.setUpdateTime(new Date());
				input.setUniq(uniq);
				input.setTaskUuid(taskUuid);
				input.setInput(JSON.toJSONString(inputBO));
				input.setType(businessType);
				taskInputDao.save(input);
				
				TaskOutputPO output = new TaskOutputPO();
				output.setInputId(input.getId());
				output.setOutput(JSON.toJSONString(outputBO));
				output.setTask(JSON.toJSONString(taskBOs));
				output.setTaskUuid(taskUuid);
				output.setType(businessType);
				output.setUpdateTime(new Date());
				
				taskOutputDao.save(output);

				allRequest.setInput_array(new ArrayListWrapper<InputBO>().add(inputBO).getList());
				allRequest.setTask_array(new ArrayListWrapper<TaskBO>().addAll(taskBOs).getList());
				allRequest.setOutput_array(new ArrayListWrapper<OutputBO>().add(outputBO).getList());
				
				AllResponse allResponse = capacityService.createAllAddMsgId(allRequest, capacityProps.getIp(), capacityProps.getPort());
				
				responseService.allResponseProcess(allResponse);
			
			} catch (ConstraintViolationException e) {
				
				//数据已存在（ip，port校验）
				System.out.println("校验输入已存在");
				Thread.sleep(300);
				save(taskUuid, uniq, record, businessType);
				
			} catch (BaseException e){
				
				capacityService.deleteAllAddMsgId(allRequest, capacityProps.getIp(), capacityProps.getPort());

			} catch (Exception e) {
				
				if(!(e instanceof ConstraintViolationException)){
					throw e;
				}
				
			}
			
		}else{
			
			AllRequest allRequest = new AllRequest();
			InputBO inputBO = new InputBO();
			List<TaskBO> taskBOs = new ArrayList<TaskBO>();
 			OutputBO outputBO = new OutputBO();
			try {
				
				if(input.getCount().equals(0)){
					//转输入
					inputBO = record2Input(taskUuid, record);
				}else{
					inputBO = JSONObject.parseObject(input.getInput(), InputBO.class);
				}
				
				taskBOs = record2Tasks(taskUuid, inputBO);
				
				outputBO = record2OutputBO(taskUuid, taskBOs, record);
				
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
				output.setOutput(JSON.toJSONString(outputBO));
				output.setTask(JSON.toJSONString(taskBOs));
				output.setTaskUuid(taskUuid);
				output.setType(businessType);
				output.setUpdateTime(new Date());
				
				taskOutputDao.save(output);
				
				if(input.getCount().equals(0)){
					
					allRequest.setInput_array(new ArrayListWrapper<InputBO>().add(inputBO).getList());

				}
				
				allRequest.setTask_array(new ArrayListWrapper<TaskBO>().addAll(taskBOs).getList());
				allRequest.setOutput_array(new ArrayListWrapper<OutputBO>().add(outputBO).getList());
				
				AllResponse allResponse = capacityService.createAllAddMsgId(allRequest, capacityProps.getIp(), capacityProps.getPort());
				
				responseService.allResponseProcess(allResponse);
							
			} catch (ObjectOptimisticLockingFailureException e) {
				
				// 版本不对，version校验
				System.out.println("save校验version版本不对");
				Thread.sleep(300);
				save(taskUuid, uniq, record, businessType);
				
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
	 * 			        数据没有清除，起线程清除<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月3日 上午9:28:35
	 * @param String taskUuid 任务流程标识
	 * @return TaskOutputPO 任务输出
	 */
	public TaskOutputPO delete(String taskUuid) throws Exception {
		
		TaskOutputPO output = taskOutputDao.findByTaskUuidAndType(taskUuid, BusinessType.RECORD);
		
		if(output != null){
			
			TaskInputPO input = taskInputDao.findOne(output.getInputId());
			
			if(input != null){
				
				try {

					input.setUpdateTime(new Date());
					if(input.getCount() >= 1){
						input.setCount(input.getCount() - 1);
					}
					taskInputDao.save(input);
					
					AllRequest allRequest = new AllRequest();
					
					OutputBO outputBO = JSONObject.parseObject(output.getOutput(), OutputBO.class);
					List<TaskBO> tasks = JSONObject.parseArray(output.getTask(), TaskBO.class);
					InputBO inputBO = JSONObject.parseObject(input.getInput(), InputBO.class);
					
					if(input.getCount().equals(0) && input.getInput() != null){
						allRequest.setInput_array(new ArrayListWrapper<InputBO>().add(inputBO).getList());
					}
					if(tasks != null){
						allRequest.setTask_array(new ArrayListWrapper<TaskBO>().addAll(tasks).getList());
					}
					if(outputBO != null){
						allRequest.setOutput_array(new ArrayListWrapper<OutputBO>().add(outputBO).getList());
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
	
	/**
	 * 收录信息转input<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月2日 下午3:24:03
	 * @param String id 标识
	 * @param RecordVO record 收录信息
	 * @return InputBO 
	 */
	public InputBO record2Input(String id, RecordVO record) throws Exception{
		
		String inputId = new StringBufferWrapper().append("input-")
												  .append(id)
												  .toString();
		
		String type = record.getType();
		SourceParamVO source = record.getSourceParam();
		String sourceUrl = source.getUrl();
		
		InputBO inputBO = new InputBO();
		
		if(type.equals("udp")){
			
			if(!sourceUrl.startsWith("udp")){
				throw new BaseException(StatusCode.FORBIDDEN, "源地址不是udp！");
			}
			
			String sourceIp = sourceUrl.split("@")[1].split(":")[0];
			
			int sourcePort = Integer.valueOf(sourceUrl.split("@")[1].split(":")[1]).intValue();
			
			CommonTsBO udp_ts = new CommonTsBO().setSource_ip(sourceIp)
												.setSource_port(sourcePort);
			
			inputBO.setUdp_ts(udp_ts);
		}
		
		if(type.equals("rtp")){
			
			if(!sourceUrl.startsWith("rtp")){
				throw new BaseException(StatusCode.FORBIDDEN, "源地址不是rtp！");
			}
			
			String sourceIp = sourceUrl.split("rtp://")[1].split(":")[0];
			
			int sourcePort = Integer.valueOf(sourceUrl.split("rtp://")[1].split(":")[1]).intValue();
			
			CommonTsBO rtp_ts = new CommonTsBO().setSource_ip(sourceIp)
												.setSource_port(sourcePort);
			
			inputBO.setRtp_ts(rtp_ts);
		}
		
		if(type.equals("http")){
			
			SourceUrlBO http_ts = new SourceUrlBO().setUrl(sourceUrl);
			inputBO.setHttp_ts(http_ts);
		}
		
		if(type.equals("hls")){
			
			SourceUrlBO hls = new SourceUrlBO().setUrl(sourceUrl);
			inputBO.setHls(hls);
		}
		
		if(type.equals("rtsp")){
			
			SourceUrlBO rtsp = new SourceUrlBO().setUrl(sourceUrl);
			inputBO.setRtsp(rtsp);
		}
		
		if(type.equals("rtmp")){
			
			SourceUrlBO rtmp = new SourceUrlBO().setUrl(sourceUrl);
			inputBO.setRtmp(rtmp);
		}
		
		inputBO.setId(inputId)
			   .setMedia_type_once_map(new JSONObject())
			   .setProgram_array(new ArrayList<ProgramBO>());
	
		ProgramBO program = new ProgramBO().setProgram_number(1)
										   .setVideo_array(new ArrayList<ProgramVideoBO>())
										   .setAudio_array(new ArrayList<ProgramAudioBO>());
		
		ProgramVideoBO video = new ProgramVideoBO().setPid(2);
		ProgramAudioBO audio = new ProgramAudioBO().setPid(1);
		
		program.getVideo_array().add(video);
		program.getAudio_array().add(audio);
		
		inputBO.getProgram_array().add(program);
		
		return inputBO;
	}
	
	/**
	 * 收录信息转taskBO<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月2日 下午4:46:14
	 * @param String id 标识
	 * @param InputBO input
	 * @return List<TaskBO> 
	 */
	public List<TaskBO> record2Tasks(String id, InputBO input) throws Exception{
		
		String videoTaskId = new StringBufferWrapper().append("task-video-")
													  .append(id)
													  .toString();
		
		String audioTaskId = new StringBufferWrapper().append("task-audio-")
													  .append(id)
													  .toString();
		
		String encodeVideoId = new StringBufferWrapper().append("encode-video-")
													    .append(id)
													    .toString();
		
		String encodeAudioId = new StringBufferWrapper().append("encode-audio-")
													    .append(id)
													    .toString();
		
		List<TaskBO> tasks = new ArrayList<TaskBO>();
		
		//视频
		TaskSourceBO videoSource = new TaskSourceBO().setInput_id(input.getId())
													 .setProgram_number(input.getProgram_array().get(0).getProgram_number())
													 .setElement_pid(input.getProgram_array().get(0).getVideo_array().get(0).getPid());
		
		TaskBO videoTask = new TaskBO().setId(videoTaskId)
									   .setType("passby")
									   .setEs_source(videoSource)
									   .setEncode_array(new ArrayList<EncodeBO>());
		
		EncodeBO videoEncode = new EncodeBO().setEncode_id(encodeVideoId)
											 .setPassby(new JSONObject());
		
		videoTask.getEncode_array().add(videoEncode);
		
		tasks.add(videoTask);
		
		//音频
		TaskSourceBO audioSource = new TaskSourceBO().setInput_id(input.getId())
													 .setProgram_number(input.getProgram_array().get(0).getProgram_number())
													 .setElement_pid(input.getProgram_array().get(0).getAudio_array().get(0).getPid());
		
		TaskBO audioTask = new TaskBO().setId(audioTaskId)
									   .setType("passby")
									   .setEs_source(audioSource)
									   .setEncode_array(new ArrayList<EncodeBO>());
		
		EncodeBO audioEncode = new EncodeBO().setEncode_id(encodeAudioId)
				                             .setPassby(new JSONObject());
		
		audioTask.getEncode_array().add(audioEncode);
		
		tasks.add(audioTask);
		
		return tasks;
		
	}
	
	/**
	 * 收录信息转output<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月2日 下午5:00:43
	 * @param String id 标识
	 * @param List<TaskBO> tasks 任务s
	 * @param RecordVO record 收录信息
	 * @return OutputBO
	 */
	public OutputBO record2OutputBO(String id, List<TaskBO> tasks, RecordVO record) throws Exception{
		
		String recordName = record.getOutputParam().getName();
		
		//录制输出hls_record	
		String outputId = new StringBufferWrapper().append("output")
												   .append("-")
												   .append("record")
												   .append("-")
												   .append(id)
												   .toString();
		
		OutputBO output = new OutputBO();			
		output.setId(outputId);
		
		OutputHlsRecordBO hls_record = new OutputHlsRecordBO().setName(recordName)
															  .setMedia_array(new ArrayList<BaseMediaBO>());
		
		for(TaskBO taskBO: tasks){
			for(EncodeBO encode: taskBO.getEncode_array()){
				BaseMediaBO media = new BaseMediaBO().setTask_id(taskBO.getId())
													 .setEncode_id(encode.getEncode_id());
				
				hls_record.getMedia_array().add(media);
			}
		}
		
		return output;
	}
	
}
