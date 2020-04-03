package com.sumavision.tetris.camera;

import com.sumavision.tetris.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CameraController
 * @Description TODO
 * @Author yud
 * @Date 2019/12/24 11:19
 **/
@RestController
@RequestMapping(value = "/show/camera")
public class CameraController {
    @Autowired
    private CameraService cameraService;


    @GetMapping("")
    public Map<String, Object> findAllCamera() throws Exception {
        List<CameraVo> list = cameraService.findAllCamera();
        return Result.sussess(list);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) throws Exception {
        return Result.sussess(cameraService.findById(id));
    }

    @GetMapping("/byUserId/{userId}")
    public Map<String, Object> getByUserId(@PathVariable Long userId) throws Exception {
        return Result.sussess(cameraService.findByUserId(userId));
    }

    @GetMapping("getByShowId/{showId}")
    public Object getByShow(@PathVariable Long showId) throws Exception {
        return cameraService.findByShowId(showId);
    }


    @PostMapping()
    public Object save(@RequestBody List<CameraVo> cameraVo) throws Exception {

        return cameraService.save(cameraVo);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> update(@PathVariable Long id, @RequestBody CameraVo cameraVo) throws Exception {
        cameraVo.setId(id);
        cameraService.update(cameraVo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        cameraService.delete(id);
        return Result.success();
    }

    /**
     * @描述 修改isInUse状态
     * @参数 机位ID
     * @返回值
     * @创建人 yud
     * @创建时间 2020/1/13
     */
    @PostMapping("/changeIsInUse/{id}")
    public Map<String, Object> changeIsInUse(@PathVariable Long id) throws Exception {
        cameraService.changeIsInUse(id);
        return Result.success();
    }

    /**
     * @描述 开始推流
     * @参数 id 机位id
     * @返回值
     * @创建人 yud
     * @创建时间 2020/1/13
     */
    @RequestMapping(value = "isOpen/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> isOpen(@PathVariable Long id) throws Exception {
       
        return Result.success();
    }

    /**
     * @描述
     * @参数 oldId 当前播放的机位id，newId切换后的机位Id
     * @返回值
     * @创建人 yud
     * @创建时间 2020/1/12
     */
    @RequestMapping(value = "switch/{oldId}/{newId}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> Switch(@PathVariable Long oldId, @PathVariable Long newId) throws Exception {
        return Result.sussess(cameraService.switchCamera(oldId, newId));
    }

    /**
     * @描述 终止该用户Id下的所有直播，停止推流
     * @参数 userId
     * @返回值
     * @创建人 yud
     * @创建时间 2020/1/13
     */
    @RequestMapping(value = "stop/{userId}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> Switch(@PathVariable Long userId) throws Exception {
        cameraService.stop(userId);
        return Result.success();
    }
    @RequestMapping(value = "createTask/{userId}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> createTask(@PathVariable Long userId) throws Exception {
        cameraService.createTask(userId);
        return Result.success();
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
    //测试创建流
    @RequestMapping(value = "testCreate", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> testCreateTask() throws Exception {

        return Result.success();
    }
    //测试切换流
    @RequestMapping(value = "testSwitch/{index}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, Object> testSwtich(@PathVariable int  index) throws Exception {
        cameraService.testSwtich(index);
        return Result.success();
    }




}
