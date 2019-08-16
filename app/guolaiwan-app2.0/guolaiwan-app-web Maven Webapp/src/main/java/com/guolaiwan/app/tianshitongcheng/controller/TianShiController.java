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
import com.guolaiwan.app.tianshitongcheng.api.TianShiTongChengAPI;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;


@Controller
@RequestMapping("/tianshi")
public class TianShiController {
	
	@Autowired
	private OrderInfoDAO orderinfoDAO;
	
	
	/**
	 * 回调结果通知 根据回调的是啥来写相应的逻辑
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/changeorderstate")
	public String  huidiao(HttpServletRequest request){
		System.out.println("天时同城回调成功");
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject result = JSON.parseObject(param);
        System.out.println("通知类型为验单："+result.getString("method"));
        String method=result.getString("method");
        if(method.equals("validate")){
        	String success=result.getString("success");
        	if(success.equals("true")){
        		String info=result.getString("info");
        		JSONObject infos = JSON.parseObject(info);
        		String orderId=infos.getString("another_orders_id");
        		System.out.println("回调获得过来玩的订单ID为："+orderId);
        		OrderInfoPO order = orderinfoDAO.get(Long.parseLong(orderId));
        		order.setOrderState(OrderStateType.TESTED);
        		orderinfoDAO.saveOrUpdate(order);
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
	
	/**
	 * 判断是不是分销商品 是的话归属哪一家
	 */
	@ResponseBody
	@RequestMapping(value = "/isdistributerefund")
	public String isDistribute(HttpServletRequest request) {
		long orderId = Long.parseLong(request.getParameter("orderId"));
		System.out.println(orderId);
		OrderInfoPO order = orderinfoDAO.get(orderId);
		String distributeId = order.getDistributeId();
		long merchatId=order.getShopId();
		String result="";
		if(distributeId!=null&&distributeId!=""){
			if(merchatId==358){
				System.out.println("调用了凤凰山的退款接口");
				result = TianShiTongChengAPI.refundFHSPost(distributeId);
			}else if(merchatId==386){
				System.out.println("调用了皮影乐园的退款接口");
				result = TianShiTongChengAPI.refundPYLYPost(distributeId);
			}
			System.out.println("接口返回参数：");
			System.err.println(result);
			JSONObject parseObject = JSON.parseObject(result);
			String success = parseObject.get("success").toString();
			if(success.equals("true")){
				System.out.println("接口调用成功");
				String info = parseObject.get("info").toString();
				JSONObject infojson = JSON.parseObject(info);
				String status = infojson.get("status").toString();
				if(status.equals("3"))return "success";
			}else{
				System.out.println("接口调用失败");
				return "lose";
			}
		}else{
			//不是分销的订单
			System.out.println("不是分销的订单");
			return "error";
		}
		return "lose";
	}
	
	
}
