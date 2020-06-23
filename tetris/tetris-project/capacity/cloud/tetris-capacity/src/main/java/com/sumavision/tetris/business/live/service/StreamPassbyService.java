package com.sumavision.tetris.business.live.service;

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
import com.sumavision.tetris.business.common.service.LockService;
import com.sumavision.tetris.business.record.vo.RecordVO;
import com.sumavision.tetris.capacity.bo.input.BackUpBO;
import com.sumavision.tetris.capacity.bo.input.BackUpEsAndRawBO;
import com.sumavision.tetris.capacity.bo.input.BackUpFileBO;
import com.sumavision.tetris.capacity.bo.input.BackUpProgramBO;
import com.sumavision.tetris.capacity.bo.input.InputBO;
import com.sumavision.tetris.capacity.bo.input.PidIndexBO;
import com.sumavision.tetris.capacity.bo.input.ProgramAudioBO;
import com.sumavision.tetris.capacity.bo.input.ProgramBO;
import com.sumavision.tetris.capacity.bo.input.ProgramElementBO;
import com.sumavision.tetris.capacity.bo.input.ProgramOutputBO;
import com.sumavision.tetris.capacity.bo.input.ProgramVideoBO;
import com.sumavision.tetris.capacity.bo.input.SourceUrlBO;
import com.sumavision.tetris.capacity.bo.output.BaseMediaBO;
import com.sumavision.tetris.capacity.bo.output.OutputAudioBO;
import com.sumavision.tetris.capacity.bo.output.OutputBO;
import com.sumavision.tetris.capacity.bo.output.OutputHlsBO;
import com.sumavision.tetris.capacity.bo.output.OutputHlsRecordBO;
import com.sumavision.tetris.capacity.bo.output.OutputIndexBO;
import com.sumavision.tetris.capacity.bo.output.OutputMediaGroupBO;
import com.sumavision.tetris.capacity.bo.output.OutputRtmpBO;
import com.sumavision.tetris.capacity.bo.output.OutputStorageBO;
import com.sumavision.tetris.capacity.bo.output.OutputVideoBO;
import com.sumavision.tetris.capacity.bo.request.AllRequest;
import com.sumavision.tetris.capacity.bo.request.DeleteInputsRequest;
import com.sumavision.tetris.capacity.bo.request.DeleteOutputsRequest;
import com.sumavision.tetris.capacity.bo.request.DeleteTasksRequest;
import com.sumavision.tetris.capacity.bo.request.IdRequest;
import com.sumavision.tetris.capacity.bo.request.PutRealIndexRequest;
import com.sumavision.tetris.capacity.bo.response.AllResponse;
import com.sumavision.tetris.capacity.bo.task.AacBO;
import com.sumavision.tetris.capacity.bo.task.EncodeBO;
import com.sumavision.tetris.capacity.bo.task.FontBO;
import com.sumavision.tetris.capacity.bo.task.H264BO;
import com.sumavision.tetris.capacity.bo.task.OsdBO;
import com.sumavision.tetris.capacity.bo.task.PictureOsdObjectBO;
import com.sumavision.tetris.capacity.bo.task.PreProcessingBO;
import com.sumavision.tetris.capacity.bo.task.ResampleBO;
import com.sumavision.tetris.capacity.bo.task.ScaleBO;
import com.sumavision.tetris.capacity.bo.task.StaticPictureOsdBO;
import com.sumavision.tetris.capacity.bo.task.TaskBO;
import com.sumavision.tetris.capacity.bo.task.TaskSourceBO;
import com.sumavision.tetris.capacity.bo.task.TextOsdBO;
import com.sumavision.tetris.capacity.config.CapacityProps;
import com.sumavision.tetris.capacity.service.CapacityService;
import com.sumavision.tetris.capacity.service.ResponseService;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.temp.GlsDao;
import com.sumavision.tetris.temp.GlsPo;
import com.sumavision.tetris.temp.TempDao;
import com.sumavision.tetris.temp.TempPo;

/**
 * 流透传<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月13日 下午1:58:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StreamPassbyService {

    @Autowired
    private CapacityService capacityService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private LockService lockService;

    @Autowired
    private TaskOutputDAO taskOutputDao;

    @Autowired
    private TaskInputDAO taskInputDao;

    @Autowired
    private CapacityProps capacityProps;

    /**
     * 添加任务
     *
     * @param taskId      唯一标识【直播Id】
     * @param srcPubNames 按照机位顺序放入ArrayList【机位的发布名】
     * @param dstPubName  注意所有的dstPubname不可以与srcPubname重名
     */
    @Deprecated
    public void createTask(Long taskId, List<String> srcPubNames, String dstPubName,
    		String resolution,int bitrate,int fps,String hw) {
        try {
            // 创建输入源
            List<InputBO> inputBOs = new ArrayList<InputBO>();
            for (String pubName : srcPubNames) {
                InputBO inputBO = stream2InputBO(pubName, "rtmp://127.0.0.1/live/" + pubName);
                inputBOs.add(inputBO);
            }
            // 创建备份源关系了
            InputBO backInput = stream2BackInputBO(taskId, srcPubNames,null);
            inputBOs.add(backInput);
            // 创建任务了
            String videoTaskId = new StringBufferWrapper().append("task-video-").append(taskId).toString();

            String audioTaskId = new StringBufferWrapper().append("task-audio-").append(taskId).toString();

            String encodeVideoId = new StringBufferWrapper().append("encode-video-").append(taskId).toString();

            String encodeAudioId = new StringBufferWrapper().append("encode-audio-").append(taskId).toString();
            List<TaskBO> taskBOs = stream2TaskBO(videoTaskId, audioTaskId, encodeVideoId, encodeAudioId, backInput
            		,resolution, bitrate, fps, hw,null);
            
            
            
            // 创建输出了
            String outputId = new StringBufferWrapper().append("output-").append(taskId).toString();
            OutputBO outputBO = streamRtmp2OutputBO(outputId, videoTaskId, audioTaskId, encodeVideoId, encodeAudioId,
                    dstPubName);
            
            

            ///OutputBO recordOutputBo = record2OutputBO(taskId+"", taskBOs, "/home/hls");
            
            AllRequest allRequest = new AllRequest();
            allRequest.setInput_array(inputBOs);
            allRequest.setTask_array(taskBOs);
            allRequest.setOutput_array(new ArrayListWrapper<OutputBO>().add(outputBO).getList());

            String[] pullServerList=capacityProps.getPip().split(",");
            int index=1;
            for (String url : pullServerList) {
            	String destPubUrl="rtmp://"+url+"/live/"+dstPubName;
            	OutputBO temOutputBO = streamUrlRtmp2OutputBO(outputId+"-"+index, videoTaskId, audioTaskId, encodeVideoId, encodeAudioId,
            			destPubUrl);
            	allRequest.getOutput_array().add(temOutputBO);
            	index++;
			}
            
            AllResponse allResponse = capacityService.createAllAddMsgId(allRequest, capacityProps.getIp(),
                    capacityProps.getPort());

            responseService.allResponseProcess(allResponse);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    /***
     * 
     * @param taskId
     * @param srcUrls
     * @param dstPubName
     * @param resolution
     * @param bitrate
     * @param fps
     * @param hw
     * @param sourceType rtsp rtmp
     */
    public void createTask(Long taskId, List<String> srcUrls, String dstPubName,
    		String resolution,int bitrate,int fps,String hw,String sourceType) {
        try {
            // 创建输入源
            List<InputBO> inputBOs = new ArrayList<InputBO>();
            List<String> inputIds=new ArrayList<String>();
            int index=1;
            for (String srcurl : srcUrls) {
                InputBO inputBO = stream2InputBO(taskId+"-"+index, srcurl,sourceType);
                inputIds.add(taskId+"-"+index);
                inputBOs.add(inputBO);
                index++;
            }
            // 创建备份源关系了
            InputBO backInput = stream2BackInputBO(taskId, inputIds,null);
            inputBOs.add(backInput);
            // 创建任务了
            String videoTaskId = new StringBufferWrapper().append("task-video-").append(taskId).toString();

            String audioTaskId = new StringBufferWrapper().append("task-audio-").append(taskId).toString();

            String encodeVideoId = new StringBufferWrapper().append("encode-video-").append(taskId).toString();

            String encodeAudioId = new StringBufferWrapper().append("encode-audio-").append(taskId).toString();
            List<TaskBO> taskBOs = stream2TaskBO(videoTaskId, audioTaskId, encodeVideoId, encodeAudioId, backInput
            		,resolution, bitrate, fps, hw,null);

            // 创建输出了
            String outputId = new StringBufferWrapper().append("output-").append(taskId).toString();
            OutputBO outputBO = streamRtmp2OutputBO(outputId, videoTaskId, audioTaskId, encodeVideoId, encodeAudioId,
                    dstPubName);
            AllRequest allRequest = new AllRequest();
            allRequest.setInput_array(inputBOs);
            allRequest.setTask_array(taskBOs);
            allRequest.setOutput_array(new ArrayListWrapper<OutputBO>().add(outputBO).getList());
            
            AllResponse allResponse = capacityService.createAllAddMsgId(allRequest, capacityProps.getIp(),
                    capacityProps.getPort());

            responseService.allResponseProcess(allResponse);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    
    
    
    @Autowired
    TempDao tempDao;
    @Autowired
    GlsDao glsDao;
    
    public void createTempTask(Long taskId, List<String> srcPubNames, String dstPubName,
    		long tempId) {
        try {
            // 创建输入源
            List<InputBO> inputBOs = new ArrayList<InputBO>();
            for (String pubName : srcPubNames) {
                InputBO inputBO = stream2InputBO(pubName, "rtmp://127.0.0.1/live/" + pubName);
                inputBOs.add(inputBO);
            }
            TempPo tempPo=tempDao.findOne(tempId);
            // 创建备份源关系了
            InputBO backInput = stream2BackInputBO(taskId, srcPubNames,tempPo.getFilePath());
            inputBOs.add(backInput);
            // 创建任务了
            String videoTaskId = new StringBufferWrapper().append("task-video-").append(taskId).toString();

            String audioTaskId = new StringBufferWrapper().append("task-audio-").append(taskId).toString();

            String encodeVideoId = new StringBufferWrapper().append("encode-video-").append(taskId).toString();

            String encodeAudioId = new StringBufferWrapper().append("encode-audio-").append(taskId).toString();
            
           
            List<GlsPo> glsPos=glsDao.findByTempId(tempId);
            
            List<TaskBO> taskBOs = stream2TaskBO(videoTaskId, audioTaskId, encodeVideoId, encodeAudioId, backInput
            		,tempPo.getX()+","+tempPo.getY(), tempPo.getRate(),tempPo.getFrame(),tempPo.getRatio(),glsPos);
            
            
            
            // 创建输出了
            String outputId = new StringBufferWrapper().append("output-").append(taskId).toString();
            OutputBO outputBO = streamRtmp2OutputBO(outputId, videoTaskId, audioTaskId, encodeVideoId, encodeAudioId,
                    dstPubName);
            
            

            ///OutputBO recordOutputBo = record2OutputBO(taskId+"", taskBOs, "/home/hls");
            
            AllRequest allRequest = new AllRequest();
            allRequest.setInput_array(inputBOs);
            allRequest.setTask_array(taskBOs);
            allRequest.setOutput_array(new ArrayListWrapper<OutputBO>().add(outputBO).getList());

            String[] pullServerList=capacityProps.getPip().split(",");
            int index=1;
            for (String url : pullServerList) {
            	String destPubUrl="rtmp://"+url+"/live/"+dstPubName;
            	OutputBO temOutputBO = streamUrlRtmp2OutputBO(outputId+"-"+index, videoTaskId, audioTaskId, encodeVideoId, encodeAudioId,
            			destPubUrl);
            	allRequest.getOutput_array().add(temOutputBO);
            	index++;
			}
            
            AllResponse allResponse = capacityService.createAllAddMsgId(allRequest, capacityProps.getIp(),
                    capacityProps.getPort());

            responseService.allResponseProcess(allResponse);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    

    /**
     * 创建录制任务【mr】
     *
     * @param pubName    发布名称【要录制视频的发布名称】
     * @param recordPath 录制路径
     */
    public void createRecordTask(String pubName, String recordPath) {
        try {
            // 创建输入源
            List<InputBO> inputBOs = new ArrayList<InputBO>();
            InputBO inputBO = stream2InputBO(pubName, "rtmp://127.0.0.1/live/" + pubName);
            inputBOs.add(inputBO);


            // 创建任务了
            String videoTaskId = new StringBufferWrapper().append("task-video-").append(pubName).toString();

            String audioTaskId = new StringBufferWrapper().append("task-audio-").append(pubName).toString();

            String encodeVideoId = new StringBufferWrapper().append("encode-video-").append(pubName).toString();

            String encodeAudioId = new StringBufferWrapper().append("encode-audio-").append(pubName).toString();
            List<TaskBO> taskBOs = stream2PassbyTaskBO(videoTaskId, audioTaskId, encodeVideoId, encodeAudioId, inputBO);


            // 创建录制
            OutputBO recordOutputBo = record2OutputBO(pubName, taskBOs, recordPath);
            // 发送命令了

            AllRequest allRequest = new AllRequest();
            allRequest.setInput_array(inputBOs);
            allRequest.setTask_array(taskBOs);
            allRequest.setOutput_array(new ArrayListWrapper<OutputBO>().add(recordOutputBo).getList());

            AllResponse allResponse = capacityService.createAllAddMsgId(allRequest, capacityProps.getIp(),
                    capacityProps.getPort());

            responseService.allResponseProcess(allResponse);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 删除录制任务【mr】
     *
     * @param pubName【录制的源的发布名称】
     * @throws Exception
     */
    public void deleteRecordTask(String pubName) throws Exception {

        // 删除输出
        String outputId = new StringBufferWrapper().append("output").append("-").append("record").append("-").append(pubName)
                .toString();
        DeleteOutputsRequest deleteOutputsRequest = new DeleteOutputsRequest();
        List<IdRequest> outIdRequests = new ArrayList<IdRequest>();
        outIdRequests.add(new IdRequest().setId(outputId));
        deleteOutputsRequest.setOutput_array(outIdRequests);
        capacityService.deleteOutputsAddMsgId(deleteOutputsRequest);
        // 删除任务
        String videoTaskId = new StringBufferWrapper().append("task-video-").append(pubName).toString();

        String audioTaskId = new StringBufferWrapper().append("task-audio-").append(pubName).toString();
        DeleteTasksRequest deleteTasksRequest = new DeleteTasksRequest();
        List<IdRequest> taskIdRequests = new ArrayList<IdRequest>();
        taskIdRequests.add(new IdRequest().setId(videoTaskId));
        taskIdRequests.add(new IdRequest().setId(audioTaskId));
        deleteTasksRequest.setTask_array(taskIdRequests);
        capacityService.deleteTasksAddMsgId(deleteTasksRequest);
        // 删除输入
        DeleteInputsRequest deleteInputsRequest = new DeleteInputsRequest();
        List<IdRequest> idRequests = new ArrayList<IdRequest>();
        IdRequest idRequest = new IdRequest().setId(pubName);
        idRequests.add(idRequest);

        deleteInputsRequest.setInput_array(idRequests);
        capacityService.deleteInputsAddMsgId(deleteInputsRequest);

    }


    private OutputBO record2OutputBO(String taskId, List<TaskBO> tasks, String recordPath) throws Exception {

        // 录制输出hls_record
        String outputId = new StringBufferWrapper().append("output").append("-").append("record").append("-").append(taskId)
                .toString();

        OutputBO output = new OutputBO();
        output.setId(outputId);

        OutputHlsRecordBO hls_record = new OutputHlsRecordBO().setName(recordPath)
                .setMedia_array(new ArrayList<BaseMediaBO>());

        for (TaskBO taskBO : tasks) {
            for (EncodeBO encode : taskBO.getEncode_array()) {
                BaseMediaBO media = new BaseMediaBO().setTask_id(taskBO.getId()).setEncode_id(encode.getEncode_id());

                hls_record.getMedia_array().add(media);
            }
        }
        output.setHls_record(hls_record);
        return output;
    }

    /**
     * rtmp输出<br/>
     * <b>作者:</b>Mr<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2019年11月13日 下午5:03:36
     *
     * @param outputId
     * @param videoTaskId
     * @param audioTaskId
     * @param encodeVideoId
     * @param encodeAudioId
     * @param name
     * @return
     */
    private OutputBO streamRtmp2OutputBO(String outputId, String videoTaskId, String audioTaskId, String encodeVideoId,
                                         String encodeAudioId, String name) throws Exception {

        OutputRtmpBO rtmp = new OutputRtmpBO().setServer_url("rtmp://127.0.0.1/live/" + name)
                .setAud_exist(true).setVid_exist(true);

        BaseMediaBO vmedia = new BaseMediaBO().setEncode_id(encodeVideoId).setTask_id(videoTaskId);
        BaseMediaBO amedia = new BaseMediaBO().setEncode_id(encodeAudioId).setTask_id(audioTaskId);

        rtmp.setMedia_array(new ArrayListWrapper().add(vmedia).add(amedia).getList());

        OutputBO output = new OutputBO().setId(outputId).setRtmp(rtmp);

        return output;
    }
    
	private OutputBO streamUrlRtmp2OutputBO(String outputId, String videoTaskId, String audioTaskId,
			String encodeVideoId, String encodeAudioId, String nameurl) throws Exception {

		OutputRtmpBO rtmp = new OutputRtmpBO().setServer_url(nameurl).setAud_exist(true)
				.setVid_exist(true);

		BaseMediaBO vmedia = new BaseMediaBO().setEncode_id(encodeVideoId).setTask_id(videoTaskId);
		BaseMediaBO amedia = new BaseMediaBO().setEncode_id(encodeAudioId).setTask_id(audioTaskId);

		rtmp.setMedia_array(new ArrayListWrapper().add(vmedia).add(amedia).getList());

		OutputBO output = new OutputBO().setId(outputId).setRtmp(rtmp);

		return output;
	}

    /**
     * 备份输入<br/>
     * <b>作者:</b>Mr<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2019年11月13日 下午4:12:20
     *
     * @param srcPubNames
     * @return
     */
    public InputBO stream2BackInputBO(Long taskId, List<String> srcPubNames,String filePath) throws Exception {

        String backInputId = new StringBufferWrapper().append("backup-").append(taskId).toString();

        BackUpEsAndRawBO back_up_es = new BackUpEsAndRawBO().setSelect_index("0").setMode("manual");
        back_up_es.setProgram_array(new ArrayList<BackUpProgramBO>());
        //back_up_es.setOutput_program(new ArrayList<ProgramOutputBO>());
        int count = 0;
        for (String inputId : srcPubNames) {

            ProgramElementBO velementBO = new ProgramElementBO().setType("audio").setPid(1).setProgram_switch_array(
                    new ArrayListWrapper<PidIndexBO>().addAll(generatePidIndex(srcPubNames.size(), 0)).getList());
            ProgramElementBO aelementBO = new ProgramElementBO().setType("video").setPid(2).setProgram_switch_array(
                    new ArrayListWrapper<PidIndexBO>().addAll(generatePidIndex(srcPubNames.size(), 1)).getList());
            List<ProgramElementBO> elementBOs = new ArrayList<ProgramElementBO>();
            elementBOs.add(velementBO);
            elementBOs.add(aelementBO);
            BackUpProgramBO backupPro = new BackUpProgramBO().setInput_id(inputId).setProgram_number(1)
                    .setElement_array(new ArrayListWrapper<ProgramElementBO>().addAll(elementBOs).getList());
            back_up_es.getProgram_array().add(backupPro);
            ProgramOutputBO outPro = new ProgramOutputBO().setProgram_number(1).setElement_array(elementBOs);
            if (count == 0) {
                back_up_es.setOutput_program(outPro);
            }
            count++;
        }

        if(filePath!=null&&!filePath.isEmpty()){
            back_up_es.setFile(new BackUpFileBO().setUrl(filePath));
        }

        // 创建输入
        InputBO input = new InputBO().setBack_up_raw(back_up_es).setId(backInputId)
                .setProgram_array(new ArrayList<ProgramBO>()).setNormal_map(new JSONObject());
        ProgramBO program = new ProgramBO().setProgram_number(1).setVideo_array(new ArrayList<ProgramVideoBO>())
                .setAudio_array(new ArrayList<ProgramAudioBO>());

        ProgramVideoBO video = new ProgramVideoBO().setPid(2);
        ProgramAudioBO audio = new ProgramAudioBO().setPid(1);

        program.getVideo_array().add(video);
        program.getAudio_array().add(audio);
        input.getProgram_array().add(program);

        return input;

    }

    /**
     * 非常恶心的生成pid顺序，mr自己懂就好。
     *
     * @param count
     * @param index
     * @return
     */
    private List<PidIndexBO> generatePidIndex(int count, int index) {
        List<PidIndexBO> pidIndexBOs = new ArrayList<PidIndexBO>();
        for (int i = 0; i < count; i++) {
            PidIndexBO pidIndexBO = new PidIndexBO().setPid_index(index);
            pidIndexBOs.add(pidIndexBO);
        }
        return pidIndexBOs;
    }

    /**
     * 删除任务
     *
     * @param taskId      任务Id【直播Id】
     * @param srcPubNames 发布名称【机位发布名称】
     * @throws Exception
     */
    public void deleteTask(Long taskId, List<String> srcPubNames) throws Exception {

        // 删除输出

        String outputId = new StringBufferWrapper().append("output-").append(taskId).toString();
        DeleteOutputsRequest deleteOutputsRequest = new DeleteOutputsRequest();
        List<IdRequest> outIdRequests = new ArrayList<IdRequest>();
        outIdRequests.add(new IdRequest().setId(outputId));
        
        
        String[] pullServerList=capacityProps.getPip().split(",");
        int index=1;
        for (String url : pullServerList) {
        	String tempOutId = new StringBufferWrapper().append("output-").append(taskId).append("-"+index).toString();
        	outIdRequests.add(new IdRequest().setId(tempOutId));
        	index++;
		}
        deleteOutputsRequest.setOutput_array(outIdRequests);
        
        capacityService.deleteOutputsAddMsgId(deleteOutputsRequest);
        // 删除任务
        String videoTaskId = new StringBufferWrapper().append("task-video-").append(taskId).toString();

        String audioTaskId = new StringBufferWrapper().append("task-audio-").append(taskId).toString();
        DeleteTasksRequest deleteTasksRequest = new DeleteTasksRequest();
        List<IdRequest> taskIdRequests = new ArrayList<IdRequest>();
        taskIdRequests.add(new IdRequest().setId(videoTaskId));
        taskIdRequests.add(new IdRequest().setId(audioTaskId));
        deleteTasksRequest.setTask_array(taskIdRequests);
        capacityService.deleteTasksAddMsgId(deleteTasksRequest);
        // 删除输入备份关系
        DeleteInputsRequest deleteInputsRequest = new DeleteInputsRequest();
        List<IdRequest> idRequests = new ArrayList<IdRequest>();
        String backInputId = new StringBufferWrapper().append("backup-").append(taskId).toString();
        IdRequest backidRequest = new IdRequest().setId(backInputId);
        idRequests.add(backidRequest);
        // 删除输入

        for (String inputId : srcPubNames) {
            IdRequest idRequest = new IdRequest().setId(inputId);
            idRequests.add(idRequest);
        }
        deleteInputsRequest.setInput_array(idRequests);
        capacityService.deleteInputsAddMsgId(deleteInputsRequest);

    }

    public void switchTask(Long taskId, int index) throws Exception {
        String backInputId = new StringBufferWrapper().append("backup-").append(taskId).toString();

        capacityService.changeBackUp(backInputId, index + "", capacityProps.getIp(), capacityProps.getPort());
    }

    /* 上面都是MR黄写的 */

   
    /**
     * 删除透传任务<br/>
     * <b>作者:</b>wjw<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2019年11月20日 下午3:18:34
     *
     * @param String uuid 任务标识
     */
    public void deleteRtmp2Hls(String uuid) throws Exception {

        TaskOutputPO output = delete(uuid);

        taskOutputDao.delete(output);
    }

    /**
     * 删除任务流程 -- 输入计数减 一（并发） 输出返回，上层删除（不管并发） TODO： 数据没有清除，之后起线程清除<br/>
     * <b>作者:</b>wjw<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2019年11月27日 下午12:28:35
     *
     * @param String taskUuid 任务流程标识
     * @return TaskOutputPO 任务输出
     */
    public TaskOutputPO delete(String taskUuid) throws Exception {

        TaskOutputPO output = taskOutputDao.findByTaskUuidAndType(taskUuid, BusinessType.LIVE);

        if (output != null) {

            TaskInputPO input = taskInputDao.findOne(output.getInputId());

            if (input != null) {

                try {

                    input.setUpdateTime(new Date());
                    if (input.getCount() >= 1) {
                        input.setCount(input.getCount() - 1);
                    }
                    taskInputDao.save(input);

                    AllRequest allRequest = new AllRequest();

                    OutputBO outputBO = JSONObject.parseObject(output.getOutput(), OutputBO.class);
                    List<TaskBO> tasks = JSONObject.parseArray(output.getTask(), TaskBO.class);
                    InputBO inputBO = JSONObject.parseObject(input.getInput(), InputBO.class);

                    if (input.getCount().equals(0) && input.getInput() != null) {
                        allRequest.setInput_array(new ArrayListWrapper<InputBO>().add(inputBO).getList());
                    }
                    if (tasks != null) {
                        allRequest.setTask_array(new ArrayListWrapper<TaskBO>().addAll(tasks).getList());
                    }
                    if (outputBO != null) {
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
     * rtmp输入<br/>
     * <b>作者:</b>wjw<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2019年11月13日 下午4:12:20
     *
     * @param id
     * @param rtmpUrl
     * @return
     */
    public InputBO stream2InputBO(String inputId, String rtmpUrl) throws Exception {

        SourceUrlBO rtmp = new SourceUrlBO().setUrl(rtmpUrl);

        InputBO input = new InputBO().setRtmp(rtmp).setId(inputId).setMedia_type_once_map(new JSONObject())
                .setProgram_array(new ArrayList<ProgramBO>()).setMedia_type_once_map(new JSONObject());

        ProgramBO program = new ProgramBO().setProgram_number(1).setVideo_array(new ArrayList<ProgramVideoBO>())
                .setAudio_array(new ArrayList<ProgramAudioBO>());

        ProgramVideoBO video = new ProgramVideoBO().setPid(2).setDecode_mode("cpu");
        ProgramAudioBO audio = new ProgramAudioBO().setPid(1).setDecode_mode("cpu");

        program.getVideo_array().add(video);
        program.getAudio_array().add(audio);

        input.getProgram_array().add(program);

        return input;

    }
    
    /***
     * 
     * @param inputId
     * @param rtmpUrl
     * @param sourceType rtsp rtmp
     * @return
     * @throws Exception
     */
    public InputBO stream2InputBO(String inputId, String url,String sourceType) throws Exception {

        SourceUrlBO source = new SourceUrlBO().setUrl(url);

        InputBO input = new InputBO();
        if(sourceType.equals("rtmp")){
        	input.setRtmp(source);
        }else if(sourceType.equals("rtsp")){
        	input.setRtsp(source);
        }
        		
        input.setId(inputId).setMedia_type_once_map(new JSONObject())
                .setProgram_array(new ArrayList<ProgramBO>()).setMedia_type_once_map(new JSONObject());

        ProgramBO program = new ProgramBO().setProgram_number(1).setVideo_array(new ArrayList<ProgramVideoBO>())
                .setAudio_array(new ArrayList<ProgramAudioBO>());

        ProgramVideoBO video = new ProgramVideoBO().setPid(2).setDecode_mode("cpu");
        ProgramAudioBO audio = new ProgramAudioBO().setPid(1).setDecode_mode("cpu");

        program.getVideo_array().add(video);
        program.getAudio_array().add(audio);

        input.getProgram_array().add(program);

        return input;

    }
    

    /**
     * 任务<br/>
     * <b>作者:</b>wjw<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2019年11月13日 下午4:44:02
     *
     * @param id
     * @param input
     * @return
     */
    public List<TaskBO> stream2TaskBO(String videoTaskId, String audioTaskId, String encodeVideoId,
                                      String encodeAudioId, InputBO input,
                                      String resolution,int bitrate,int fps,String hw,List<GlsPo> glsList) throws Exception {

        List<TaskBO> tasks = new ArrayList<TaskBO>();

        // 视频
        TaskSourceBO videoSource = new TaskSourceBO().setInput_id(input.getId())
                .setProgram_number(input.getProgram_array().get(0).getProgram_number())
                .setElement_pid(input.getProgram_array().get(0).getVideo_array().get(0).getPid());

        TaskBO videoTask = new TaskBO().setId(videoTaskId).setType("video").setRaw_source(videoSource)
                .setEncode_array(new ArrayList<EncodeBO>())
                .setDecode_process_array(new ArrayList<PreProcessingBO>());

        String[] res=resolution.split(",");
        H264BO h264 = new H264BO().setBitrate(Integer.valueOf(bitrate))
                .setRatio(hw)
                .setFps(fps+"")
                .setMax_bframe(0)
                .setWidth(Integer.parseInt(res[0]))
                .setHeight(Integer.parseInt(res[1]));

        EncodeBO videoEncode = new EncodeBO().setEncode_id(encodeVideoId).setH264(h264);

        
        ScaleBO scaleBO=new ScaleBO().setHeight(Integer.parseInt(res[1])).setWidth(Integer.parseInt(res[0]))
        		.setPlat("cpu").setNv_card_idx(0).setMode("slow");
        PreProcessingBO encodepreProcessingBO=new PreProcessingBO();
        encodepreProcessingBO.setScale(scaleBO);
        List<PreProcessingBO> encodepreProcessingBOs=new ArrayList<PreProcessingBO>();
        encodepreProcessingBOs.add(encodepreProcessingBO);
        videoEncode.setProcess_array(encodepreProcessingBOs);
        videoTask.getEncode_array().add(videoEncode);
        
        //字幕
        if(glsList!=null){
        	for (GlsPo gls : glsList) {
        		if(gls.getWidth()==0||gls.getHeight()==0){
        			continue;
        		}

        		if(gls.getType()==0){
        			OsdBO osdBO=new OsdBO().setHeight(gls.getHeight()).setWidth(gls.getWidth()).setX(gls.getX()).setY(gls.getY())
        	        		.setHas_background(false).setBackground_color(gls.getBackgroundColor())
        	        		.setTrack_type(gls.getTrackType()).setTrack_speed(gls.getRollSpead())
        	        		.setFont(new FontBO().setFamily(gls.getFontFamily()).
        	        				setColor(gls.getFontColor()).setSize(gls.getFontSize())
        	        				.setHas_border(false).setBorder_color("(255,255,255,60)"))
        	        		.setContent(gls.getContent());
        	        TextOsdBO textOsdBO=new TextOsdBO().setText_osds(new ArrayListWrapper().add(osdBO).getList());
        	        PreProcessingBO preProcessingBO=new PreProcessingBO().setText_osd(textOsdBO);
        	        videoTask.getDecode_process_array().add(preProcessingBO);
        		}else{
        			PictureOsdObjectBO pObjectBO=new PictureOsdObjectBO().setAuto_scale(true)
        	        		.setHeight(gls.getHeight()).setWidth(gls.getWidth()).setX(gls.getX()).setY(gls.getY()).setTransparent(0)
        	        		.setPath(gls.getLogoPath());
        	        StaticPictureOsdBO staticPictureOsdBO=new StaticPictureOsdBO()
        	        		.setStatic_pic_osds(new ArrayListWrapper().add(pObjectBO).getList());
        	        PreProcessingBO preProcessingBOlogo=new PreProcessingBO().setStatic_pic_osd(staticPictureOsdBO);
        	        videoTask.getDecode_process_array().add(preProcessingBOlogo);  
        		}
				
			}
        }
        

        //添加缩放
        
        //videoTask.setDecode_process_array(preProcessingBOs);
        
        
        tasks.add(videoTask);

        // 音频
        TaskSourceBO audioSource = new TaskSourceBO().setInput_id(input.getId())
                .setProgram_number(input.getProgram_array().get(0).getProgram_number())
                .setElement_pid(input.getProgram_array().get(0).getAudio_array().get(0).getPid());

        TaskBO audioTask = new TaskBO().setId(audioTaskId).setType("audio").setRaw_source(audioSource)
                .setEncode_array(new ArrayList<EncodeBO>());

        AacBO aac = new AacBO().setAac()
                .setBitrate(192000)
                .setSample_rate(44100);

        EncodeBO audioEncode = new EncodeBO().setEncode_id(encodeAudioId).setAac(aac)
                .setProcess_array(new ArrayList<PreProcessingBO>());

        ResampleBO resample = new ResampleBO().setSample_rate(44100)
                .setChannels(1)
                .setChannel_layout("mono");
        PreProcessingBO audio_decode_processing = new PreProcessingBO().setResample(resample);
        audioEncode.getProcess_array().add(audio_decode_processing);

        audioTask.getEncode_array().add(audioEncode);

        tasks.add(audioTask);

        return tasks;

    }

    /**
	 * 任务<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月13日 下午4:44:02
	 * @param id
	 * @param input
	 * @return
	 */
	public List<TaskBO> stream2PassbyTaskBO(String videoTaskId, String audioTaskId, String encodeVideoId, String encodeAudioId, InputBO input) throws Exception{
		
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
    
    
   

}
