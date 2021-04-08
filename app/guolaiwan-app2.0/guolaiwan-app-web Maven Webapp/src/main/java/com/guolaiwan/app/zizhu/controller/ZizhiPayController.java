package com.guolaiwan.app.zizhu.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.guolaiwan.app.web.Guide.controller.integralControll;
import com.guolaiwan.app.web.website.alipay.controller.AlipayConfig;
import com.guolaiwan.app.zizhu.GlwHttpUtils;
import com.guolaiwan.app.zizhu.bean.PayCodeVo;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.admin.dao.BundleOrderDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import cn.hutool.core.date.DateTime;
import pub.caterpillar.commons.file.oss.OSSUtils;
import pub.caterpillar.commons.qrcode.QRCodeGenerator;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPay;
import pub.caterpillar.weixin.wxpay.WXPayConstants;

@Controller
public class ZizhiPayController {
	/**
	 * 获取微信支付二维码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wxpay/scanPay", method = RequestMethod.POST)
    public String payCodeWx(HttpServletRequest request){
		JSONObject responseJsonObject=new JSONObject();
		JSONObject dataJsonObject=new JSONObject();
		String json=getRequestJson(request);
		JSONObject pageObject = JSON.parseObject(json);
		String urlString;
		try {
			urlString = wxpay(pageObject.getString("trade_no"));
			String orderid=pageObject.getString("trade_no").toString();
			String ordertradeNum="";
			long allMoney=0l;
	        if(orderid.indexOf("bundle")!=-1){
	        	ordertradeNum=orderid;
	        	String[] ids=orderid.split("-");
	        	BundleOrder bundleOrder=conn_bundleorder.get(Long.parseLong(ids[1]));
	        	String[] orderIds=bundleOrder.getOrderStr().split("A");
	        	
	        	for (String idStr : orderIds) {
					OrderInfoPO orderInfoPO=conn_order.get(Long.parseLong(idStr));
					allMoney+=orderInfoPO.getPayMoney();
				}
	        }else{
	        	OrderInfoPO orderInfoPO=conn_order.get(Long.parseLong(orderid));
				allMoney+=orderInfoPO.getPayMoney();
				ordertradeNum=orderid;
	        }
			
			dataJsonObject.put("mchid", AlipayConfig.merchant_private_key);
			dataJsonObject.put("appid",AlipayConfig.app_id);
			dataJsonObject.put("qrcode_url",urlString);
			dataJsonObject.put("qrcode_data",urlString);
			dataJsonObject.put("trans_time",new DateTime());
			dataJsonObject.put("trans_status","SUCCESS");
			dataJsonObject.put("trans_no",pageObject.getString("trade_no"));
			dataJsonObject.put("trade_no",pageObject.getString("trade_no"));
			dataJsonObject.put("referNo",pageObject.getString("trade_no"));
			dataJsonObject.put("amount", allMoney);
			dataJsonObject.put("memo","");
			responseJsonObject.put("code", "SUCCESS");
			responseJsonObject.put("msg", "二维码获取成功");
			responseJsonObject.put("trans_no", pageObject.getString("trade_no"));
			responseJsonObject.put("data", dataJsonObject);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataJsonObject.put("mchid", AlipayConfig.merchant_private_key);
			dataJsonObject.put("appid",AlipayConfig.app_id);
			dataJsonObject.put("qrcode_url","");
			dataJsonObject.put("qrcode_data","");
			dataJsonObject.put("trans_time",new DateTime());
			dataJsonObject.put("trans_status","FAIL");
			dataJsonObject.put("trans_no","");
			dataJsonObject.put("trade_no","");
			dataJsonObject.put("referNo","");
			dataJsonObject.put("memo","");
			responseJsonObject.put("code", "FAIL");
			responseJsonObject.put("msg", "二维码获取失败");
			responseJsonObject.put("trans_no", "");
			responseJsonObject.put("data", dataJsonObject);
		}
		return responseJsonObject.toJSONString();
    }
	
	/**
	 * 获取支付宝支付二维码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/alipay/scanPay", method = RequestMethod.GET)
    public String payCodeAli(HttpServletRequest request){
		JSONObject responseJsonObject=new JSONObject();
		JSONObject dataJsonObject=new JSONObject();
		String json=getRequestJson(request);
		JSONObject pageObject = JSON.parseObject(json);
		String urlString;
		try {
			urlString = alipay(Long.parseLong(pageObject.getString("trade_no").toString()));
			OrderInfoPO orderInfoPO=conn_order.get(Long.parseLong(pageObject.getString("trade_no").toString()));
			dataJsonObject.put("mchid", AlipayConfig.merchant_private_key);
			dataJsonObject.put("appid",AlipayConfig.app_id);
			dataJsonObject.put("qrcode_url",urlString);
			dataJsonObject.put("qrcode_data",urlString);
			dataJsonObject.put("trans_time",new DateTime());
			dataJsonObject.put("trans_status","SUCCESS");
			dataJsonObject.put("trans_no",pageObject.getString("trade_no"));
			dataJsonObject.put("trade_no",pageObject.getString("trade_no"));
			dataJsonObject.put("referNo",pageObject.getString("trade_no"));
			dataJsonObject.put("amount", orderInfoPO.getPayMoney());
			dataJsonObject.put("memo","");
			responseJsonObject.put("code", "SUCCESS");
			responseJsonObject.put("msg", "二维码获取成功");
			responseJsonObject.put("trans_no", pageObject.getString("trade_no"));
			responseJsonObject.put("data", dataJsonObject);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataJsonObject.put("mchid", AlipayConfig.merchant_private_key);
			dataJsonObject.put("appid",AlipayConfig.app_id);
			dataJsonObject.put("qrcode_url","");
			dataJsonObject.put("qrcode_data","");
			dataJsonObject.put("trans_time",new DateTime());
			dataJsonObject.put("trans_status","FAIL");
			dataJsonObject.put("trans_no","");
			dataJsonObject.put("trade_no","");
			dataJsonObject.put("referNo","");
			dataJsonObject.put("memo","");
			responseJsonObject.put("code", "FAIL");
			responseJsonObject.put("msg", "二维码获取失败");
			responseJsonObject.put("trans_no", "");
			responseJsonObject.put("data", dataJsonObject);
		}
		return responseJsonObject.toJSONString();
    }

	@Autowired
	SysConfigDAO conn_sysconfig;
	@Autowired
	OrderInfoDAO conn_order;
	
	@Autowired
	BundleOrderDAO conn_bundleorder;
	public String wxpay(String orderid) throws Exception {

		//OrderInfoPO order = conn_order.get(orderid);
		//if (order == null) {
		//	throw new Exception("数据异常，未获取到用户可下的订单信息！");
		//}
		// //存支付方式
		// order.setPayMode(PayType.WEICHAT);
		String ordertradeNum="";
		long allMoney=0l;
        if(orderid.indexOf("bundle")!=-1){
        	ordertradeNum=orderid;
        	String[] ids=orderid.split("-");
        	BundleOrder bundleOrder=conn_bundleorder.get(Long.parseLong(ids[1]));
        	String[] orderIds=bundleOrder.getOrderStr().split("A");
        	
        	for (String idStr : orderIds) {
				OrderInfoPO orderInfoPO=conn_order.get(Long.parseLong(idStr));
				allMoney+=orderInfoPO.getPayMoney();
			}
        }else{
        	OrderInfoPO orderInfoPO=conn_order.get(Long.parseLong(orderid));
			allMoney+=orderInfoPO.getPayMoney();
			ordertradeNum=orderid;
        }
		
		// 计算总价
	
		String result="";
	
		try {

			GuolaiwanWxPay wxPay = GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/website/wxreport/payreport");

			Map<String, String> reqData = new HashMap<String, String>();

			reqData.put("device_info", "WEB");

			reqData.put("total_fee", String.valueOf((int) allMoney));

			reqData.put("attach", "test");

			reqData.put("body", "过来玩");

			reqData.put("out_trade_no", ordertradeNum);

			reqData.put("spbill_create_ip", "192.165.56.64");

			reqData.put("trade_type", "NATIVE");

			Map<String, String> resData = wxPay.pay(reqData); // 生成二维码数据

			SysConfigPO sysConfig = conn_sysconfig.getSysConfig();

			String fileName = ordertradeNum + DateUtil.getCurrentDateTime() + ".png";

			String path = sysConfig.getFolderUrl() + File.separator + "wxPayCode" + File.separator + fileName;

			String content = resData.get("code_url");

			//QRCodeGenerator.generate(path, content); // 本地路径下生成二维码
			//
			// order表中存二维码的本地路径
			// for (BasketPO basketPO : baskets) {
			// basketPO.setTradeNum(tradeNum);
			// basketPO.setCodeFilePath(path);
			// }
			//
			// conn_basket.saveAll(baskets);
			//File file=new File(path);
			
			//OSSUtils.createFolder("glw-old-file", "file/wxPayCode/");
			//OSSUtils.uploadObjectOSS("file/wxPayCode/", fileName,file, new FileInputStream(file));

			//result=sysConfig.getWebUrl() + "/wxPayCode/" + fileName;
			result=content;

			//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	public String alipay(Long orderid
			) throws Exception{
//		ModelAndView mv = new ModelAndView("");
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		OrderInfoPO order =  conn_order.get(orderid);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = order.getId()+"";
		//付款金额，必填
		String total_amount = String.valueOf((double)order.getPayMoney()/100);
		//订单名称，必填
		String subject = order.getProductName();
		//商品描述，可空
		String body = order.getShopName()+order.getProductName();
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		return result;
	}
	
	/**
	 * 查询支付宝支付状态
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/alipay/query", method = RequestMethod.GET)
    public String QuaryAli(HttpServletRequest request){
		JSONObject object=new JSONObject();
		if (request!=null) {
			String orderIdString=request.getParameter("trade_no");
			OrderInfoPO orderInfoPO=conn_order.get(Long.parseLong(orderIdString));
			if(orderInfoPO.getOrderState().equals(OrderStateType.PAYSUCCESS)){
				object.put("code", "SUCCESS");
				object.put("msg", "支付成功");
				object.put("mchid", orderInfoPO.getRoomId()+"");
				object.put("appid", WXContants.AppId);
				object.put("amount", orderInfoPO.getOrderAllMoney());
				object.put("trans_no", orderInfoPO.getId());
				object.put("trans_status", "SUCCESS");
				object.put("trans_type", "00");
				object.put("trans_time", DateUtil.format(new Date(),DateUtil.dateTimePattern));
				object.put("trade_no", orderInfoPO.getId()+"");
				object.put("refer_no",orderInfoPO.getId()+"");
				object.put("option_type","02");
				object.put("memo","");
			}else{
				object.put("code", "SUCCESS");
				object.put("msg", "支付成功");
				object.put("mchid", orderInfoPO.getRoomId()+"");
				object.put("appid", WXContants.AppId);
				object.put("amount", orderInfoPO.getOrderAllMoney());
				object.put("trans_no", orderInfoPO.getId());
				object.put("trans_status", "TRADING");
				object.put("trans_type", "00");
				object.put("trans_time", DateUtil.format(new Date(),DateUtil.dateTimePattern));
				object.put("trade_no", orderInfoPO.getId()+"");
				object.put("refer_no",orderInfoPO.getId()+"");
				object.put("option_type","02");
				object.put("memo","");
			}
		}
		return object.toJSONString();
	}
	
	/**
	 * 查询微信支付状态
	 * @param request
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/wxpay/query", method = RequestMethod.GET)
    public String QuaryWx(HttpServletRequest request){
		Map<String,Object> response=new HashMap<String, Object>();
		Map<String,Object> data=new HashMap<String, Object>();
		Map<String,Object> error=new HashMap<String, Object>();
		OrderInfoPO orderInfoPO=null;
		if (request!=null) {
			String orderIdString=request.getParameter("trade_no");
			
			String orderid=orderIdString;
			long allMoney=0l;
	        if(orderid.indexOf("bundle")!=-1){
	        	String[] ids=orderid.split("-");
	        	BundleOrder bundleOrder=conn_bundleorder.get(Long.parseLong(ids[1]));
	        	String[] orderIds=bundleOrder.getOrderStr().split("A");
	    
	        	
	        	for (String idStr : orderIds) {
					orderInfoPO=conn_order.get(Long.parseLong(idStr));
					allMoney+=orderInfoPO.getPayMoney();
					
				}
	        }else{
	        	orderInfoPO=conn_order.get(Long.parseLong(orderid));
				allMoney+=orderInfoPO.getPayMoney();
	        }
			
			
			
			if(orderInfoPO.getOrderState().equals(OrderStateType.PAYSUCCESS)){
				data.put("mchid", orderInfoPO.getRoomId()+"");
				data.put("appid", WXContants.AppId);
				data.put("amount", allMoney);
				data.put("trans_no",orderIdString);
				data.put("trans_status", "SUCCESS");
				data.put("trans_type", "00");
				data.put("trans_time", DateUtil.format(new Date(),DateUtil.dateTimePattern));
				data.put("trade_no", orderInfoPO.getId()+"");
				data.put("refer_no",orderInfoPO.getId()+"");
				data.put("option_type","02");
				data.put("memo","");
				error.put("code", 0);
				error.put("message", "success");
				response.put("error", error);
				response.put("data", data);
			}else{
				data.put("mchid", orderInfoPO.getRoomId()+"");
				data.put("appid", WXContants.AppId);
				data.put("amount", allMoney);
				data.put("trans_no",orderIdString);
				data.put("trans_status", "TRADING");
				data.put("trans_type", "00");
				data.put("trans_time", DateUtil.format(new Date(),DateUtil.dateTimePattern));
				data.put("trade_no", orderInfoPO.getId()+"");
				data.put("refer_no",orderInfoPO.getId()+"");
				data.put("option_type","02");
				data.put("memo","");
				error.put("code", 0);
				error.put("message", "success");
				response.put("error", error);
				response.put("data", data);
			}
		}
		return JSONObject.toJSONString(response,SerializerFeature.WriteMapNullValue);  
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
