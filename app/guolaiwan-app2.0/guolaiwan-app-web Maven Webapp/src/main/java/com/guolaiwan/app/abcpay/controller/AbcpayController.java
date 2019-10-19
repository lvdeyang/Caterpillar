package com.guolaiwan.app.abcpay.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.pay.client.Constants;
import com.abc.pay.client.JSON;
import com.abc.pay.client.ebus.PaymentRequest;
import com.abc.pay.client.ebus.PaymentResult;
import com.abc.pay.client.ebus.RefundRequest;
import com.guolaiwan.app.abcpay.PaymentRequestBo;
import com.guolaiwan.app.abcpay.RefundRequestBo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;


@Controller
@RequestMapping("/abcpay")
public class AbcpayController extends WebBaseControll {
	
	/**
	 * 农行对接支付
	 * 地址定义为http://www.guolaiwan.net/abcpay/MerchantPayment
	 * @param request
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/MerchantPayment")
	public Object MerchantPayment(HttpServletRequest request, HttpServletResponse response, PaymentRequestBo tPaymentRequestBo) 
			throws Exception{
		
		request.setCharacterEncoding("GBK");
		response.setHeader("Cache-Control", "no-cache");
		
		//1、生成订单对象
		PaymentRequest tPaymentRequest = new PaymentRequest();
		tPaymentRequest.dicOrder.put("PayTypeID", tPaymentRequestBo.getPayTypeID());//设定交易类型
		tPaymentRequest.dicOrder.put("OrderDate", tPaymentRequestBo.getOrderDate());//设定订单日期 （必要信息 - YYYY/MM/DD）
		tPaymentRequest.dicOrder.put("OrderTime", tPaymentRequestBo.getOrderTime());//设定订单时间 （必要信息 - HH:MM:SS）
		tPaymentRequest.dicOrder.put("orderTimeoutDate", tPaymentRequestBo.getOrderTimeoutDate());//设定订单有效期
		tPaymentRequest.dicOrder.put("OrderNo", tPaymentRequestBo.getOrderNo());//设定订单编号 （必要信息）
		tPaymentRequest.dicOrder.put("CurrencyCode", tPaymentRequestBo.getCurrencyCode());//设定交易币种
		tPaymentRequest.dicOrder.put("OrderAmount", tPaymentRequestBo.getOrderAmount());//设定交易金额
		tPaymentRequest.dicOrder.put("Fee", tPaymentRequestBo.getFee());//设定手续费金额
		tPaymentRequest.dicOrder.put("AccountNo", tPaymentRequestBo.getAccountNo());//设定支付账户
		tPaymentRequest.dicOrder.put("OrderDesc", tPaymentRequestBo.getOrderDesc());//设定订单说明
		tPaymentRequest.dicOrder.put("OrderURL", tPaymentRequestBo.getOrderURL());//设定订单地址
		tPaymentRequest.dicOrder.put("ReceiverAddress", tPaymentRequestBo.getReceiverAddress());//收货地址
		tPaymentRequest.dicOrder.put("InstallmentMark", tPaymentRequestBo.getInstallmentMark());//分期标识
		if (tPaymentRequestBo.getInstallmentMark() == "1" && tPaymentRequestBo.getPayTypeID() == "DividedPay")
		{
		    tPaymentRequest.dicOrder.put("InstallmentCode", request.getParameter("InstallmentCode"));//设定分期代码
		    tPaymentRequest.dicOrder.put("InstallmentNum", request.getParameter("InstallmentNum"));//设定分期期数
		}
		tPaymentRequest.dicOrder.put("CommodityType", tPaymentRequestBo.getCommodityType());//设置商品种类
		tPaymentRequest.dicOrder.put("BuyIP", tPaymentRequestBo.getBuyIP());//IP
		tPaymentRequest.dicOrder.put("ExpiredDate", tPaymentRequestBo.getExpiredDate());//设定订单保存时间

		//3、生成支付请求对象
		String paymentType = tPaymentRequestBo.getPaymentType();
		tPaymentRequest.dicRequest.put("PaymentType", paymentType);//设定支付类型
		String paymentLinkType  = tPaymentRequestBo.getPaymentLinkType();                                         
		tPaymentRequest.dicRequest.put("PaymentLinkType", paymentLinkType);//设定支付接入方式
		if (paymentType.equals(Constants.PAY_TYPE_UCBP) && paymentLinkType.equals(Constants.PAY_LINK_TYPE_MOBILE))
		{
		    tPaymentRequest.dicRequest.put("UnionPayLinkType",request.getParameter("UnionPayLinkType"));//当支付类型为6，支付接入方式为2的条件满足时，需要设置银联跨行移动支付接入方式
		}
		tPaymentRequest.dicRequest.put("ReceiveAccount", tPaymentRequestBo.getReceiveAccount());//设定收款方账号
		tPaymentRequest.dicRequest.put("ReceiveAccName", tPaymentRequestBo.getReceiveAccName());//设定收款方户名
		tPaymentRequest.dicRequest.put("NotifyType", tPaymentRequestBo.getNotifyType());//设定通知方式
		tPaymentRequest.dicRequest.put("ResultNotifyURL", tPaymentRequestBo.getResultNotifyURL());//设定通知URL地址
		tPaymentRequest.dicRequest.put("MerchantRemarks", tPaymentRequestBo.getMerchantRemarks());//设定附言
		tPaymentRequest.dicRequest.put("ReceiveMark", tPaymentRequestBo.getReceiveMark());//交易是否直接入二级商户账户
		tPaymentRequest.dicRequest.put("ReceiveMerchantType", tPaymentRequestBo.getReceiveMerchantType());//设定收款方账户类型
		tPaymentRequest.dicRequest.put("IsBreakAccount", tPaymentRequestBo.getIsBreakAccount());//设定交易是否分账、交易是否支持向二级商户入账
		tPaymentRequest.dicRequest.put("SplitAccTemplate", tPaymentRequestBo.getSplitAccTemplate());//分账模版编号        

		JSON json = tPaymentRequest.postRequest();

		String ReturnCode = json.GetKeyValue("ReturnCode");
		String ErrorMessage = json.GetKeyValue("ErrorMessage");
		
		Map<String, Object> ret = new HashMap<String, Object>();
		
		if (ReturnCode.equals("0000"))
		{
			ret.put("status", "success");
			System.out.println("ReturnCode   = [" + ReturnCode + "]<br/>");
			System.out.println("ErrorMessage = [" + ErrorMessage + "]<br/>");
			System.out.println("PaymentURL-->"+json.GetKeyValue("PaymentURL"));
			response.sendRedirect(json.GetKeyValue("PaymentURL"));
		}
		else
		{
			ret.put("status", "failed");
			ret.put("code", ReturnCode);
			ret.put("message", ErrorMessage);
			System.out.println("ReturnCode   = [" + ReturnCode + "]");
			System.out.println("ErrorMessage   = [" + ErrorMessage + "]");
		}
		
		return ret;
	}
	
	/**
	 * 农行对接支付结果
	 * 地址定义为http://www.guolaiwan.net/abcpay/MerchantResult
	 * @param request
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/MerchantResult")
	public Object MerchantResult(HttpServletRequest request) throws Exception{
		
		//1、取得MSG参数，并利用此参数值生成支付结果对象
		String msg = request.getParameter("MSG");
		PaymentResult tResult = new PaymentResult(msg);

		Map<String, Object> ret = new HashMap<String, Object>();
		
		//2、判断支付结果状态，进行后续操作
		if (tResult.isSuccess()) {
			//3、支付成功并且验签、解析成功
			System.out.println("TrxType         = [" + tResult.getValue("TrxType"        ) + "]<br>");
			System.out.println("OrderNo         = [" + tResult.getValue("OrderNo"        ) + "]<br>");//订单号
			System.out.println("Amount          = [" + tResult.getValue("Amount"         ) + "]<br>");//订单金额
			System.out.println("BatchNo         = [" + tResult.getValue("BatchNo"        ) + "]<br>");//交易批次号
			System.out.println("VoucherNo       = [" + tResult.getValue("VoucherNo"      ) + "]<br>");//交易凭证号
			System.out.println("HostDate        = [" + tResult.getValue("HostDate"       ) + "]<br>");//银行交易日期
			System.out.println("HostTime        = [" + tResult.getValue("HostTime"       ) + "]<br>");//银行交易时间
			System.out.println("MerchantRemarks = [" + tResult.getValue("MerchantRemarks") + "]<br>");//商户备注信息
			System.out.println("PayType         = [" + tResult.getValue("PayType"        ) + "]<br>");//消费者支付方式
			System.out.println("NotifyType      = [" + tResult.getValue("NotifyType"     ) + "]<br>");//支付结果通知方式
			System.out.println("TrnxNo          = [" + tResult.getValue("iRspRef"        ) + "]<br>");//银行返回交易流水号
			System.out.println("BankType        = [" + tResult.getValue("bank_type"      ) + "]<br>");//付款银行(微信)
			System.out.println("ThirdOrderNo    = [" + tResult.getValue("ThirdOrderNo"   ) + "]<br>");//第三方订单号(微信和支付宝)
			
			ret.put("status", "success");
		}
		else {
			//4、支付成功但是由于验签或者解析报文等操作失败
			System.out.println("ReturnCode   = [" + tResult.getReturnCode  () + "]<br>");
			System.out.println("ErrorMessage = [" + tResult.getErrorMessage() + "]<br>");

			ret.put("status", "failed");
			ret.put("code", tResult.getReturnCode  ());
			ret.put("message", tResult.getErrorMessage());
		}
		
		return ret;
	}
	
	/**
	 * 农行对接退款及退款结果
	 * 地址定义为http://www.guolaiwan.net/abcpay/MerchantRefund
	 * @param request
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/MerchantRefund")
	public Object MerchantRefund(HttpServletRequest request, HttpServletResponse response, RefundRequestBo tRefundRequestBo) 
			throws Exception{
		
		request.setCharacterEncoding("gb2312");
		response.setHeader("Cache-Control", "no-cache");
		
		//1、生成退款请求对象
        RefundRequest tefundRequest = new RefundRequest();
        tefundRequest.dicRequest.put("OrderDate", tRefundRequestBo.getOrderDate());//订单日期（必要信息）
        tefundRequest.dicRequest.put("OrderTime", tRefundRequestBo.getOrderTime());//订单时间（必要信息）
        tefundRequest.dicRequest.put("OrderNo", tRefundRequestBo.getOrderNo()); //原交易编号（必要信息）
        tefundRequest.dicRequest.put("NewOrderNo", tRefundRequestBo.getNewOrderNo());//交易编号（必要信息）
        tefundRequest.dicRequest.put("CurrencyCode", tRefundRequestBo.getCurrencyCode());//交易币种（必要信息）
        tefundRequest.dicRequest.put("TrxAmount", tRefundRequestBo.getTrxAmount());//退货金额 （必要信息）
        tefundRequest.dicRequest.put("RefundType", tRefundRequestBo.getRefundType());//退货类型 （非必要信息）              
        tefundRequest.dicRequest.put("MerchantRemarks", tRefundRequestBo.getMerchantRemarks());//附言

        //3、传送退款请求并取得退货结果
        JSON json = tefundRequest.postRequest();

        //4、判断退款结果状态，进行后续操作
        StringBuilder strMessage = new StringBuilder("");
        String ReturnCode = json.GetKeyValue("ReturnCode");
        String ErrorMessage = json.GetKeyValue("ErrorMessage");
        
        Map<String, Object> ret = new HashMap<String, Object>();
        
        if (ReturnCode.equals("0000"))
        {
            //5、退款成功/退款受理成功
        	System.out.println("ReturnCode   = [" + ReturnCode + "]<br/>");
        	System.out.println("ErrorMessage = [" + ErrorMessage + "]<br/>");
        	System.out.println("OrderNo   = [" + json.GetKeyValue("OrderNo") + "]<br/>");
        	System.out.println("NewOrderNo   = [" + json.GetKeyValue("NewOrderNo") + "]<br/>");
        	System.out.println("TrxAmount = [" + json.GetKeyValue("TrxAmount") + "]<br/>");
        	System.out.println("BatchNo   = [" + json.GetKeyValue("BatchNo") + "]<br/>");
        	System.out.println("VoucherNo = [" + json.GetKeyValue("VoucherNo") + "]<br/>");
        	System.out.println("HostDate  = [" + json.GetKeyValue("HostDate") + "]<br/>");
        	System.out.println("HostTime  = [" + json.GetKeyValue("HostTime") + "]<br/>");
        	System.out.println("iRspRef  = [" + json.GetKeyValue("iRspRef") + "]<br/>");

        	ret.put("status", "success");
        }       
        else
        {
            //6、退款失败
        	System.out.println("ReturnCode   = [" + ReturnCode + "]<br>");
        	System.out.println("ErrorMessage = [" + ErrorMessage + "]<br>");

        	ret.put("status", "failed");
			ret.put("code", ReturnCode);
			ret.put("message", ErrorMessage);
		}
        
        return ret;
	}
	
	
	
	
	/**
	 * 农行对接支付（备用）
	 * demo中MerchantPayment.jsp代码
	 * @param request
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/MerchantPaymentDemo")
	public void MerchantPaymentDemo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("GBK");
		response.setHeader("Cache-Control", "no-cache");
		
		//1、生成订单对象
		PaymentRequest tPaymentRequest = new PaymentRequest();
		tPaymentRequest.dicOrder.put("PayTypeID", request.getParameter("PayTypeID"));                   //设定交易类型
		tPaymentRequest.dicOrder.put("OrderDate", request.getParameter("OrderDate") );                  //设定订单日期 （必要信息 - YYYY/MM/DD）
		tPaymentRequest.dicOrder.put("OrderTime", request.getParameter("OrderTime"));                   //设定订单时间 （必要信息 - HH:MM:SS）
		tPaymentRequest.dicOrder.put("orderTimeoutDate", request.getParameter("orderTimeoutDate"));     //设定订单有效期
		tPaymentRequest.dicOrder.put("OrderNo", request.getParameter("OrderNo"));                       //设定订单编号 （必要信息）
		tPaymentRequest.dicOrder.put("CurrencyCode", request.getParameter("CurrencyCode"));             //设定交易币种
		tPaymentRequest.dicOrder.put("OrderAmount", request.getParameter("PaymentRequestAmount"));      //设定交易金额
		tPaymentRequest.dicOrder.put("Fee", request.getParameter("Fee"));                               //设定手续费金额
		tPaymentRequest.dicOrder.put("AccountNo", request.getParameter("AccountNo"));                   //设定支付账户
		tPaymentRequest.dicOrder.put("OrderDesc", request.getParameter("OrderDesc"));                   //设定订单说明
		tPaymentRequest.dicOrder.put("OrderURL", request.getParameter("OrderURL"));                     //设定订单地址
		tPaymentRequest.dicOrder.put("ReceiverAddress", request.getParameter("ReceiverAddress"));       //收货地址
		tPaymentRequest.dicOrder.put("InstallmentMark", request.getParameter("InstallmentMark"));       //分期标识
		if (request.getParameter("InstallmentMark") == "1" && request.getParameter("PayTypeID") == "DividedPay")
		{
		    tPaymentRequest.dicOrder.put("InstallmentCode", request.getParameter("InstallmentCode"));   //设定分期代码
		    tPaymentRequest.dicOrder.put("InstallmentNum", request.getParameter("InstallmentNum"));     //设定分期期数
		}
		tPaymentRequest.dicOrder.put("CommodityType", request.getParameter("CommodityType"));           //设置商品种类
		tPaymentRequest.dicOrder.put("BuyIP", request.getParameter("BuyIP"));                           //IP
		tPaymentRequest.dicOrder.put("ExpiredDate", request.getParameter("ExpiredDate"));               //设定订单保存时间

		//2、订单明细
		LinkedHashMap orderitem = new LinkedHashMap();
		orderitem.put("SubMerName", "测试二级商户1");    //设定二级商户名称
		orderitem.put("SubMerId", "12345");    //设定二级商户代码
		orderitem.put("SubMerMCC", "0000");   //设定二级商户MCC码 
		orderitem.put("SubMerchantRemarks", "测试");   //二级商户备注项
		orderitem.put("ProductID", "IP000001");//商品代码，预留字段
		orderitem.put("ProductName", "中国移动IP卡");//商品名称
		orderitem.put("UnitPrice", "1.00");//商品总价
		orderitem.put("Qty", "1");//商品数量
		orderitem.put("ProductRemarks", "测试商品"); //商品备注项
		orderitem.put("ProductType", "充值类");//商品类型
		orderitem.put("ProductDiscount", "0.9");//商品折扣
		orderitem.put("ProductExpiredDate", "10");//商品有效期
		tPaymentRequest.orderitems.put(1, orderitem);

		orderitem = new LinkedHashMap();
		orderitem.put("SubMerName", "测试二级商户1");    //设定二级商户名称
		orderitem.put("SubMerId", "12345");    //设定二级商户代码
		orderitem.put("SubMerMCC", "0000");   //设定二级商户MCC码 
		orderitem.put("SubMerchantRemarks", "测试");   //二级商户备注项
		orderitem.put("ProductID", "IP000001");//商品代码，预留字段
		orderitem.put("ProductName", "中国联通IP卡");//商品名称
		orderitem.put("UnitPrice", "1.00");//商品总价
		orderitem.put("Qty", "2");//商品数量
		orderitem.put("ProductRemarks", "测试商品"); //商品备注项
		orderitem.put("ProductType", "充值类");//商品类型
		orderitem.put("ProductDiscount", "0.9");//商品折扣
		orderitem.put("ProductExpiredDate", "10");//商品有效期
		tPaymentRequest.orderitems.put(2, orderitem);

		//3、生成支付请求对象
		String paymentType = request.getParameter("PaymentType");
		tPaymentRequest.dicRequest.put("PaymentType", paymentType);            //设定支付类型
		String paymentLinkType  = request.getParameter("PaymentLinkType");                                         
		tPaymentRequest.dicRequest.put("PaymentLinkType", paymentLinkType);    //设定支付接入方式
		if (paymentType.equals(Constants.PAY_TYPE_UCBP) && paymentLinkType.equals(Constants.PAY_LINK_TYPE_MOBILE))
		{
		    tPaymentRequest.dicRequest.put("UnionPayLinkType",request.getParameter("UnionPayLinkType"));  //当支付类型为6，支付接入方式为2的条件满足时，需要设置银联跨行移动支付接入方式
		}
		tPaymentRequest.dicRequest.put("ReceiveAccount", request.getParameter("ReceiveAccount"));      //设定收款方账号
		tPaymentRequest.dicRequest.put("ReceiveAccName", request.getParameter("ReceiveAccName"));      //设定收款方户名
		tPaymentRequest.dicRequest.put("NotifyType", request.getParameter("NotifyType"));              //设定通知方式
		tPaymentRequest.dicRequest.put("ResultNotifyURL", request.getParameter("ResultNotifyURL"));    //设定通知URL地址
		tPaymentRequest.dicRequest.put("MerchantRemarks", request.getParameter("MerchantRemarks"));    //设定附言
		tPaymentRequest.dicRequest.put("ReceiveMark",request.getParameter("ReceiveMark"));             //交易是否直接入二级商户账户
		tPaymentRequest.dicRequest.put("ReceiveMerchantType",request.getParameter("ReceiveMerchantType")); //设定收款方账户类型
		tPaymentRequest.dicRequest.put("IsBreakAccount", request.getParameter("IsBreakAccount"));      //设定交易是否分账、交易是否支持向二级商户入账
		tPaymentRequest.dicRequest.put("SplitAccTemplate", request.getParameter("SplitAccTemplate"));  //分账模版编号        

		//4、添加分账信息
		String[] SubMerchantID_arr = new String[]{};       
		String[] SplitAmount_arr = new String[]{};  

		SubMerchantID_arr    = request.getParameterValues("SplitMerchantID");  
		SplitAmount_arr      = request.getParameterValues("SplitAmount");  

		LinkedHashMap map = null;   
		  
		if(SubMerchantID_arr != null){
		     for (int i = 0; i < SubMerchantID_arr.length; i++)
		     {
		         map = new LinkedHashMap();
		         //map.put("SeqNo       ", String.valueOf(i + 1));
		         map.put("SplitMerchantID",SubMerchantID_arr[i]);
		         map.put("SplitAmount",SplitAmount_arr[i]);
		         
		         tPaymentRequest.dicSplitAccInfo.put(i+1, map);
		     }
		}  

		JSON json = tPaymentRequest.postRequest();
		//JSON json = tPaymentRequest.extendPostRequest(1);

		String ReturnCode = json.GetKeyValue("ReturnCode");
		String ErrorMessage = json.GetKeyValue("ErrorMessage");
		if (ReturnCode.equals("0000"))
		{
			System.out.println("ReturnCode   = [" + ReturnCode + "]<br/>");
			System.out.println("ErrorMessage = [" + ErrorMessage + "]<br/>");
			System.out.println("PaymentURL-->"+json.GetKeyValue("PaymentURL"));
			response.sendRedirect(json.GetKeyValue("PaymentURL"));
		}
		else
		{
			System.out.println("ReturnCode   = [" + ReturnCode + "]");
			System.out.println("ErrorMessage   = [" + ErrorMessage + "]");
		}
		
	}
}
