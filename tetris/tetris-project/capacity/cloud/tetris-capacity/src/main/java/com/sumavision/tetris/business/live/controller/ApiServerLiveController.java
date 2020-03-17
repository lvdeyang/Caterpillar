package com.sumavision.tetris.business.live.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sumavision.tetris.business.live.service.StreamPassbyService;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/capacity/live")
public class ApiServerLiveController {

    @Autowired
    private StreamPassbyService streamPassbyService;

   
    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/create/easytask")
    public Object createTask(HttpServletRequest request, String list, Long userId) throws Exception {

    	List<String> array=Arrays.asList(list.split(","));
    	
    	
        streamPassbyService.createTask(userId,
        		array,
                "camera" + userId,"1280,720", 500000, 25, "16:9");

        return null;
    }
    
    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/create/task")
    public Object createTask(HttpServletRequest request, String list, Long userId,
    		String resolution,int bitrate,int fps,String hw) throws Exception {

    	List<String> array=Arrays.asList(list.split(","));
    	
    	
        streamPassbyService.createTask(userId,
        		array,
                "camera" + userId,resolution, bitrate, fps, hw);

        return null;
    }

    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/create/record")
    public Object createTask(HttpServletRequest request, String pubName, String path,
    		String resolution,int bitrate,int fps,String hw) throws Exception {


        streamPassbyService.createRecordTask(pubName, path, resolution, bitrate, fps, hw);

        return null;
    }

    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/delete/recordTask")
    public Object deleteRecordTask(HttpServletRequest request, String pubName) throws Exception {

        streamPassbyService.deleteRecordTask(pubName);

        return null;
    }

    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/switch/task")
    public Object createTask(HttpServletRequest request, long userId, int index) throws Exception {

        streamPassbyService.switchTask(userId, index);

        return null;
    }

    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/delete/task")
    public Object deleteTask(HttpServletRequest request, String list, Long userId) throws Exception {
    	List<String> array=Arrays.asList(list.split(","));
        streamPassbyService.deleteTask(userId,
                array);

        return null;
    }


}
