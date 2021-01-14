package com.sumavision.tetris.business.live.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.sumavision.tetris.business.live.service.StreamPassbyService;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.temp.GlsPo;
import com.sumavision.tetris.temp.TempDao;
import com.sumavision.tetris.temp.TempPo;

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
    @RequestMapping(value = "/create/rtsptask")
    public Object creatertspTask(HttpServletRequest request, String list, Long userId) throws Exception {

    	List<String> array=Arrays.asList(list.split(","));
    	
    	
        streamPassbyService.createTask(userId,
        		array,
                "camera" + userId,"720,576", 500000, 25, "4:3","rtsp");

        /*Thread.sleep(3000);
        int count=1000;
        int index=0;
        while (index<count) {
        	 streamPassbyService.switchTask(userId,count++%2);
        	 Thread.sleep(3000);
		}*/
        return null;
    }
    
    
    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/create/rtmptask")
    public Object creatertmpTask(HttpServletRequest request, String list, Long userId) throws Exception {

    	List<String> array=Arrays.asList(list.split(","));
    	
    	
        streamPassbyService.createTask(userId,
        		array,
                "camera" + userId,"720,576", 500000, 25, "4:3","rtmp");

        /*Thread.sleep(3000);
        int count=1000;
        int index=0;
        while (index<count) {
        	 streamPassbyService.switchTask(userId,count++%2);
        	 Thread.sleep(3000);
		}*/
        return null;
    }
 
    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/create/temptask")
    public Object createTempTask(HttpServletRequest request, String list, Long userId,Long tempId) throws Exception {

    	List<String> array=Arrays.asList(list.split(","));
    	
    	
        streamPassbyService.createTempTask(userId,
        		array,
                "camera" + userId,tempId);

        return null;
    }
    
    @Autowired
    TempDao tempDao;
    
    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/temp/list")
    public Object tempList(HttpServletRequest request) throws Exception {
        return tempDao.findAll();
    }
    
    
    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/pushtemp/list")
    public Object pushtempList(HttpServletRequest request) throws Exception {
        return tempDao.findAllByType("push");
    }
    
    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/transcodetemp/list")
    public Object transcodetempList(HttpServletRequest request) throws Exception {
        return tempDao.findAllByType("transcode");
    }
    

    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/create/record")
    public Object createTask(HttpServletRequest request, String pubName, String path) throws Exception {


        streamPassbyService.createRecordTask(pubName, path);

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
    
    //下面是新直播平台接口
    
    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/create/livetask")
    public Object createLiveTask(HttpServletRequest request, @RequestBody String json) throws Exception {

    	JSONObject jsonObj=JSONObject.parseObject(json);
        Long liveId=jsonObj.getLong("liveId");
        String destPubName=jsonObj.getString("pubName");
    	List<String> srcPubNames=new ArrayList<String>();
    	JSONArray arr=jsonObj.getJSONArray("cameras");
    	for (Object obj : arr) {
			JSONObject cameraJson=(JSONObject)obj;
			srcPubNames.add(cameraJson.getString("pubName"));
		}
    	TempPo tempPo=JSONObject.toJavaObject(jsonObj.getJSONObject("template"), TempPo.class);
    	List<GlsPo> glsPos=new ArrayList<GlsPo>();
    	JSONArray glsArr=jsonObj.getJSONArray("gls");
    	for (Object obj : glsArr) {
			JSONObject glsJson=(JSONObject)obj;
			glsPos.add(JSONObject.toJavaObject(glsJson, GlsPo.class));
		}
        streamPassbyService.createLiveTask(jsonObj.getLong("liveId"), srcPubNames, destPubName, tempPo, glsPos);

        return null;
    }

}
