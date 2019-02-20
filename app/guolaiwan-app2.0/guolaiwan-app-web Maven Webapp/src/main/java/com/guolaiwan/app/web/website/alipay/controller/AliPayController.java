package com.guolaiwan.app.web.website.alipay.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
//import com.guolaiwan.app.web.enumeration.LoginCacheName;
//import com.guolaiwan.app.web.user.vo.UserVO;
//import com.guolaiwan.app.web.website.order.vo.OrderDetailVO;
//import com.guolaiwan.bussiness.admin.dao.SystemConfigDao;
//import com.guolaiwan.bussiness.admin.po.SystemConfigPO;
//import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
//import com.guolaiwan.bussiness.chapman.product.enumeration.ProductDetailType;
//import com.guolaiwan.bussiness.chapman.product.po.ProductDetailPO;
//import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
//import com.guolaiwan.bussiness.order.dao.OrderDAO;
//import com.guolaiwan.bussiness.order.dao.OrderDetailDAO;
//import com.guolaiwan.bussiness.order.dto.OrderDetailDTO;
//import com.guolaiwan.bussiness.order.enumeration.OrderStatusType;
//import com.guolaiwan.bussiness.order.po.OrderDetailPO;
//import com.guolaiwan.bussiness.order.po.OrderPO;
//import com.guolaiwan.bussiness.user.check.UserDBCheck;
//import com.guolaiwan.bussiness.user.dao.BasketDAO;
//import com.guolaiwan.bussiness.user.po.BasketPO;
//import com.guolaiwan.bussiness.user.po.UserPO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.controller.BaseController;
@Controller
@RequestMapping("/website/alipay")
public class AliPayController extends BaseController {

	@Autowired
	private OrderInfoDAO conn_orderInfo;
	//	
	@Autowired
	private SysConfigDAO conn_sysConfig;
	//下订单
	@ResponseBody
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public void pay(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
//		ModelAndView mv = new ModelAndView("");
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		String orderUuid = request.getParameter("orderUuid");
		OrderInfoPO order =  conn_orderInfo.get(orderUuid);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = order.getOrderNO();
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
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
	
		out.println(result);
		out.flush();
		out.close();
	}
	//下订单
		@ResponseBody
		@RequestMapping(value = "/pay/batch", method = RequestMethod.POST)
		public void payBatch(
				HttpServletRequest request,
				HttpServletResponse response,
				@RequestParam(required = false, value="orderUuids[]")List<String> orderUuids) throws Exception{
//			ModelAndView mv = new ModelAndView("");
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			
			List<OrderInfoPO> orders = conn_orderInfo.getAllByUuids(orderUuids);
			if(orders==null || orders.size()<=0){
				throw new Exception("数据异常，未获取到用户可下的订单信息！");
			}
			StringBufferWrapper idBuffer = new StringBufferWrapper();
			//计算总价
			long PayMoney = 0l;
			String bodystr = "";
			for(OrderInfoPO order:orders){
				PayMoney += order.getPayMoney();
				idBuffer.append(order.getId()).append("A");
				bodystr=bodystr+order.getProductName()+"*"+order.getProductNum()+"||";
			}
			
			//商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = idBuffer.toString();
			//付款金额，必填
			String total_amount = String.valueOf((double)PayMoney/100);
			//订单名称，必填
			String subject = "综合订单";
			//商品描述，可空
			String body = bodystr;
			
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
					+ "\"total_amount\":\""+ total_amount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"body\":\""+ body +"\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			
			//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
			//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
			//		+ "\"total_amount\":\""+ total_amount +"\"," 
			//		+ "\"subject\":\""+ subject +"\"," 
			//		+ "\"body\":\""+ body +"\"," 
			//		+ "\"timeout_express\":\"10m\"," 
			//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
			
			//请求
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
		
			out.println(result);
			out.flush();
			out.close();
		}

}
