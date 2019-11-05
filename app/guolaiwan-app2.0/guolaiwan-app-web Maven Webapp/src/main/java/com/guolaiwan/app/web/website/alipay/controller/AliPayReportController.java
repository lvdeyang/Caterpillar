package com.guolaiwan.app.web.website.alipay.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.internal.util.AlipaySignature;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.AliPayRecordDAO;
import com.guolaiwan.bussiness.admin.dao.BundleOrderDAO;
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
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.AliPayRecordPO;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
@Controller
@RequestMapping("/website/alireport")
public class AliPayReportController extends WebBaseControll {

	@Autowired
	private OrderInfoDAO conn_orderInfo;
	//	
	@Autowired
	private AliPayRecordDAO conn_aliPayRecord;
	
	@Autowired
	private ProductDAO conn_product;
	
	@Autowired
	private SysConfigDAO conn_sysConfig;

	//get请求
	@RequestMapping(value = "/reportR", method = RequestMethod.GET)
	public ModelAndView reportR(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		ModelAndView mv;
		Map<String,String> params = new HashMap<String,String>();
		Map<String, Object> strMap = new HashMap<String,Object>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		String info;
		OrderInfoPO order =null;
		String out_no="";
		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			out_no=out_trade_no;
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			
			info = "trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount;
			
			order = conn_orderInfo.get(Long.parseLong(out_trade_no));
		}else {
			info = "验签失败";
		}
		strMap.put("info",info);
		strMap.put("order", order);
		strMap.put("out_trade_no", out_no);
		mv =new ModelAndView("web/order/paySuccess",strMap);
		return mv;
	}

	@Autowired
	BundleOrderDAO conn_bundleorder;
	
	//post请求
	@ResponseBody
	@RequestMapping(value = "/reportN", method = RequestMethod.POST)
	public String reportN(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");(这行代码有毒，注释掉！)
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
		//——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		 */
		if(signVerified) {//验证成功
			System.out.println("支付宝验证成功！");
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			System.out.println("订单号："+out_trade_no);
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			
			String[] orderIds =  out_trade_no.split("A");
			if(out_trade_no.indexOf("bundle")!=-1){
				Long bundleOrderId=Long.parseLong(out_trade_no.split("-")[1]);
				BundleOrder bundleOrder=conn_bundleorder.get(bundleOrderId);
				orderIds=bundleOrder.getOrderStr().split("A");
			}
			
			
			int i=0;
			for (String orderId : orderIds) {
				i=i+1;
				//获取订单
				OrderInfoPO order = conn_orderInfo.get(Long.parseLong(orderId));
				if(order==null){
					System.out.println("订单是空：订单号："+out_trade_no);
				}
				
				if(trade_status.equals("TRADE_FINISHED")){
					//修改订单状态
					order.setOrderState(OrderStateType.PAYSUCCESS);
					System.out.println("订单状态："+trade_status);
				}else if (trade_status.equals("TRADE_SUCCESS")){
					System.out.println("订单状态："+trade_status);
					if(order.getOrderState().toString().equals("NOTPAY")){//防止支付宝一直发post,一直减库存
						//修改订单状态、减库存
						order.setOrderState(OrderStateType.PAYSUCCESS);
						order.setPayMode(PayType.ALIPAY);
						order.setPayDate(new Date());
						long productNum = order.getProductNum();
						
						//生成验单码,和二维码图片
						String ydNO = ydNoCode(orderId);
						order.setYdNO(ydNO);
						
						ProductPO product = conn_product.get(order.getProductId());
						product.setProductStock(product.getProductStock()-productNum);
						conn_product.saveOrUpdate(product);
						conn_orderInfo.saveOrUpdate(order);
					
						//生成一条支付记录
						AliPayRecordPO aliPayRecord = new AliPayRecordPO();
						aliPayRecord.setOrderNO(out_trade_no);
						aliPayRecord.setTradeNo(trade_no);
						aliPayRecord.setOrderId(order.getId());
						conn_aliPayRecord.save(aliPayRecord);
					}
					
				}
				
			}
			
			System.out.println("支付成功:"+i+"个订单。订单号："+out_trade_no);
			return "success";
			
		}else {//验证失败
			System.out.println("支付失败fail");

				//调试用，写文本函数记录程序运行情况是否正常
				//String sWord = AlipaySignature.getSignCheckContentV1(params);
				//AlipayConfig.logResult(sWord);
			return "error";
		}


	}
}
