package com.guolaiwan.app.interfac.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.po.LivePO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping("/gatesvr")
public class GateBrakeController {
	//验单二维码，身份证
	@ResponseBody
	@RequestMapping(value = "/CheckCode", method = RequestMethod.POST)//id为0时使用备源
	public Object checkCode(HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
	    //接收json
		//票号值	CodeVal	string	可为二维码值，IC卡卡号，身份证号码等
		//票号类型	CodeType	string	"Q"代表二维码；
		//"C"代表IC卡；
		//"I"代表身份证；
		//"F"代表指纹（1:N验证验票）；"F1"用于指纹1:1验证后，返回指纹验证成功数据给服务器；
		//"B"代表按钮（一般用于使用按钮出园，用于园区计数使用）
		//景点ID	ViewId	string	设备上可设置
		//设备ID（或用户ID）	UId	string	设置上可设置
		//用户密码	UPassword	string	设置上可设置
		//设备序列号	SN	String	唯一
		//随机数	Random	int
		long productId=params.getLong("ViewId");
		String codeType=params.getString("CodeType");
		String orderNo=params.getString("CodeVal");
		if(codeType.equals("Q")){
			//二维码验单 刘岫琳
		}else if(codeType.equals("I")){
			//身份证验单 刘岫琳
		}
		//返回json
		//验票状态	Status	是	Int	验票状态 
		//0为非法票(不开闸)；
		//1为合法票(开闸)；
		//2为合法票，但需要验证指纹(1：1验证)，指纹验证成功后开闸； 
		//3为合法票，但需要验证指纹(1：N验证) ，指纹验证成功后开闸；
		//状态描述	StatusDesc	是	String	屏幕显示的提示信息(字符过长或换行显示时，字符串中可包含\r\n)
		//如：验票成功
		//可开闸次数	TurnGateTimes	否	Int	可开闸次数，默认为1
		//一般用于团体票，刷票一次后，闸机可转动多次。
		//指纹有效天数	FPEffDays	否	int	指纹有效天数。用于指纹在设备上保存时间
		//姓名	Name	否	String	人员姓名
		//编号	Number	否	String	人员编号或证件号码等
		//部门	DeptName	否	String	人员部门或单位
		//照片地址	PhotoAdd	否	String	可浏览的Http照片地址
		Map<String, Object> ret=new HashMap<String, Object>(); 
		ret.put("Status", 1);
		ret.put("StatusDesc", "验票成功：2人");
		ret.put("TurnGateTimes", 2);
        return ret;
	}
	
	@ResponseBody
	@RequestMapping(value = "/IsConnect", method = RequestMethod.POST)//id为0时使用备源
	public Object isConnect(HttpServletRequest request) throws Exception{
		//景点ID	ViewId	string	设备上可设置
		//设备ID（或用户ID）	UId	string	设置上可设置
		//用户密码	UPassword	string	设置上可设置
		//设备序列号	SN	String	唯一
		//随机数	Random	int
		Map<String, Object> ret=new HashMap<String, Object>(); 
		ret.put("success", 1);
		return ret;
		//调用是否成功	String	返回任意字符串即可，如：
		//{"success":"1"}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/GetDateTime", method = RequestMethod.POST)//id为0时使用备源
	public Object getDateTime(HttpServletRequest request) throws Exception{
		//景点ID	ViewId	string	设备上可设置
		//设备ID（或用户ID）	UId	string	设置上可设置
		//用户密码	UPassword	string	设置上可设置
		//设备序列号	SN	String	唯一
		//随机数	Random	int
		Map<String, Object> ret=new HashMap<String, Object>(); 
		ret.put("DateTime", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return ret;
		//日期时间	DateTime	String	格式要求为：yyyy-MM-dd HH:mm:ss
	}
	
}
