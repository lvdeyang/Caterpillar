package com.guolaiwan.app.qingdongling.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping
public class CallBackController {

	@ResponseBody
	@RequestMapping(value = "/callBackAction", method = RequestMethod.GET)//id为0时使用备源
    public String getCallBackAction(HttpServletRequest request){
		 String orderState = request.getParameter("OrderState");
	        Map<String,String> resmap = new HashMap<String,String>();
	        resmap.put("OrderState",orderState);
	        //6-取票通知
	        if(orderState=="6"){
	            //订单取票通知{"VerifyCode":"0a332f709b3466db219c2d23d6cdb7e4","Order16U":"16435716","ActionTime":" 2019-05-22 16:23:26","OrderCall":"12201905221131112757972176896","OrderState":6,"Action":6}
	            resmap.put("VerifyCode",request.getParameter("VerifyCode"));
	            resmap.put("Order16U",request.getParameter("Order16U"));
	            resmap.put("OrderCall",request.getParameter("OrderCall"));
	            resmap.put("ActionTime",request.getParameter("ActionTime"));
	            resmap.put("Action",request.getParameter("Action"));
	        }else if(orderState=="1" || orderState=="7"){
	            //1-全部消费 7-部分消费
	            //订单核销通知（必接）{"VerifyCode":"0a332f709b3466db219c2d23d6cdb7e4","Order16U":"16435716","ActionTime":" 2019-05-22 16:23:26","OrderCall":"12201905221131112757972176896","Tnumber":1,"OrderState":7,"AllCh eckNum":1,"Source":3,"Action":1}
	            resmap.put("VerifyCode",request.getParameter("VerifyCode"));
	            resmap.put("Order16U",request.getParameter("Order16U"));
	            resmap.put("OrderCall",request.getParameter("OrderCall"));
	            resmap.put("ActionTime",request.getParameter("ActionTime"));
	            resmap.put("Tnumber", request.getParameter("Tnumber"));
	            resmap.put("AllCheckNum",request.getParameter("AllCheckNum"));
	            resmap.put("Source", request.getParameter("Source"));
	            resmap.put("Action",request.getParameter("Action"));
	        }else if(orderState=="8"){
	            //订单退票通知（必接）
	            //{"VerifyCode":"0a332f709b3466db219c2d23d6cdb7e4","Order16U":"16435728","ActionTime":" 2019-05-22 17:32:43","OrderCall":"12201905221131129923396177920","Tnumber":"2","OrderState":8,"Ref undtype":2,"Explain":"2","Source":2,"Action":3,"AllCheckNum":0,"RefundAmount":0,"RefundFee ":0,"RemoteSn":"16435728"}
	            resmap.put("VerifyCode",request.getParameter("VerifyCode"));
	            resmap.put("Action",request.getParameter("Order16U"));
	            resmap.put("Order16U",request.getParameter("OrderCall"));
	            resmap.put("ActionTime",request.getParameter("ActionTime"));
	            resmap.put("Tnumber",request.getParameter("Tnumber"));
	            resmap.put("Refundtype", request.getParameter("Refundtype"));

	            resmap.put("Explain",request.getParameter("Explain"));
	            resmap.put("AllCheckNum",request.getParameter("AllCheckNum"));
	            resmap.put("RefundAmount",request.getParameter("RefundAmount"));
	            resmap.put("RefundFee",request.getParameter("RefundFee"));
	            resmap.put("RemoteSn",request.getParameter("RemoteSn"));
	            resmap.put("Source",request.getParameter("Source"));
	            resmap.put("Action",request.getParameter("Action"));
	        }else if(orderState=="2"  ){
	            
	            if(request.getParameter("VerifyCode")==null){
	            	//2-产品上架 3-产品下架 4-产品价格变动 5-产品信息变动
		            //产品变更回调{"ActionTime":"2017-11-27 11:35:32","UUlid":59108,"VerifyCode":"8407143ed5aee4010eaa4be63701a2bb","UUtids":{"pid": 131685,"tid":134597},"OrderState":4}
		            resmap.put("VerifyCode",request.getParameter("VerifyCode"));
		            resmap.put("ActionTime",request.getParameter("ActionTime"));
		            resmap.put("UUtids",request.getParameter("UUtids"));
		            resmap.put("pid",request.getParameter("pid"));
		            resmap.put("tid",request.getParameter("tid"));
	            }else{
	            	 //三方消费码异步回调（指上游异步返码通知）
		            resmap.put("VerifyCode",request.getParameter("VerifyCode"));
		            resmap.put("pftOrder",request.getParameter("pftOrder"));
		            resmap.put("remoteOrder",request.getParameter("remoteOrder"));
		            resmap.put("code",request.getParameter("code"));
	            }
	        }else if(orderState=="3" || orderState=="4" || orderState=="5" ){
	        	 resmap.put("VerifyCode",request.getParameter("VerifyCode"));
		            resmap.put("ActionTime",request.getParameter("ActionTime"));
		            resmap.put("UUtids",request.getParameter("UUtids"));
		            resmap.put("pid",request.getParameter("pid"));
		            resmap.put("tid",request.getParameter("tid"));
            }
            
	        String res = JSON.toJSONString(resmap);
	        System.out.println(res);
	        return "success";
    }
	@ResponseBody
	@RequestMapping(value = "/callBackAction", method = RequestMethod.POST)//id为0时使用备源
    public String postCallBackAction(HttpServletRequest request){
		 try {
	            InputStreamReader insr = new InputStreamReader(request.getInputStream(),"utf-8");
	            String result = "";
	            int respInt = insr.read();
	            System.out.println(respInt);
	            while(respInt != -1){
	                result += (char)respInt;
	                respInt = insr.read();
	            }
	            System.out.println("result:" + result);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        return "200";
    }
}
