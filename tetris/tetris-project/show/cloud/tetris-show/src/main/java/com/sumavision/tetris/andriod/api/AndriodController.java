package com.sumavision.tetris.andriod.api;

import com.sumavision.tetris.camera.CameraService;
import com.sumavision.tetris.capacity.server.CapacityFeign;
import com.sumavision.tetris.capacity.server.CapacityFeignService;
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

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AndriodController
 * @Description TODO
 * @Author Mr
 * @Date 2019/12/24 11:19
 **/
@RestController
@RequestMapping(value = "/api/android/show")
public class AndriodController {
    @Autowired
    private CameraService cameraService;

    @Autowired
    UserQuery userQuery;
    
    @Autowired
    LiveService liveService;

    @Autowired
    LiveQuery liveQuery;
    /**
     * 根据机位Id查询机位信息
     * @param id 机位Id
     * @return
     * @throws Exception
     */
    @GetMapping("/findCameraById/{id}")
    public Map<String, Object> findCameraById(@PathVariable Long id) throws Exception {
        return Result.sussess(cameraService.findById(id));
    }
    /**
     * 查询直播用户所有机位
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/findAllCameras")
    public Map<String, Object> findAllCameras() throws Exception {
    	UserVO user=userQuery.current();
    	return Result.sussess(cameraService.findByUserId(user.getId()));
    }
    
    @GetMapping("/findAllTemps")
    public Map<String, Object> findAllTemps() throws Exception {
    	UserVO user=userQuery.current();
    	return Result.sussess(cameraService.tempList());
    }
    
    
    @GetMapping("/findAllPushTemps")
    public Map<String, Object> findAllPushTemps() throws Exception {
    	UserVO user=userQuery.current();
    	return Result.sussess(cameraService.pushtempList());
    }
    
    @GetMapping("/findAllTranscodeTemps")
    public Map<String, Object> findAllTranscodeTemps() throws Exception {
    	UserVO user=userQuery.current();
    	return Result.sussess(cameraService.transcodetempList());
    }
    
    @GetMapping("/findLive")
    public Map<String, Object> findLive() throws Exception {
    	UserVO user=userQuery.current();
    	return Result.sussess(liveService.getByAnchorId(user.getId()));
    }

    
   /**
    * 设置机位使用状态
    * @param id
    * @param status 0/未使用 1/使用
    * @return
    * @throws Exception
    */
    @PostMapping("/changeCameraStatus/{id}/{status}")
    public Map<String, Object> changeCameraStatus(@PathVariable Long id,@PathVariable int status) throws Exception {
        cameraService.changeStatus(id,status);
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
    	cameraService.deleteTask(user.getId());
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

    
    /**
     *@描述   创建录制
     *@参数  id 机位id
     *@返回值
     *@创建人  yud
     *@创建时间  2020/2/5
     */
    @RequestMapping(value = "createRecordTask/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> createRecordTask(@PathVariable Long id) throws Exception {
        cameraService.createRecordTask(id);
        return Result.success();
    }

    /**
     *@描述  删除录制任务
     *@参数
     *@返回值
     *@创建人  yud
     *@创建时间  2020/2/7
     */
    @RequestMapping(value = "deleteRecordTask/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> deleteRecordTask(@PathVariable Long id) throws Exception {
        cameraService.deleteRecordTask(id);
        return Result.success();
    }
    /**
     *@描述  根据用户id查询他所有的录制视频
     *@参数  userID
     *@返回值
     *@创建人  yud
     *@创建时间  2020/2/7
     */
    @RequestMapping(value = "getRecordByUser/{userId}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> getRecordByUser(@PathVariable Long userId) throws Exception {
        cameraService.getRecordByUser(userId);
        return Result.success();
    }
    /**
     *@描述  根据路径删除某个录制
     *@参数
     *@返回值
     *@创建人  yud
     *@创建时间  2020/2/7
     */
    @RequestMapping(value = "deleteRecordByPath/{path}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> getRecordByUser(@PathVariable List<String> path) throws Exception {
        cameraService.deleteRecordByPath(path);
        return Result.success();
    }
}
