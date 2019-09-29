package com.guolaiwan.app.customs.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.customs.MsgTypeEnums;
import com.guolaiwan.app.tianshitongcheng.api.TianShiTongChengAPI;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.website.wxpay.controller.WxPayController;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;

import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPay;


@Controller
@RequestMapping("/customs")
public class CustomsController extends WebBaseControll {
	
	
	
	/**
	 * 回调结果通知 报关订单是否成功
	 * 地址定义为http://www.guolaiwan.net/customs/results
	 * @param request
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/results")
	public Object  huidiao(HttpServletRequest request) throws Exception{
		
		String json=getAllRequestJson(request);
		JSONObject obj=JSON.parseObject(json);
		if(MsgTypeEnums.ADD_ORDER_BACK.equals(obj.getString("messageType"))){
			
			String content=obj.getString("content");
			//此处处理报关订单回执
			//这个xml见海关回执
			//报文格式定义见海关总署公告2018年第56号 “业务节点报文”下各单证回执报文定义
			
			/*系统唯一序号 guid C36 是 系统生成36位唯一序号（英文字母大 写） 
			电商平台代码 ebpCode C..50 是 电商平台的海关注册登记编号或统一 社会信用代码。 
			电商企业代码 ebcCode C..18 是 电商企业的海关注册登记编号或统一 社会信用代码，对应清单的收发货人。 
			订单编号 orderNo C..60 是 电商平台的交易订单编号 
			回执状态 returnStatus C1..10 是 操作结果（1电子口岸已暂存/2电子口 岸申报中/3发送海关成功/4发送海关 失败/100海关退单/120海关入库等）, 若小于0数字表示处理异常回执 
			回执时间 returnTime C17 是 操作时间(格式：yyyyMMddHHmmssfff) 
			回执信息 returnInfo C..1000 是 
			备注（如:退单原因*/
			
			
			/*<ceb:CEB304Message guid="311af125-6fed-4603-8c5d-49b1fa4b4b9b" version="1.0"  xmlns:ceb="http://www.chinaport.gov.cn/ceb" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<ceb:OrderReturn>
				<ceb:guid>311af125-6fed-4603-8c5d-49b1fa4b4b9b</ceb:guid>
				<ceb:ebpCode>1105910159</ceb:ebpCode>
				<ceb:ebcCode>1105910159</ceb:ebcCode>
				<ceb:orderNo>order2018050711340001</ceb:orderNo>
				<ceb:returnStatus>2</ceb:returnStatus>
				<ceb:returnTime>20180507153001001</ceb:returnTime>
				<ceb:returnInfo>新增申报成功[4CDE1CFD-EDED-46B1-946C-B8022E42FC94]</ceb:returnInfo>
			</ceb:OrderReturn>*/
			
			addOrderBack(content);
			
		}
		Map<String, Object> ret=new HashMap<String, Object>();
		ret.put("status","success");
		ret.put("message","报文接收成功");
		return "success";
	}
	
	private void addOrderBack(String content){
		
	}
	
}
