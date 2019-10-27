package com.guolaiwan.app.customs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.customs.CustomsService;
import com.guolaiwan.app.customs.MsgTypeEnums;
import com.guolaiwan.app.customs.bo.CEB304Message;
import com.guolaiwan.app.customs.bo.CEB312Message;
import com.guolaiwan.app.customs.bo.CEB404Message;
import com.guolaiwan.app.customs.bo.CEB412Message;
import com.guolaiwan.app.customs.bo.CEB506Message;
import com.guolaiwan.app.customs.bo.CEB508Message;
import com.guolaiwan.app.customs.bo.CEB510Message;
import com.guolaiwan.app.customs.bo.CEB512Message;
import com.guolaiwan.app.customs.bo.CEB514Message;
import com.guolaiwan.app.customs.bo.CEB604Message;
import com.guolaiwan.app.customs.bo.CEB606Message;
import com.guolaiwan.app.customs.bo.CEB608Message;
import com.guolaiwan.app.customs.bo.CEB622Message;
import com.guolaiwan.app.customs.bo.CEB624Message;
import com.guolaiwan.app.customs.bo.CEB626Message;
import com.guolaiwan.app.customs.bo.CEB702Message;
import com.guolaiwan.app.customs.bo.CEB712Message;
import com.guolaiwan.app.customs.util.XMLUtil;
import com.guolaiwan.app.web.website.controller.WebBaseControll;


@Controller
@RequestMapping("/message")
public class CustomsController extends WebBaseControll {
	
	/**
	 * 各种单子申报接口 返回报文是否成功接收（不是申报成功或失败）
	 * 地址定义为http://www.guolaiwan.net/customs/declare
	 * @param request
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/declare", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object CustomsDeclare(HttpServletRequest request){
		
		String json = getAllRequestJson(request);
		JSONObject obj = JSON.parseObject(json);
		String msgType = obj.getString("messageType");
		
		String result = "";
		String str = "";
		CustomsService customsService = new CustomsService();
		//303订单申报
		if(MsgTypeEnums.OUT_ORDER.equals(msgType)){
			str = customsService.getCeb303(obj);
		}
		//403收款单申报
		if(MsgTypeEnums.OUT_RECEIPTS.equals(msgType)){
			str = customsService.getCeb403(obj);
		}
		//505运单申报
		if(MsgTypeEnums.OUT_LOGISTICS.equals(msgType)){
			str = customsService.getCeb505(obj);
		}
		//507运抵单申报
		if(MsgTypeEnums.OUT_ARRIVAL.equals(msgType)){
			str = customsService.getCeb507(obj);
		}
		//509离境单申报
		if(MsgTypeEnums.OUT_DEPARTURE.equals(msgType)){
			str = customsService.getCeb509(obj);
		}
		//603出口清单申报
		if(MsgTypeEnums.OUT_INVENTORY.equals(msgType)){
			str = customsService.getCeb603(obj);
		}
		//605撤销申请单申报
		if(MsgTypeEnums.OUT_INVTCANCEL.equals(msgType)){
			str = customsService.getCeb605(obj);
		}
		//607清单总分单申报
		if(MsgTypeEnums.OUT_WAYBILL.equals(msgType)){
			str = customsService.getCeb607(obj);
		}
		//701汇总申请单申报
		if(MsgTypeEnums.OUT_SUMMARYAPPLY.equals(msgType)){
			str = customsService.getCeb701(obj);
		}
		//792汇总结果单申报
		if(MsgTypeEnums.OUT_SUMMARYRESULT.equals(msgType)){
			str = customsService.getCeb792(obj);
		}
			
		
		//311订单申报
		if(MsgTypeEnums.IN_ORDER.equals(msgType)){
			str = customsService.getCeb311(obj);
		}
		//411支付申报
		if(MsgTypeEnums.IN_PAYMENT.equals(msgType)){
			str = customsService.getCeb411(obj);
		}
		//511运单申报
		if(MsgTypeEnums.IN_LOGISTICS.equals(msgType)){
			str = customsService.getCeb511(obj);
		}
		//513运单状态申报
		if(MsgTypeEnums.IN_LOGISTICS_STATUS.equals(msgType)){
			str = customsService.getCeb513(obj);
		}
		//621进口清单申报
		if(MsgTypeEnums.IN_INVENTORY.equals(msgType)){
			str = customsService.getCeb621(obj);
		}
		//623撤销申请单申报
		if(MsgTypeEnums.IN_INVTCANCEL.equals(msgType)){
			str = customsService.getCeb623(obj);
		}
		//625退货申请单申报
		if(MsgTypeEnums.IN_INVTREFOUND.equals(msgType)){
			str = customsService.getCeb625(obj);
		}
		//711入库明细单申报
		if(MsgTypeEnums.IN_DELIVERY.equals(msgType)){
			str = customsService.getCeb711(obj);
		}
		
		try {
			result = customsService.sendMessage(msgType, str);
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		}
		
		return result;
	}
	
	/**
	 * 各种单子回调接口 接收申报状态结果通知 返回报文是否成功接收
	 * 地址定义为http://www.guolaiwan.net/customs/results
	 * @param request
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/receiptMessage")
	public Object CustomsReceipt(HttpServletRequest request){
		
		String json = getAllRequestJson(request);
		JSONObject obj = JSON.parseObject(json);
		String msgType = obj.getString("messageType");
		String content = obj.getString("content");
		
		Map<String, Object> ret = new HashMap<String, Object>();
		//304订单回执
		if(MsgTypeEnums.OUT_ORDER_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb304m = XMLUtil.convertXmlStrToObject(CEB304Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb304m, MsgTypeEnums.OUT_ORDER_RETURN);
		}
		//404收款单回执
		if(MsgTypeEnums.OUT_RECEIPTS_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb404m = XMLUtil.convertXmlStrToObject(CEB404Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb404m, MsgTypeEnums.OUT_RECEIPTS_RETURN);
		}
		//506运单回执
		if(MsgTypeEnums.OUT_LOGISTICS_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb506m = XMLUtil.convertXmlStrToObject(CEB506Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb506m, MsgTypeEnums.OUT_LOGISTICS_RETURN);
		}
		//508运抵单回执
		if(MsgTypeEnums.OUT_ARRIVAL_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb508m = XMLUtil.convertXmlStrToObject(CEB508Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb508m, MsgTypeEnums.OUT_ARRIVAL_RETURN);
		}
		//510离境单回执
		if(MsgTypeEnums.OUT_DEPARTURE_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb510m = XMLUtil.convertXmlStrToObject(CEB510Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb510m, MsgTypeEnums.OUT_DEPARTURE_RETURN);
		}
		//604出口清单回执
		if(MsgTypeEnums.OUT_INVENTORY_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb604m = XMLUtil.convertXmlStrToObject(CEB604Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb604m, MsgTypeEnums.OUT_INVENTORY_RETURN);
		}
		//606撤销申请单回执
		if(MsgTypeEnums.OUT_INVTCANCEL_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb606m = XMLUtil.convertXmlStrToObject(CEB606Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb606m, MsgTypeEnums.OUT_INVTCANCEL_RETURN);
		}
		//608清单总分单回执
		if(MsgTypeEnums.OUT_WAYBILL_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb608m = XMLUtil.convertXmlStrToObject(CEB608Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb608m, MsgTypeEnums.OUT_WAYBILL_RETURN);
		}
		//702汇总申请单回执
		if(MsgTypeEnums.OUT_SUMMARY_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb702m = XMLUtil.convertXmlStrToObject(CEB702Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb702m, MsgTypeEnums.OUT_SUMMARY_RETURN);
		}
		
		
		//312订单回执
		if(MsgTypeEnums.IN_ORDER_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb312m = XMLUtil.convertXmlStrToObject(CEB312Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb312m, MsgTypeEnums.IN_ORDER_RETURN);
		}
		//412支付回执
		if(MsgTypeEnums.IN_PAYMENT_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb412m = XMLUtil.convertXmlStrToObject(CEB412Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb412m, MsgTypeEnums.IN_PAYMENT_RETURN);
		}
		//512运单回执
		if(MsgTypeEnums.IN_LOGISTICS_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb512m = XMLUtil.convertXmlStrToObject(CEB512Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb512m, MsgTypeEnums.IN_LOGISTICS_RETURN);
		}
		//514运单状态回执
		if(MsgTypeEnums.IN_LOGISTICS_STATUS_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb514m = XMLUtil.convertXmlStrToObject(CEB514Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb514m, MsgTypeEnums.IN_LOGISTICS_STATUS_RETURN);
		}
		//622进口清单回执
		if(MsgTypeEnums.IN_INVENTORY_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb622m = XMLUtil.convertXmlStrToObject(CEB622Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb622m, MsgTypeEnums.IN_INVENTORY_RETURN);
		}
		//624撤销申请单回执
		if(MsgTypeEnums.IN_INVTCANCEL_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb624m = XMLUtil.convertXmlStrToObject(CEB624Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb624m, MsgTypeEnums.IN_INVTCANCEL_RETURN);
		}
		//626退货申请单回执
		if(MsgTypeEnums.IN_INVTREFOUND_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb626m = XMLUtil.convertXmlStrToObject(CEB626Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb626m, MsgTypeEnums.IN_INVTREFOUND_RETURN);
		}
		//712入库明细单回执
		if(MsgTypeEnums.IN_DELIVERY_RETURN.equals(msgType)){
			//此处将回执的xml格式转换为对象
			Object ceb712m = XMLUtil.convertXmlStrToObject(CEB712Message.class, content);
			//解析后，去数据库更新申报状态
			updateStatus(ceb712m, MsgTypeEnums.IN_DELIVERY_RETURN);
		}
		
		//处理成功
		ret.put("status","success");
		ret.put("message","报文接收成功");
		
		return ret;
	}
	
	private void updateStatus(Object obj, String msgType){
		//304订单申报状态数据库处理
		if(MsgTypeEnums.OUT_ORDER_RETURN.equals(msgType)){
			CEB304Message ceb304m = (CEB304Message) obj;
		}
		
		//404收款单申报状态数据库处理
		
		//506运单申报状态数据库处理
				
		//...
		
	}
	
}
