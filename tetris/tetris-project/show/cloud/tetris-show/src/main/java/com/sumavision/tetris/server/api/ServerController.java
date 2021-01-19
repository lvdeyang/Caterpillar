package com.sumavision.tetris.server.api;

import com.sumavision.tetris.camera.CameraService;
import com.sumavision.tetris.capacity.server.CapacityFeign;
import com.sumavision.tetris.capacity.server.CapacityFeignService;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.result.Result;
import com.sumavision.tetris.show.LiveDao;
import com.sumavision.tetris.show.LivePo;
import com.sumavision.tetris.show.LiveQuery;
import com.sumavision.tetris.show.LiveService;
import com.sumavision.tetris.show.LiveVo;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AndriodController
 * @Description TODO
 * @Author Mr
 * @Date 2019/12/24 11:19
 **/
@RestController
@RequestMapping(value = "/api/server/show")
public class ServerController {
    @Autowired
    private CameraService cameraService;

    @Autowired
    UserQuery userQuery;
    
    @Autowired
    LiveService liveService;

    @Autowired
    LiveQuery liveQuery;
   
    /**
     * 创建机位
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/createCameras/{count}")
    public Map<String, Object> createCameras(@PathVariable int count) throws Exception {
    	UserVO user=userQuery.current();
    	return Result.sussess(cameraService.createCameras(user.getId(),count));
    }
    
   /**
    * 设置机位使用状态
    * @param id
    * @param status 0/未使用 1/使用
    * @return
    * @throws Exception
    */
    @PostMapping("/startPush/{id}")
    public Map<String, Object> startPush(@PathVariable Long id) throws Exception {
        cameraService.changeStatus(id,1);
        return Result.success();
    }
    
    
    @PostMapping("/stopPush/{id}")
    public Map<String, Object> stopPush(@PathVariable Long id) throws Exception {
        cameraService.changeStatus(id,0);
        return Result.success();
    }
    

    @Autowired
    LiveDao liveDao;
    
    
    @RequestMapping(value = "/startTempShow/{tempId}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> startTempShow(@PathVariable Long tempId) throws Exception {
    	UserVO user=userQuery.current();
    	
    	List<LivePo> livePo=liveDao.getByAnchorId(user.getId());
    	if(livePo!=null&&!livePo.isEmpty()){
    		livePo.get(0).setStatus(1);
    		liveDao.save(livePo.get(0));
    	}
		
    	//调用capacityfein开始任务
    	cameraService.createTempTask(user.getId(),tempId);
        return Result.success();
    }
    
    @GetMapping("/findAllTranscodeTemps")
    public Map<String, Object> findAllTranscodeTemps() throws Exception {
    	UserVO user=userQuery.current();
    	return Result.sussess(cameraService.transcodetempList());
    }
    
    /**
     * 停止直播
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/stopShow", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> stopShow() throws Exception {
    	UserVO user=userQuery.current();
    	List<LivePo> livePo=liveDao.getByAnchorId(user.getId());
    	
    	if(livePo!=null&&!livePo.isEmpty()){
    		livePo.get(0).setStatus(0);
    		liveDao.save(livePo.get(0));
    	}
    	//删除直播残留文件
    	File file=new File("/home/hls/live");
    	File[] subfiles=file.listFiles();
    	if(subfiles!=null){
    		for (File file2 : subfiles) {
    			file2.delete();
    		}
    	}
    	
    	//调用capacityfein停止任务
    	cameraService.deleteTask(user.getId(),null);
        return Result.success();
    }

    @RequestMapping(value = "/switchVod", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> switchVod() throws Exception {
    	UserVO user=userQuery.current();
    	cameraService.switchVod(user.getId());
        return Result.success();
    }
    
    
    @RequestMapping(value = "switch/{oldId}/{newId}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> Switch(@PathVariable Long oldId, @PathVariable Long newId) throws Exception {
        return Result.sussess(cameraService.switchCamera(oldId, newId));
    }
    
    
    //对接过来玩新平台接口
    @RequestMapping(value = "/startLive", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> startLiveShow(String json) throws Exception {
    	UserVO user=userQuery.current();
    	//调用capacityfein开始任务
    	cameraService.createLiveTask(json);
        return Result.success();
    }
    
    @RequestMapping(value = "/stopLive", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> stopLiveShow(Long liveId,String srcPubNames) throws Exception {
    	//调用capacityfein开始任务
    	cameraService.deleteTask(liveId,srcPubNames);
        return Result.success();
    }
    
    @RequestMapping(value = "/switch", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> switchCamera(Long liveId,Integer index) throws Exception {
    	//调用capacityfein开始任务
    	cameraService.switchCameraNodatabase(liveId,index);
        return Result.success();
    }
    
    
    
    @RequestMapping(value = "/startRecord", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> startLiveRecord(String pubName) throws Exception {
    	//调用capacityfein开始任务
    	cameraService.createRecordLive(pubName);
        return Result.success();
    }
    
    @RequestMapping(value = "/stopRecord", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> stopLiveRecord(String pubName) throws Exception {
    	//调用capacityfein开始任务
    	cameraService.deleteRecordLive(pubName);
        return Result.success();
    }
    
    //上传文件
    @RequestMapping(value = "/uploadMedia", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importFiles(@RequestParam("filePoster") MultipartFile uploadFile) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			String fileName=uploadFile.getOriginalFilename();
		    String dateStr=DateUtil.format(new Date(),"yyyyMMddHHmmss");
		    String newFileName=dateStr+"_"+fileName;
			File dest=new File("/home/hls/media/"+newFileName);
			if(!dest.exists()){
				dest.createNewFile();
			}
			uploadFile.transferTo(dest);
			data.put("Url", "http://47.95.241.89:6690/media/"+newFileName);
		} catch (Exception e) {

		}

		return data;
	}

    
}
