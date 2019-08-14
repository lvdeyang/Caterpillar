package com.guolaiwan.app.tianshitongcheng.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/tianshi")
public class TianShiController extends BaseController {
	@Autowired
	private OrderInfoDAO orderinfoDAO; 
	
	
	/**
	 * 回调结果通知 根据回调的是啥来写相应的逻辑
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/changeorderstate")
	public String  huidiao(HttpServletRequest request){
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject result = JSON.parseObject(param);
        System.out.println(result.getString("method")+"--------");
        String method=result.getString("method");
        if(method.equals("validate")){
        	String success=result.getString("success");
        	if(success.equals("true")){
        		String orderId=result.getString("another_orders_id");
        		System.out.println(orderId+"--------------------------");
        		OrderInfoPO order = orderinfoDAO.get(Long.parseLong(orderId));
        		order.setOrderState(OrderStateType.TESTED);
        	}
        	
        }
		return "success";
	}
	
	/**
	 * 解析json
	 * @param request
	 * @return
	 */
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
