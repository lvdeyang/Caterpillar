package com.sumavision.tetris.business.bvc.service;

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
import com.sumavision.tetris.business.bvc.vo.BvcAudioParamVO;
import com.sumavision.tetris.business.bvc.vo.BvcVO;
import com.sumavision.tetris.business.bvc.vo.BvcVideoParamVO;
import com.sumavision.tetris.business.common.dao.TaskInputDAO;
import com.sumavision.tetris.business.common.dao.TaskOutputDAO;
import com.sumavision.tetris.business.common.enumeration.BusinessType;
import com.sumavision.tetris.business.common.po.TaskInputPO;
import com.sumavision.tetris.business.common.po.TaskOutputPO;
import com.sumavision.tetris.capacity.bo.input.CommonTsBO;
import com.sumavision.tetris.capacity.bo.input.InputBO;
import com.sumavision.tetris.capacity.bo.input.ProgramAudioBO;
import com.sumavision.tetris.capacity.bo.input.ProgramBO;
import com.sumavision.tetris.capacity.bo.input.ProgramVideoBO;
import com.sumavision.tetris.capacity.bo.output.CommonTsOutputBO;
import com.sumavision.tetris.capacity.bo.output.OutputBO;
import com.sumavision.tetris.capacity.bo.output.OutputMediaBO;
import com.sumavision.tetris.capacity.bo.output.OutputProgramBO;
import com.sumavision.tetris.capacity.bo.request.AllRequest;
import com.sumavision.tetris.capacity.bo.response.AllResponse;
import com.sumavision.tetris.capacity.bo.task.EncodeBO;
import com.sumavision.tetris.capacity.bo.task.FpsConvertBO;
import com.sumavision.tetris.capacity.bo.task.H264BO;
import com.sumavision.tetris.capacity.bo.task.H265BO;
import com.sumavision.tetris.capacity.bo.task.PreProcessingBO;
import com.sumavision.tetris.capacity.bo.task.ScaleBO;
import com.sumavision.tetris.capacity.bo.task.TaskBO;
import com.sumavision.tetris.capacity.bo.task.TaskSourceBO;
import com.sumavision.tetris.capacity.config.CapacityProps;
import com.sumavision.tetris.capacity.service.CapacityService;
import com.sumavision.tetris.capacity.service.ResponseService;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class BvcService {
	
	@Autowired
	private CapacityService capacityService;
	
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private TaskOutputDAO taskOutputDao;
	
	@Autowired
	private TaskInputDAO taskInputDao;
	
	@Autowired
	private CapacityProps capacityProps;

	/**
	 * 添加bvc转码任务<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月18日 下午5:11:12
	 * @param BvcVO bvcInfo bvc参数
	 */
	public void addTranscode(BvcVO bvcInfo) throws Exception{
		
		String sourceIp = bvcInfo.getIp();
		String sourcePort = bvcInfo.getPort();
		String uuid = bvcInfo.getBundleId();
		
		String uniq = new StringBufferWrapper().append(sourceIp)
											   .append("%")
											   .append(sourcePort)
											   .toString();
		
		save(uuid, uniq, bvcInfo, BusinessType.BVC);
		
	}
	
	/**
	 * 删除bvc转码<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月18日 下午5:23:04
	 * @param String uuid 任务标识
	 */
	public void deleteTranscode(String uuid) throws Exception{
		
		TaskOutputPO output = delete(uuid);
		
		taskOutputDao.delete(output);
	}
	
	/**
	 * 添加任务流程 -- 一个输入，多个任务，多个输出，
	 *             输入计数+1（并发），
	 *             输出直接存储（不管并发）
	 *             说明：联合unique校验insert（rtmpUrl）； 数据行锁（乐观锁version）校验update<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月18日 下午4:57:09
	 * @param String taskUuid 任务标识
	 * @param String uniq 源标识
	 * @param BvcVO bvcInfo bvc参数
	 * @param BusinessType businessType 类型
	 */
	public void save(
			String taskUuid,
			String uniq,
			BvcVO bvcInfo,
			BusinessType businessType) throws Exception{
		
		TaskInputPO input = taskInputDao.findByUniq(uniq);
		
		if(input == null){
			
			AllRequest allRequest = new AllRequest();
			InputBO inputBO = new InputBO();
			List<TaskBO> taskBOs = new ArrayList<TaskBO>();
 			OutputBO outputBO = new OutputBO();
			try {
				
				inputBO = transformVo2Input(taskUuid, bvcInfo);
				
				taskBOs = transformVo2Tasks(taskUuid, inputBO, bvcInfo);
				
				outputBO = transformVo2Output(taskUuid, taskBOs, bvcInfo);
				
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
				save(taskUuid, uniq, bvcInfo, businessType);
				
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
					inputBO = transformVo2Input(taskUuid, bvcInfo);
				}else{
					inputBO = JSONObject.parseObject(input.getInput(), InputBO.class);
				}
				
				taskBOs = transformVo2Tasks(taskUuid, inputBO, bvcInfo);
				
				outputBO = transformVo2Output(taskUuid, taskBOs, bvcInfo);
				
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
				save(taskUuid, uniq, bvcInfo, businessType);
				
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
	 * <b>日期：</b>2019年12月18日 下午5:06:09
	 * @param String taskUuid 任务标识
	 * @return TaskOutputPO 任务输出
	 */  
	public TaskOutputPO delete(String taskUuid) throws Exception {
		
		TaskOutputPO output = taskOutputDao.findByTaskUuidAndType(taskUuid, BusinessType.BVC);
		
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
	 * bvc参数转inputBO<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月18日 下午4:10:22
	 * @param String id 任务标识
 	 * @param BvcVO bvcInfo bvc参数
	 * @return InputBO
	 */
	public InputBO transformVo2Input(String id, BvcVO bvcInfo) throws Exception{
		
		String inputId = new StringBufferWrapper().append("input-")
												  .append(id)
												  .toString();
		
		String sourceIp = bvcInfo.getIp();
		int sourcePort = Integer.valueOf(bvcInfo.getPort()).intValue();
		
		InputBO inputBO = new InputBO();
		
		//udp_ts			
		CommonTsBO udp_ts = new CommonTsBO().setSource_ip(sourceIp)
											.setSource_port(sourcePort);
		
		inputBO.setId(inputId)
			   .setUdp_ts(udp_ts)
			   .setMedia_type_once_map(new JSONObject())
			   .setProgram_array(new ArrayList<ProgramBO>());
		
		ProgramBO program = new ProgramBO().setProgram_number(1)
										   .setVideo_array(new ArrayList<ProgramVideoBO>())
										   .setAudio_array(new ArrayList<ProgramAudioBO>());

		ProgramVideoBO video = new ProgramVideoBO().setPid(2)
								   				   .setDecode_mode("cpu");
		ProgramAudioBO audio = new ProgramAudioBO().setPid(1)
											       .setDecode_mode("cpu");
		
		program.getVideo_array().add(video);
		program.getAudio_array().add(audio);
		
		inputBO.getProgram_array().add(program);
		
		return inputBO;
	}
	
	/**
	 * bvc参数转为taskBO<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月18日 下午4:13:56
	 * @param String id 任务标识
	 * @param InputBO input 任务input
	 * @param BvcVO bvcInfo bvc参数
	 * @return List<TaskBO>
	 */
	public List<TaskBO> transformVo2Tasks(String id, InputBO input, BvcVO bvcInfo) throws Exception{
		
		
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
		
		//区分视音频转码
		BvcVideoParamVO videoParam = bvcInfo.getVideo();
		BvcAudioParamVO audioParam = bvcInfo.getAudio();
		
		if(videoParam != null){
			
			//视频--转码
			int width = Integer.valueOf(videoParam.getResolution().split("x")[0]).intValue();
			int height = Integer.valueOf(videoParam.getResolution().split("x")[1]).intValue();
			String fps = videoParam.getFps();
			String bitrate = videoParam.getBitrate();
			String codec = videoParam.getCodec(); 
			
			TaskSourceBO videoSource = new TaskSourceBO().setInput_id(input.getId())
					 .setProgram_number(input.getProgram_array().get(0).getProgram_number())
					 .setElement_pid(input.getProgram_array().get(0).getVideo_array().get(0).getPid());

			TaskBO videoTask = new TaskBO().setId(videoTaskId)
				   .setType("video")
				   .setRaw_source(videoSource)
				   .setEncode_array(new ArrayList<EncodeBO>());
			
			EncodeBO videoEncode = new EncodeBO().setEncode_id(encodeVideoId)
						 .setProcess_array(new ArrayList<PreProcessingBO>());
			
			ScaleBO scale = new ScaleBO().setWidth(width)
				 .setHeight(height);
			FpsConvertBO fps_convert = new FpsConvertBO().setFps(fps);
			
			PreProcessingBO video_decode_scale = new PreProcessingBO().setScale(scale);
			PreProcessingBO video_decode_fps = new PreProcessingBO().setFps_convert(fps_convert);
			videoEncode.getProcess_array().add(video_decode_scale);
			videoEncode.getProcess_array().add(video_decode_fps);
			
			//h265
			if(codec.equals("h265")){
			
				H265BO h265 = new H265BO().setBitrate(Integer.valueOf(bitrate))
															 .setRatio(new StringBufferWrapper().append(width)
																  							    .append(":")
																  							    .append(height)
																  							    .toString())
															 .setFps(fps)
															 .setWidth(width)
															 .setHeight(height);
				videoEncode.setHevc(h265);
			
			//h264
			}else if(codec.equals("h264")){
			
				H264BO h264 = new H264BO().setBitrate(Integer.valueOf(bitrate))
										  .setRatio(new StringBufferWrapper().append(width)
				   		  							    					 .append(":")
				   		  							    					 .append(height)
				   		  							    					 .toString())
										  .setFps(fps)
										  .setWidth(width)
										  .setHeight(height);
				videoEncode.setH264(h264);
			
			}
			
			videoTask.getEncode_array().add(videoEncode);
			tasks.add(videoTask);
			
		}else {
			
			//TODO: 视频--透传
			
		}
		
		if(audioParam != null){
			
			//TODO:音频--转码
			String codec = audioParam.getCodec();
			String bitrate = audioParam.getBitrate();
			String sample = audioParam.getSample();
			
		}else{
			
			//音频--透传
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
			
		}
		
		return tasks;
		
	}
	
	/**
	 * bvc参数转为OutputBO<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月18日 下午4:15:38
	 * @param String id 任务标识
	 * @param List<TaskBO> tasks 任务
	 * @param BvcVO bvcInfo bvc参数
	 * @return OutputBO
	 */
	public OutputBO transformVo2Output(String id, List<TaskBO> tasks, BvcVO bvcInfo) throws Exception{
		
		String outputId = new StringBufferWrapper().append("output-")
												   .append(id)
												   .toString();
		
		String udp = bvcInfo.getUdp();
		String dstIp = udp.split("udp://")[1].split(":")[0];
		int dstPort = Integer.valueOf(udp.split("udp://")[1].split(":")[1]).intValue();
		
		OutputBO output = new OutputBO();			
		output.setId(outputId);
		
		//拼媒体
		List<OutputMediaBO> medias = new ArrayList<OutputMediaBO>();
		for(int i=0; i<tasks.size(); i++){
			
			OutputMediaBO media = new OutputMediaBO().setTask_id(tasks.get(i).getId())
													 .setEncode_id(tasks.get(i).getEncode_array().iterator().next().getEncode_id())
													 .setPid(i+1);
			medias.add(media);
			
		}
	
		CommonTsOutputBO udp_ts = new CommonTsOutputBO().setUdp_ts()
														.setIp(dstIp)
														.setPort(dstPort)
														.setLocal_ip(dstIp)
														.setProgram_array(new ArrayList<OutputProgramBO>());
		
		OutputProgramBO program = new OutputProgramBO().setProgram_number(301)
													   .setPmt_pid(101)
													   .setPcr_pid(100)
													   .setMedia_array(new ArrayListWrapper<OutputMediaBO>().addAll(medias).getList());
		
		udp_ts.getProgram_array().add(program);	
		output.setUdp_ts(udp_ts);
		
		return output;
	}
	
}
