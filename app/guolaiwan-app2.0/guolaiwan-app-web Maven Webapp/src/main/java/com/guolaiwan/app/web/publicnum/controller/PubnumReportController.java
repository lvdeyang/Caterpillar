package com.guolaiwan.app.web.publicnum.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.BundleOrderDAO;
import com.guolaiwan.bussiness.distribute.dao.DistributeProductDao;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.distribute.classify.DistributorOrderStatus;
import com.guolaiwan.bussiness.distribute.classify.DistributorType;
import com.guolaiwan.bussiness.distribute.dao.DistributorOrderDao;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;
import com.guolaiwan.bussiness.distribute.po.DistributorOrder;

import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPay;
@Controller
@RequestMapping("/pubnum/wxreport")
public class PubnumReportController extends WebBaseControll {
    @Autowired
    private DistributorOrderDao conn_order;
	@Autowired
	private DistributeProductDao conn_dispro;
	@Autowired
	private ProductDAO conn_pro;
	@ResponseBody
	@RequestMapping(value = "/payreport", method = RequestMethod.POST)
	public String pay(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String, Object> ret=new HashMap<String, Object>();
		
		//Mr.huang 2017/09/12 飞的好低的小蜜蜂
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml="";
		String tempStr="";
		while((tempStr=reader.readLine())!=null){
			xml+=tempStr;
			System.out.println(tempStr);
		}
		GuolaiwanWxPay wxPay=GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/pubnum/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);
		System.out.println("返回的参数："+respData.toString());
		
		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是："+returncode+";resultcode是"+resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if(returncode.equals("SUCCESS")){
			if(resultcode.equals("SUCCESS")){
				//获取订单号
				String tradeNum=respData.get("out_trade_no");
				String[] tradeNums=tradeNum.split("-");
				DistributorOrder order=conn_order.get(Long.parseLong(tradeNums[1]));
				if(order.getStatus().equals(DistributorOrderStatus.PAYED)){
					return "";
				}
				order.setStatus(DistributorOrderStatus.PAYED);
				conn_order.save(order);

				DistributeProduct oldDistributeProduct=conn_dispro.get(order.getProductId());
				oldDistributeProduct.setProleft(oldDistributeProduct.getProleft()-order.getCount());
				conn_dispro.save(oldDistributeProduct);
				
				
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!1个订单。订单号："+tradeNum);
			}else{
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("FAIL");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("交易失败");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付交易失败");
			}
		}else{
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("FAIL");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("签名失败");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付签名失败");
		}
		
		System.out.println("微信返回字符串:"+stringBuffer);
		return stringBuffer.toString();
	}
	
}
