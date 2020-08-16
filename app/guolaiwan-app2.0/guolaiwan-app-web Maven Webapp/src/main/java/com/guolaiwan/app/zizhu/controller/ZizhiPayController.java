package com.guolaiwan.app.zizhu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.guolaiwan.app.web.Guide.controller.integralControll;
import com.guolaiwan.app.web.website.alipay.controller.AlipayConfig;
import com.guolaiwan.app.zizhu.GlwHttpUtils;
import com.guolaiwan.app.zizhu.bean.PayCodeVo;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
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
		PayCodeVo vo=new PayCodeVo();
		Map payData=new HashMap<String,Object>();
		if (request!=null) {
			payData=GlwHttpUtils.getPayCode(request);
		}
		if (!payData.isEmpty()) {
			
			try {
				String urlString=wxpay(Long.parseLong(request.getParameter("trade_no").toString()));
				vo.setCode("SUCCESS");
				vo.setMsg("获取成功");
				
				vo.setMchid(WXContants.MchId);
				vo.setAppid(WXContants.AppId);
				vo.setQrcode_url(urlString);
				vo.setTrans_status((String)payData.get("TRADING"));
				vo.setTrans_time((DateTime)payData.get("trans_time"));
				vo.setMemo((String)payData.get("memo"));
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				vo.setCode("FAIL");
				vo.setMsg("获取失败");
			}
			
		}else{
		
			vo.setCode("FAIL");
			vo.setMsg("获取失败");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 获取支付宝支付二维码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/alipay/scanPay", method = RequestMethod.GET)
    public String payCodeAli(HttpServletRequest request){
		PayCodeVo vo=new PayCodeVo();
		Map payData=new HashMap<String,Object>();
		if (request!=null) {
			payData=GlwHttpUtils.getPayCode(request);
		}
		if (!payData.isEmpty()) {
			String urlString;
			try {
				urlString = alipay(Long.parseLong(request.getParameter("trade_no").toString()));
				vo.setMchid(AlipayConfig.merchant_private_key);
				vo.setAppid(AlipayConfig.app_id);
				vo.setQrcode_url(urlString);
				vo.setTrans_status((String)payData.get("TRADING"));
				vo.setTrans_time((DateTime)payData.get("trans_time"));
				vo.setMemo((String)payData.get("memo"));
				vo.setCode("SUCCESS");
				vo.setMsg("支付成功");
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				vo.setCode("FAIL");
				vo.setMsg("支付失败");
			}
		
		
		}else{
			vo.setCode("FAIL");
			vo.setMsg("支付失败");
		}
		return JSONObject.toJSONString(vo);
    }

	@Autowired
	SysConfigDAO conn_sysconfig;
	@Autowired
	OrderInfoDAO conn_order;
	public String wxpay(Long orderid) throws Exception {

		OrderInfoPO order = conn_order.get(orderid);
		if (order == null) {
			throw new Exception("数据异常，未获取到用户可下的订单信息！");
		}
		// //存支付方式
		// order.setPayMode(PayType.WEICHAT);

		// 计算总价
		long PayMoney = order.getPayMoney();
		String result="";
	
		try {

			GuolaiwanWxPay wxPay = GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/website/wxreport/payreport");

			Map<String, String> reqData = new HashMap<String, String>();

			String tradeNum = order.getId().toString();// 订单编号UUID.randomUUID().toString().replace("-",
														// "")

			reqData.put("device_info", "WEB");

			reqData.put("total_fee", String.valueOf((int) PayMoney));

			reqData.put("attach", "test");

			reqData.put("body", "过来玩");

			reqData.put("out_trade_no", tradeNum);

			reqData.put("spbill_create_ip", "192.165.56.64");

			reqData.put("trade_type", "NATIVE");

			Map<String, String> resData = wxPay.pay(reqData); // 生成二维码数据

			SysConfigPO sysConfig = conn_sysconfig.getSysConfig();

			String fileName = order.getId() + DateUtil.getCurrentDateTime() + ".png";

			String path = sysConfig.getFolderUrl() + File.separator + "wxPayCode" + File.separator + fileName;

			String content = resData.get("code_url");

			QRCodeGenerator.generate(path, content); // 本地路径下生成二维码
			//
			// order表中存二维码的本地路径
			// for (BasketPO basketPO : baskets) {
			// basketPO.setTradeNum(tradeNum);
			// basketPO.setCodeFilePath(path);
			// }
			//
			// conn_basket.saveAll(baskets);
			File file=new File(path);
			
			OSSUtils.createFolder("glw-old-file", "file/wxPayCode/");
			OSSUtils.uploadObjectOSS("file/wxPayCode/", fileName,file, new FileInputStream(file));

			result=sysConfig.getWebUrl() + "/wxPayCode/" + fileName;

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
				object.put("code", "FAIL");
				object.put("msg", "支付中");
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
				object.put("code", "FAIL");
				object.put("msg", "支付中");
			}
		}
		return object.toJSONString();
	}
	
}
