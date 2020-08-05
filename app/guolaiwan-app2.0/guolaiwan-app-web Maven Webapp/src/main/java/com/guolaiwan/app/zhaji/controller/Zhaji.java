package com.guolaiwan.app.zhaji.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.guolaiwan.app.zhaji.AvailablePo;
import com.guolaiwan.app.zhaji.ZhajiService;

import cn.hutool.json.JSONObject;

@Controller
@RequestMapping("/zhaji")
public class Zhaji {
	
	@ResponseBody
	@RequestMapping(value = "/checkIn", method = RequestMethod.GET)
    public String checkIn(HttpServletRequest request){
		String uniqueCode = request.getParameter("uniqueCode");
		String version = request.getParameter("version");
		String response=ZhajiService.checkIn(uniqueCode,version);
		return response; 
    }
	
	@ResponseBody
	@RequestMapping(value = "/checkAvailable", method = RequestMethod.POST)
    public String checkAvailable(HttpServletRequest request){
	    String result = "";
		try {
            InputStreamReader insr = new InputStreamReader(request.getInputStream(),"utf-8");
            int respInt = insr.read();
            System.out.println(respInt);
            while(respInt != -1){
                result += (char)respInt;
                respInt = insr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		JSONObject jsonObject=new JSONObject(result);
		AvailablePo po=new AvailablePo();
		po.setSerialNumber(jsonObject.getStr("serialNumber"));
		po.setMediaType(jsonObject.getInt("mediaType"));
		po.setUniqueCode(jsonObject.getStr("uniqueCode"));
		po.setAccessDir(jsonObject.getInt("accessDir"));
		po.setVersion(jsonObject.getStr("version"));
		String response=ZhajiService.checkAvailable(po);
		return response;      
    }
	
	
	
	@ResponseBody
	@RequestMapping(value = "/hasHeartbeat", method = RequestMethod.GET)
    public String hasHeartbeat(HttpServletRequest request){
		String uniqueCode = request.getParameter("uniqueCode");
		String response=ZhajiService.checkHeart(uniqueCode);
		return response; 
    }
}
