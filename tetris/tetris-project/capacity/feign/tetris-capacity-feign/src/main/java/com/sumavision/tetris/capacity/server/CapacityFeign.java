package com.sumavision.tetris.capacity.server;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.config.feign.FeignConfiguration;

import java.util.List;

@FeignClient(name = "tetris-capacity", configuration = FeignConfiguration.class)
public interface CapacityFeign {

    @RequestMapping(value = "/capacity/live/create/rtsptask")
    public JSONObject createRtspTask(@RequestParam("list") String list, @RequestParam("userId") Long userId) throws Exception;

    
    @RequestMapping(value = "/capacity/live/create/temptask")
    public JSONObject createTempTask(@RequestParam("list") String list, @RequestParam("userId") Long userId, @RequestParam("tempId") Long tempId) throws Exception;


    /**
     * @描述
     * @参数
     * @返回值 void
     * @创建人 yud
     * @创建时间 2020/1/11
     */
    @RequestMapping(value = "/capacity/live/delete/task")
    public JSONObject deleteTask(@RequestParam("list") String list, @RequestParam("userId") Long userId) throws Exception;

    /**
     * @描述
     * @参数
     * @返回值 void
     * @创建人 yud
     * @创建时间 2020/1/11
     */
    @RequestMapping(value = "/capacity/live/switch/task")
    public JSONObject swtichTask(@RequestParam("userId") long userId, @RequestParam("index") int index) throws Exception;

    /**
     * @描述 创建录制
     * @参数 pubName
     * @返回值
     * @创建人 yud
     * @创建时间 2020/2/6
     */
    @RequestMapping(value = "/capacity/live/create/record")
    public JSONObject createRecordTask(@RequestParam("pubName") String pubName, @RequestParam("path") String path) throws Exception;

    /**
     *@描述  删除录制任务
     *@参数  pubName
     *@返回值
     *@创建人  yud
     *@创建时间  2020/2/6
     */
    @RequestMapping(value = "/capacity/live/delete/recordTask")
    public JSONObject deleteRecordTask(@RequestParam("pubName") String pubName) throws Exception;

    
    @RequestMapping(value = "/capacity/live/temp/list")
    public JSONObject tempList() throws Exception;
    
    @RequestMapping(value = "/capacity/live/pushtemp/list")
    public JSONObject pushtempList() throws Exception;
    
    @RequestMapping(value = "/capacity/live/transcodetemp/list")
    public JSONObject transcodetempList() throws Exception;

}
