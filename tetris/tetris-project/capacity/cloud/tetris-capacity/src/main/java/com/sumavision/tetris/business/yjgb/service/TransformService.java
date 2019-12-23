package com.sumavision.tetris.business.yjgb.service;

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
import com.sumavision.tetris.business.common.service.LockService;
import com.sumavision.tetris.business.yjgb.vo.CodecParamVO;
import com.sumavision.tetris.business.yjgb.vo.InputParamVO;
import com.sumavision.tetris.business.yjgb.vo.MimsVO;
import com.sumavision.tetris.business.yjgb.vo.OutParamVO;
import com.sumavision.tetris.business.yjgb.vo.RecordVO;
import com.sumavision.tetris.business.yjgb.vo.StreamTranscodingVO;
import com.sumavision.tetris.business.yjgb.vo.TaskVO;
import com.sumavision.tetris.capacity.bo.input.CommonTsBO;
import com.sumavision.tetris.capacity.bo.input.InputBO;
import com.sumavision.tetris.capacity.bo.input.ProgramAudioBO;
import com.sumavision.tetris.capacity.bo.input.ProgramBO;
import com.sumavision.tetris.capacity.bo.input.ProgramVideoBO;
import com.sumavision.tetris.capacity.bo.input.RtpEsBO;
import com.sumavision.tetris.capacity.bo.input.UdpPcmBO;
import com.sumavision.tetris.capacity.bo.output.BaseMediaBO;
import com.sumavision.tetris.capacity.bo.output.CommonTsOutputBO;
import com.sumavision.tetris.capacity.bo.output.OutputBO;
import com.sumavision.tetris.capacity.bo.output.OutputHlsRecordBO;
import com.sumavision.tetris.capacity.bo.output.OutputMediaBO;
import com.sumavision.tetris.capacity.bo.output.OutputProgramBO;
import com.sumavision.tetris.capacity.bo.output.OutputRtpEsBO;
import com.sumavision.tetris.capacity.bo.output.OutputRtpesMediaBO;
import com.sumavision.tetris.capacity.bo.request.AllRequest;
import com.sumavision.tetris.capacity.bo.request.CreateOutputsRequest;
import com.sumavision.tetris.capacity.bo.request.DeleteOutputsRequest;
import com.sumavision.tetris.capacity.bo.request.IdRequest;
import com.sumavision.tetris.capacity.bo.response.AllResponse;
import com.sumavision.tetris.capacity.bo.response.CreateOutputsResponse;
import com.sumavision.tetris.capacity.bo.task.AacBO;
import com.sumavision.tetris.capacity.bo.task.EncodeBO;
import com.sumavision.tetris.capacity.bo.task.H264BO;
import com.sumavision.tetris.capacity.bo.task.Mpeg2BO;
import com.sumavision.tetris.capacity.bo.task.MpegBO;
import com.sumavision.tetris.capacity.bo.task.PreProcessingBO;
import com.sumavision.tetris.capacity.bo.task.ResampleBO;
import com.sumavision.tetris.capacity.bo.task.ScaleBO;
import com.sumavision.tetris.capacity.bo.task.TaskBO;
import com.sumavision.tetris.capacity.bo.task.TaskSourceBO;
import com.sumavision.tetris.capacity.config.CapacityProps;
import com.sumavision.tetris.capacity.service.CapacityService;
import com.sumavision.tetris.capacity.service.ResponseService;
import com.sumavision.tetris.capacity.util.http.HttpUtil;
import com.sumavision.tetris.capacity.util.record.RecordUtil;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.user.UserVO;

/**
 * 流转码<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月13日 下午1:58:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TransformService {
	
	private static final String INPUT_PREFIX = "input-";
	
	private static final String TASK_VIDEO_PREFIX = "task-video-";
	
	private static final String TASK_AUDIO_PREFIX = "task-audio-";
	
	private static final String ENCODE_VIDEO_PREFIX = "encode-video-";
	
	private static final String ENCODE_AUDIO_PREFIX  = "encode-audio-";
	
	private static final String OUTPUT_PREFIX = "output-";
	
	@Autowired
	private CapacityService capacityService;
	
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private CapacityProps capacityProps;
	
	@Autowired
	private LockService lockService;
	
	@Autowired
	private TaskOutputDAO taskOutputDao;
	
	@Autowired
	private TaskInputDAO taskInputDao;
	
	/**
	 * 添加流转码任务<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月12日 上午10:54:00
	 * @param user
	 * @param uuid
	 * @param streamTranscodingVO
	 * @param recordVO
	 * @return
	 */
	public String addStreamTask(UserVO user, String uuid, StreamTranscodingVO streamTranscodingVO, RecordVO record) throws Exception{
		
		System.out.println(JSON.toJSONString(record));
		System.out.println(JSON.toJSONString(streamTranscodingVO));
		
		boolean isRecord = false;
		if(record != null){
			isRecord = record.isRecord();
		}
		
		String sourceUrl = streamTranscodingVO.getAssetUrl();
		if(!sourceUrl.startsWith("udp")){
			throw new BaseException(StatusCode.FORBIDDEN, "源地址不是udp！");
		}
		
		String sourceIp = sourceUrl.split("@")[1].split(":")[0];
		
		Long sourcePort = Long.valueOf(sourceUrl.split("@")[1].split(":")[1]);
		
		String uniq = new StringBufferWrapper().append(sourceIp)
											   .append("@")
											   .append(sourcePort)
											   .toString();
		
		String recordName = new StringBufferWrapper().append("/home/hls/").append(uuid).toString();
			
		save(uuid, uniq, BusinessType.YJGB, isRecord, recordName, record.getRecordCallback(), streamTranscodingVO);
		
		return uuid;
		
	}
	
	/**
	 * 添加任务流程 -- 一个输入，多个任务，多个输出，
	 *   (乐观锁)    输入计数+1（并发），
	 *             输出直接存储（不管并发）
	 *             说明：联合unique校验insert（ip和port关联）； 数据行锁（乐观锁version）校验update<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月29日 下午1:15:28
	 * @param taskUuid
	 * @param uniq
	 * @param businessType
	 * @param isRecord
	 * @param recordAddress
	 * @param recordCallBackUrl
	 * @param streamVO
	 */
	public void save(
			String taskUuid, 
			String uniq, 
			BusinessType businessType,
			boolean isRecord,
			String recordAddress,
			String recordCallBackUrl,
			StreamTranscodingVO streamVO) throws Exception{
		
		TaskInputPO input = taskInputDao.findByUniq(uniq);
		
		if(input == null){
			
			AllRequest allRequest = new AllRequest();
			InputBO inputBO = new InputBO();
			List<TaskBO> taskBOs = new ArrayList<TaskBO>();
 			List<OutputBO> outputBOs = new ArrayList<OutputBO>();
			try {
				
				//转输入
				inputBO = transformVo2InputBO(taskUuid, streamVO);
				
				//转任务
				taskBOs = transformVo2TaskBO(taskUuid, inputBO, streamVO);
				
				//转输出
				outputBOs = transformVo2OutputBO(taskUuid, taskBOs, streamVO, isRecord, recordAddress);
				
				input = new TaskInputPO();
				input.setUpdateTime(new Date());
				input.setUniq(uniq);
				input.setTaskUuid(taskUuid);
				input.setInput(JSON.toJSONString(inputBO));
				input.setType(businessType);
				taskInputDao.save(input);
				
				TaskOutputPO output = new TaskOutputPO();
				output.setInputId(input.getId());
				output.setMediaType(streamVO.getMediaType());
				output.setOutput(JSON.toJSONString(outputBOs));
				output.setRecord(isRecord);
				output.setRecordAddress(recordAddress);
				output.setTask(JSON.toJSONString(taskBOs));
				output.setTaskUuid(taskUuid);
				output.setType(businessType);
				output.setRecordCallbackUrl(recordCallBackUrl);
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
				save(taskUuid, uniq, businessType, isRecord, recordAddress, recordCallBackUrl, streamVO);
			
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
 			List<OutputBO> outputBOs = new ArrayList<OutputBO>();
			try {
				
				if(input.getCount().equals(0)){
					//转输入
					inputBO = transformVo2InputBO(taskUuid, streamVO);
				}else{
					inputBO = JSONObject.parseObject(input.getInput(), InputBO.class);
				}
				
				//转任务
				taskBOs = transformVo2TaskBO(taskUuid, inputBO, streamVO);
				
				//转输出
				outputBOs = transformVo2OutputBO(taskUuid, taskBOs, streamVO, isRecord, recordAddress);
				
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
				output.setMediaType(streamVO.getMediaType());
				output.setOutput(JSON.toJSONString(outputBOs));
				output.setRecord(isRecord);
				output.setRecordAddress(recordAddress);
				output.setTask(JSON.toJSONString(taskBOs));
				output.setTaskUuid(taskUuid);
				output.setType(businessType);
				output.setRecordCallbackUrl(recordCallBackUrl);
				output.setUpdateTime(new Date());
				
				taskOutputDao.save(output);
				
				if(input.getCount().equals(1)){
					
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
				save(taskUuid, uniq, businessType, isRecord, recordAddress, recordCallBackUrl, streamVO);
				
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
	 * 删除流转码任务<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月14日 上午11:34:11
	 * @param UserVO user
	 * @param String taskUuid 任务标识
	 */
	public MimsVO deleteStreamTask(UserVO user, String taskUuid) throws Exception {
		
		TaskOutputPO output = delete(taskUuid);
		
		MimsVO mimsVO = new MimsVO();
		
		if(output != null){
			if(output.isRecord()){
				
				String recordPre = new StringBufferWrapper().append("http://")
															.append(capacityProps.getIp())
															.append(":6690")
															.append("/")
															.append(taskUuid)
															.toString();
				
				String recordXml = new StringBufferWrapper().append(recordPre)
															.append("/record.xml")
															.toString();
				
				String vodFolderName = RecordUtil.getInstance().getVodFolderName(recordXml);
				
				String recordUrl = new StringBufferWrapper().append(recordPre)
															.append("/")
															.append(vodFolderName)
															.append("/vod.m3u8")
															.toString();
				
				mimsVO.setNeedAdd(true);
				mimsVO.setName(taskUuid);
				mimsVO.setType(output.getMediaType());
				mimsVO.setHttpUrl(recordUrl);
				
			}else{
				
				taskOutputDao.delete(output);
				mimsVO.setNeedAdd(false);
				
			}
		}
		
		return mimsVO;
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
	public TaskOutputPO delete(String taskUuid) throws Exception {
		
		TaskOutputPO output = taskOutputDao.findByTaskUuidAndType(taskUuid, BusinessType.YJGB);
		
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

	/**
	 * 添加输出 -- 只管udp-ts<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月21日 下午4:12:33
	 * @param String id 流转码标识
	 * @param List<OutParamVO> outputParams 新添加的输出
	 */
	public void addStreamOutput(String id, List<OutParamVO> outputParams) throws Exception{
		
		TaskOutputPO output = taskOutputDao.findByTaskUuidAndType(id, BusinessType.YJGB);
		
		if(output == null){
			throw new BaseException(StatusCode.ERROR, "任务不存在在！");
		}
		
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		
		List<TaskBO> tasks = JSONObject.parseArray(output.getTask(), TaskBO.class);
		
		List<OutputBO> outputs = JSONObject.parseArray(output.getOutput(), OutputBO.class);
		
		List<OutputBO> outputBOs = transformVo2OutputBO(uuid, tasks, outputParams);
		
		CreateOutputsRequest outputsRequest = new CreateOutputsRequest();
		outputsRequest.setOutput_array(new ArrayListWrapper<OutputBO>().addAll(outputBOs).getList());
		//创建输出
		CreateOutputsResponse outputResponse = capacityService.createOutputsAddMsgId(outputsRequest);
		
		//创建输出返回处理 -- 回滚
		List<String> outputIds = responseService.outputResponseProcess(outputResponse, null, null);
		
		outputs.addAll(outputBOs);
		
		output.setOutput(JSON.toJSONString(outputs));
		
		taskOutputDao.save(output);
		
	}
	
	/**
	 * 删除输出 -- 只管udp-ts， 分为协议删除和删除后输出持久化<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月21日 下午4:57:03
	 * @param String id 标识
	 * @param List<OutParamVO> outputParams 需要删除的输出
	 */
	public void deleteStreamOutput(String id, List<OutParamVO> outputParams) throws Exception{
		
		TaskOutputPO taskPO = taskOutputDao.findByTaskUuidAndType(id, BusinessType.YJGB);
		
		if(taskPO == null){
			throw new BaseException(StatusCode.ERROR, "任务不存在在！");
		}
		
		List<OutputBO> outputs = JSONObject.parseArray(taskPO.getOutput(), OutputBO.class);
		
		List<OutputBO> needDeleteOutputs = new ArrayList<OutputBO>();
		for(OutputBO outputBO: outputs){
			for(OutParamVO outParamVO: outputParams){
				String ip = outParamVO.getOutputUrl().split(":")[0];
				String port = outParamVO.getOutputUrl().split(":")[1];
				if(outputBO.getUdp_ts() == null) continue;
				if(outputBO.getUdp_ts().getIp().equals(ip) && outputBO.getUdp_ts().getPort().toString().equals(port)){
					needDeleteOutputs.add(outputBO);		
				}
			}
		}
		
		if(needDeleteOutputs.size() > 0){
			
			outputs.removeAll(needDeleteOutputs);
			
			DeleteOutputsRequest delete = new DeleteOutputsRequest().setOutput_array(new ArrayList<IdRequest>());
			for(OutputBO outputBO: needDeleteOutputs){
				IdRequest idRequest = new IdRequest().setId(outputBO.getId());
				delete.getOutput_array().add(idRequest);
			}

			capacityService.deleteOutputsAddMsgId(delete);
			
			taskPO.setOutput(JSON.toJSONString(outputs));
			taskOutputDao.save(taskPO);
			
		}
		
	}
	
	/**
	 * 录制回调<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月25日 上午10:39:00
	 * @param String id 标识
	 * @param mimsId 媒资id
	 */
	public void recordCallback(String id, String mimsId) throws Exception{
		
		TaskOutputPO task = taskOutputDao.findByTaskUuidAndType(id, BusinessType.YJGB);
		
		if(task != null){
			if(task.isRecord() && task.getRecordCallbackUrl() != null){
				
				String recordCallbackUrl = task.getRecordCallbackUrl();
				
				String url = new StringBufferWrapper().append(recordCallbackUrl)
													  .append("&assetId=")
													  .append(mimsId)
													  .append("&mediaType=")
													  .append(task.getMediaType())
													  .toString();	
				
				System.out.println("回调打印:" + url);
				
				HttpUtil.httpGet(url);
			}
			
			taskOutputDao.delete(task);
		}else{
			throw new BaseException(StatusCode.ERROR, "任务不存在！");
		}
	}
	
	/**
	 * VO转InputBO<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月7日 下午1:28:40
	 * @param String id 标识
	 * @param StreamTranscodingVO streamTranscodingVO
	 * @return InputBO
	 */
	public InputBO transformVo2InputBO(String id, StreamTranscodingVO streamTranscodingVO) throws Exception{
		
		String inputId = new StringBufferWrapper().append(INPUT_PREFIX)
												  .append(id)
												  .toString();
		
		Integer bePcm = streamTranscodingVO.getBePCM();
		String sourceUrl = streamTranscodingVO.getAssetUrl();
		
		InputBO inputBO = new InputBO();
		if(bePcm.equals(0)){
			
			//udp_ts			
			String sourceIp = sourceUrl.split("@")[1].split(":")[0];
			
			int sourcePort = Integer.valueOf(sourceUrl.split("@")[1].split(":")[1]).intValue();
			
			CommonTsBO udp_ts = new CommonTsBO().setSource_ip(sourceIp)
												.setSource_port(sourcePort);
			
			inputBO.setId(inputId)
				   .setUdp_ts(udp_ts);
		}else if(bePcm.equals(1)){
			
			//udp_pcm
			String sourceIp = sourceUrl.split("@")[1].split(":")[0];
			
			int sourcePort = Integer.valueOf(sourceUrl.split("@")[1].split(":")[1]).intValue();
			
			InputParamVO inputParam = streamTranscodingVO.getInputParam();
			
			String sampleFmt = "u8";
			if(inputParam.getSourPrecision().equals(8l)){
				sampleFmt = "u8";
			}else if(inputParam.getSourPrecision().equals(16l)){
				sampleFmt = "s16";
			}
			
			String channelLayout = "mono";
			if(inputParam.getSourChannel().equals(1l)){
				channelLayout = "mono";
			}else if(inputParam.getSourChannel().equals(2l)){
				channelLayout = "stereo";
			}
			
			UdpPcmBO udp_pcm = new UdpPcmBO().setSource_ip(sourceIp)
											 .setSource_port(sourcePort)
											 .setSample_rate(inputParam.getSourSample().intValue())
											 .setSample_fmt(sampleFmt)
											 .setChannel_layout(channelLayout);
			
			inputBO.setId(inputId)
				   .setUdp_pcm(udp_pcm);
			
		}else if(bePcm.equals(2)){
			
			//TODO：暂时不管
		}else if(bePcm.equals(3)){
			
			//rtp_es
			String sourceIp = sourceUrl.split("@")[1].split(":")[0];
			
			int sourcePort = Integer.valueOf(sourceUrl.split("@")[1].split(":")[1]).intValue();
			
			String type = streamTranscodingVO.getMediaType();
			
			RtpEsBO rtp_es = new RtpEsBO().setLocal_port(sourcePort)
					 					  .setType(type)
					 					  .setCodec("auto");
			
			inputBO.setId(inputId)
				   .setRtp_es(rtp_es);
		}
		
		if(streamTranscodingVO.getProgNum() == null){
			//单节目流--不刷源
			inputBO.setMedia_type_once_map(new JSONObject())
				   .setProgram_array(new ArrayList<ProgramBO>());
			
			//区分音视频
			if(streamTranscodingVO.getMediaType().equals("video")){
				
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
			}
			
			if(streamTranscodingVO.getMediaType().equals("audio")){
				
				ProgramBO program = new ProgramBO().setProgram_number(1)
												   .setAudio_array(new ArrayList<ProgramAudioBO>());
				
				ProgramAudioBO audio = new ProgramAudioBO().setPid(1)
						   								   .setDecode_mode("cpu");
				
				program.getAudio_array().add(audio);
				
				inputBO.getProgram_array().add(program);
			}
			
		}else{
			//TODO:没有多节目流
		}
		
		return inputBO;
	}
	
	/**
	 * VO转List<TaskBO><br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月8日 上午10:49:44
	 * @param String id 标识
	 * @param InputBO input 输入协议
	 * @param StreamTranscodingVO streamTranscodingVO
	 * @return List<TaskBO> 协议--任务列表
	 */
	public List<TaskBO> transformVo2TaskBO(String id, InputBO input, StreamTranscodingVO streamTranscodingVO) throws Exception{
		
		List<TaskBO> tasks = new ArrayList<TaskBO>();
		
		/*******
		 * 音频  *
		 *******/
		String audioId = new StringBufferWrapper().append(TASK_AUDIO_PREFIX)
												  .append(id)
												  .toString();
		
		String encodeAudioId = new StringBufferWrapper().append(ENCODE_AUDIO_PREFIX)
														.append(id)
														.toString();
		
		TaskSourceBO audioSource = new TaskSourceBO().setInput_id(input.getId())
				   									 .setProgram_number(input.getProgram_array().get(0).getProgram_number())
				   									 .setElement_pid(input.getProgram_array().get(0).getAudio_array().get(0).getPid());
		
		TaskBO audioTask = new TaskBO().setId(audioId)
				                       .setType("audio")
				                       .setRaw_source(audioSource)
				                       .setEncode_array(new ArrayList<EncodeBO>());
		
		CodecParamVO codecParam = streamTranscodingVO.getTaskVO().getCodecParam();
		
		EncodeBO audioEncode = new EncodeBO().setEncode_id(encodeAudioId)
											 .setProcess_array(new ArrayList<PreProcessingBO>());
		
		ResampleBO resample = new ResampleBO().setSample_rate(codecParam.getAsample() == null? 44100: codecParam.getAsample().intValue())
											  .setChannels(codecParam.getAchannel() == null? 1: codecParam.getAchannel().intValue())
											  .setChannel_layout(codecParam.getChLayout() == null? "mono": codecParam.getChLayout());
		PreProcessingBO audio_decode_processing = new PreProcessingBO().setResample(resample);
		audioEncode.getProcess_array().add(audio_decode_processing);
		
		//mp2
		if(codecParam.getAcodec().equals("0")){
			
			MpegBO mp2 = new MpegBO().setMpeg()
									 .setBitrate(codecParam.getAbitrate() == null? 192000: codecParam.getAbitrate().intValue())
									 .setSample_rate(codecParam.getAsample() == null? 44100: codecParam.getAsample().intValue());
			
			audioEncode.setMp2(mp2);
									 
		//TODO
		}else if(codecParam.getAcodec().equals("1")){
			

		//mp3	
		}else if(codecParam.getAcodec().equals("2")){
		
			MpegBO mp3 = new MpegBO().setMpeg()
									 .setBitrate(codecParam.getAbitrate() == null? 192000: codecParam.getAbitrate().intValue())
									 .setSample_rate(codecParam.getAsample() == null? 44100: codecParam.getAsample().intValue());
			
			audioEncode.setMp3(mp3);
		
		//aac
		}else if(codecParam.getAcodec().equals("3")){
		
			AacBO aac = new AacBO().setAac()
								   .setBitrate(codecParam.getAbitrate() == null? 192000: codecParam.getAbitrate().intValue())
								   .setSample_rate(codecParam.getAsample() == null? 48000: codecParam.getAsample().intValue());
			
			audioEncode.setAac(aac);
			
		//TODO
		}else if(codecParam.getAcodec().equals("4")){
			
		}
		
		audioTask.getEncode_array().add(audioEncode);
		tasks.add(audioTask);
		
		/*******
		 * 视频  *
		 *******/
		if(streamTranscodingVO.getMediaType().equals("video")){
			
			String videoId = new StringBufferWrapper().append(TASK_VIDEO_PREFIX)
													  .append(id)
													  .toString();
			
			String encodeVideoId = new StringBufferWrapper().append(ENCODE_VIDEO_PREFIX)
															.append(id)
															.toString();
			
			TaskSourceBO videoSource = new TaskSourceBO().setInput_id(input.getId())
														 .setProgram_number(input.getProgram_array().get(0).getProgram_number())
														 .setElement_pid(input.getProgram_array().get(0).getVideo_array().get(0).getPid());
			
			TaskBO videoTask = new TaskBO().setId(videoId)
									       .setType("video")
									       .setRaw_source(videoSource)
									       .setEncode_array(new ArrayList<EncodeBO>());
			
			EncodeBO videoEncode = new EncodeBO().setEncode_id(encodeVideoId)
												 .setProcess_array(new ArrayList<PreProcessingBO>());
			
			int width = (codecParam.getVresolution() == null || codecParam.getVresolution().equals("")) ?  1920 : Integer.valueOf(codecParam.getVresolution().split("x")[0]).intValue();
			int height = (codecParam.getVresolution() == null || codecParam.getVresolution().equals("")) ? 1080 : Integer.valueOf(codecParam.getVresolution().split("x")[1]).intValue();
			
			ScaleBO scale = new ScaleBO().setWidth(width)
										 .setHeight(height);
			PreProcessingBO video_decode_processing = new PreProcessingBO().setScale(scale);
			videoEncode.getProcess_array().add(video_decode_processing);
			
			//mpeg2
			if(codecParam.getVcodec().equals("0")){
				
				Mpeg2BO mpeg2 = new Mpeg2BO().setBitrate(codecParam.getVbitrate() == null? 1500000: codecParam.getVbitrate().intValue())
											 .setRatio(codecParam.getVratio() == null? "16:9": codecParam.getVratio())
											 .setWidth(width)
											 .setHeight(height);
				videoEncode.setMpeg2(mpeg2);
				
			//h264
			}else if(codecParam.getVcodec().equals("1")){
				
				H264BO h264 = new H264BO().setBitrate(codecParam.getVbitrate() == null? 1500000: codecParam.getVbitrate().intValue())
										  .setRatio(codecParam.getVratio() == null? "16:9": codecParam.getVratio())
										  .setWidth(width)
										  .setHeight(height);
				videoEncode.setH264(h264);
				
			}
			
			videoTask.getEncode_array().add(videoEncode);
			tasks.add(videoTask);
		}
		
		return tasks;
	}
	
	/**
	 * VO转List<OutputBO><br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月11日 上午10:11:58
	 * @param String id 标识
	 * @param List<TaskBO> tasks 任务
	 * @param StreamTranscodingVO streamTranscodingVO
	 * @return List<OutputBO>
	 */
	public List<OutputBO> transformVo2OutputBO(
			String id, 
			List<TaskBO> tasks, 
			StreamTranscodingVO streamTranscodingVO,
			boolean isRecord,
			String recordName) throws Exception{
		
		List<OutputBO> outputs = new ArrayList<OutputBO>();
		
		TaskVO task = streamTranscodingVO.getTaskVO();
		
		List<OutParamVO> outputParams = task.getOutParam();
		
		if(outputParams != null){
			for(int i=0; i<outputParams.size(); i++){
				
				OutParamVO outParam = outputParams.get(i);
				
				String outputIp = outParam.getOutputUrl().split(":")[0];
				
				int outputPort = Integer.valueOf(outParam.getOutputUrl().split(":")[1]).intValue();
				
				//udp-ts
				if(task.getEsType().equals(0)){
					
					String outputId = new StringBufferWrapper().append(OUTPUT_PREFIX)
															   .append(i+1)
															   .append("-")
															   .append(id)
															   .toString();

					OutputBO output = new OutputBO();			
					output.setId(outputId);
					
					//拼媒体
					List<OutputMediaBO> medias = new ArrayList<OutputMediaBO>();
					for(TaskBO taskBO: tasks){
						
						if(taskBO.getType().equals("video")){
							OutputMediaBO media = new OutputMediaBO().setTask_id(taskBO.getId())
																	 .setType(taskBO.getType())
																	 .setEncode_id(taskBO.getEncode_array().iterator().next().getEncode_id())
																	 .setPid(outParam.getVid1pid() == null?1: outParam.getVid1pid());
							medias.add(media);
						}
						
						if(taskBO.getType().equals("audio")){
							OutputMediaBO media = new OutputMediaBO().setTask_id(taskBO.getId())
																	 .setType(taskBO.getType())
																	 .setEncode_id(taskBO.getEncode_array().iterator().next().getEncode_id())
																	 .setPid(outParam.getAud1pid() == null?2: outParam.getAud1pid());
							medias.add(media);
						}
						
					}
				
					CommonTsOutputBO udp_ts = new CommonTsOutputBO().setUdp_ts()
																	.setIp(outputIp)
																	.setPort(outputPort)
																	.setLocal_ip(outParam.getLocalIp() == null?capacityProps.getIp(): outParam.getLocalIp())
																	.setProgram_array(new ArrayList<OutputProgramBO>());
					
					OutputProgramBO program = new OutputProgramBO().setProgram_number(outParam.getProgNum() == null?301: outParam.getProgNum())
																   .setPmt_pid(outParam.getPmtpid() == null?101: outParam.getPmtpid())
																   .setPcr_pid(outParam.getPcrpid() == null?100: outParam.getPcrpid())
																   .setMedia_array(new ArrayListWrapper<OutputMediaBO>().addAll(medias).getList());
					
					udp_ts.getProgram_array().add(program);	
					output.setUdp_ts(udp_ts);
					
					outputs.add(output);
					
				//不用
				}else if(task.getEsType().equals(1)){
					
					throw new BaseException(StatusCode.FORBIDDEN, "封装不支持udp-es");
					
				//不用
				}else if(task.getEsType().equals(2)){
					
					throw new BaseException(StatusCode.FORBIDDEN, "封装不支持KT-RTP");
					
				//rtp-es(视频需要分两路输出) -- 只用考虑音频
				}else if(task.getEsType().equals(3)){
					
					//TODO：参数不明确
					for(TaskBO taskBO: tasks){
						if(taskBO.getType().equals("video")){
							
							String vcode = task.getCodecParam().getVcodec();
							
							Integer videoPt = 96;
							if(vcode.equals("0")){
								videoPt = 32;
							}else if(vcode.equals("1")){
								videoPt = 96;
							}
							
							OutputRtpesMediaBO media = new OutputRtpesMediaBO().setTask_id(taskBO.getId())
																			   .setEncode_id(taskBO.getEncode_array().iterator().next().getEncode_id())
																			   .setPayload_type(videoPt);
							
							String outputId = new StringBufferWrapper().append(OUTPUT_PREFIX)
																	   .append("video")
																	   .append("-")
																	   .append(i+1)
																	   .append("-")
																	   .append(id)
																	   .toString();
							
							OutputBO output = new OutputBO();			
							output.setId(outputId);
							
							OutputRtpEsBO rtp_es = new OutputRtpEsBO().setDst_ip(outputIp)
																	  .setDst_port(outputPort)
																	  .setLocal_ip(outParam.getLocalIp() == null?capacityProps.getIp(): outParam.getLocalIp())
																	  .setMedia(media);
							
							output.setRtp_es(rtp_es);
							
							outputs.add(output);
						}
						
						if(taskBO.getType().equals("audio")){
							
							String acode = task.getCodecParam().getAcodec();
							
							Integer audioPt = 97;
							if(acode.equals("0")){
								audioPt = 14;
							}else if(acode.equals("2")){
								audioPt = 102;
							}else if(acode.equals("3")){
								audioPt = 97;
							}
							
							OutputRtpesMediaBO media = new OutputRtpesMediaBO().setTask_id(taskBO.getId())
																			   .setEncode_id(taskBO.getEncode_array().iterator().next().getEncode_id())
																			   .setPayload_type(audioPt);
							
							String outputId = new StringBufferWrapper().append(OUTPUT_PREFIX)
																	   .append("audio")
																	   .append("-")
																	   .append(i+1)
																	   .append("-")
																	   .append(id)
																	   .toString();
							
							OutputBO output = new OutputBO();			
							output.setId(outputId);
							
							OutputRtpEsBO rtp_es = new OutputRtpEsBO().setDst_ip(outputIp)
																	  .setDst_port(outputPort)
																	  .setLocal_ip(outParam.getLocalIp())
																	  .setMedia(media);
							
							output.setRtp_es(rtp_es);
							
							outputs.add(output);
						}
					}
				}
			}
		}
		
		if(isRecord){
		
			//录制输出hls_record
			String outputId = new StringBufferWrapper().append(OUTPUT_PREFIX)
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
			
			output.setHls_record(hls_record);
			outputs.add(output);
			
		}
		
		return outputs;
	}
	
	/**
	 * 添加输出转换<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月21日 下午3:25:35
	 * @param String id 标识
	 * @param List<TaskBO> tasks 任务
	 * @param List<OutParamVO> outputParams 要添加的输出参数
	 * @return List<OutputBO> 输出
	 */
	public List<OutputBO> transformVo2OutputBO(
			String id, 
			List<TaskBO> tasks, 
			List<OutParamVO> outputParams) throws Exception{
		
		List<OutputBO> outputs = new ArrayList<OutputBO>();
		
		for(int i=0; i<outputParams.size(); i++){
			
			OutParamVO outParam = outputParams.get(i);
			
			String outputIp = outParam.getOutputUrl().split(":")[0];
			
			int outputPort = Integer.valueOf(outParam.getOutputUrl().split(":")[1]).intValue();
			
			String outputId = new StringBufferWrapper().append(OUTPUT_PREFIX)
													   .append(i+1)
													   .append("-")
													   .append(id)
													   .toString();

			OutputBO output = new OutputBO();			
			output.setId(outputId);
			
			//拼媒体
			List<OutputMediaBO> medias = new ArrayList<OutputMediaBO>();
			for(TaskBO taskBO: tasks){
				
				if(taskBO.getType().equals("video")){
					OutputMediaBO media = new OutputMediaBO().setTask_id(taskBO.getId())
															 .setType(taskBO.getType())
															 .setEncode_id(taskBO.getEncode_array().iterator().next().getEncode_id())
															 .setPid(outParam.getVid1pid() == null?1: outParam.getVid1pid());
					medias.add(media);
				}
				
				if(taskBO.getType().equals("audio")){
					OutputMediaBO media = new OutputMediaBO().setTask_id(taskBO.getId())
															 .setType(taskBO.getType())
															 .setEncode_id(taskBO.getEncode_array().iterator().next().getEncode_id())
															 .setPid(outParam.getAud1pid() == null?2: outParam.getAud1pid());
					medias.add(media);
				}
				
			}
		
			CommonTsOutputBO udp_ts = new CommonTsOutputBO().setUdp_ts()
															.setIp(outputIp)
															.setPort(outputPort)
															.setLocal_ip(outParam.getLocalIp() == null?capacityProps.getIp(): outParam.getLocalIp())
															.setProgram_array(new ArrayList<OutputProgramBO>());
			
			OutputProgramBO program = new OutputProgramBO().setProgram_number(outParam.getProgNum() == null?301: outParam.getProgNum())
														   .setPmt_pid(outParam.getPmtpid() == null?101: outParam.getPmtpid())
														   .setPcr_pid(outParam.getPcrpid() == null?100: outParam.getPcrpid())
														   .setMedia_array(new ArrayListWrapper<OutputMediaBO>().addAll(medias).getList());
			
			udp_ts.getProgram_array().add(program);	
			output.setUdp_ts(udp_ts);
			
			outputs.add(output);

		}
		
		return outputs;
		
	}
	
}
