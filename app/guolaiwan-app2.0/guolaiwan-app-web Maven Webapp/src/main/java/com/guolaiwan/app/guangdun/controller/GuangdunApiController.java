package com.guolaiwan.app.guangdun.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/checkapi")
public class GuangdunApiController{
	
	@ResponseBody
	@RequestMapping(value = "/scancodeopen")
	public Object  scancodeopen(HttpServletRequest request) throws Exception{
		String param = getRequestJson(request);
		JSONObject paramJson=(JSONObject) JSONObject.parse(param);
		String orderNo=paramJson.getString("jqmpass");
		//此处写订单验证部分【订单号】
		
		/*gateNumber	闸机编号	String	
		rsindex	串口编号	String	
		jqmpass	用户展示二维码秘钥	String*/	
		JSONObject retjson=new JSONObject();
		retjson.put("status", 0);
		retjson.put("count", 1);
		retjson.put("audio", "ok.wav");
		retjson.put("Show_msg", "欢迎光临");
		/*status	返回码	Int	0:有效票、1:无效票、3:团体票
		count	团体票次数	Int	Status=3时,返回团体票次数
		audio	语音提示	String	播放声音文件名.wav格式
		Show_msg	液晶屏显示内容	Srting	支持html样式*/
		return retjson;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sfzopen")
	public Object  sfzopen(HttpServletRequest request) throws Exception{
		String param = getRequestJson(request);
		JSONObject paramJson=(JSONObject) JSONObject.parse(param);
		String orderNo=paramJson.getString("jqmpass");
		//此处写订单验证部分【身份证】
		
		/*gateNumber	闸机编号	String	
		rsindex	串口编号	String	
		jqmpass	用户展示二维码秘钥	String*/	
		JSONObject retjson=new JSONObject();
		retjson.put("status", 0);
		retjson.put("count", 1);
		retjson.put("audio", "ok.wav");
		retjson.put("Show_msg", "欢迎光临");
		/*status	返回码	Int	0:有效票、1:无效票、3:团体票
		count	团体票次数	Int	Status=3时,返回团体票次数
		audio	语音提示	String	播放声音文件名.wav格式
		Show_msg	液晶屏显示内容	Srting	支持html样式*/
		return retjson;
	}
		
	@ResponseBody
	@RequestMapping(value = "/GateOut")
	public Object  GateOut(HttpServletRequest request) throws Exception{
		String param = getRequestJson(request);
		JSONObject paramJson=(JSONObject) JSONObject.parse(param);
		String orderNo=paramJson.getString("gateNumber");
		
		JSONObject retjson=new JSONObject();
		retjson.put("status", 1);
		
		return retjson;
	}
	
	
	private String getRequestJson(HttpServletRequest request) {
		try {
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			request.getInputStream().close();
			br.close();
			return sb.toString();
		} catch (IOException e) {
			return "";
		}
	}
	
}
