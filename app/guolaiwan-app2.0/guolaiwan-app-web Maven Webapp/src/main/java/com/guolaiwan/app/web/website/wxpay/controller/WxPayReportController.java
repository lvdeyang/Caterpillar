package com.guolaiwan.app.web.website.wxpay.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.DecimalFormat;
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

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.RefundDetail;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.weixin.SendMsgUtil;
import com.guolaiwan.bussiness.Parking.dao.AttractionsDao;
import com.guolaiwan.bussiness.Parking.dao.CarPositionDao;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.dao.ParkingPositionDao;
import com.guolaiwan.bussiness.Parking.dao.VehicleDao;
import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.admin.dao.BundleOrderDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantUserDao;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MerchantUser;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;

import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPay;
@Controller
@RequestMapping("/website/wxreport")
public class WxPayReportController extends WebBaseControll {
//	@Autowired
//	private BasketDAO conn_basket;
//	
	@Autowired
	private OrderInfoDAO conn_orderInfo;
	
	@Autowired
	private ProductDAO conn_product;
	
	@Autowired
	private BundleOrderDAO conn_bundleorder;
	
	@Autowired
	private  VehicleDao par_king;
	
	@Autowired
	private  AttractionsDao Attra_ctions;	
	
	@Autowired
	private OrderDao  Order;
	
	@Autowired
	private CarPositionDao  Car_Position;
	
	@Autowired
	private ParkingPositionDao  Parking_Position;
	
	@ResponseBody
	@RequestMapping(value = "/payreport", method = RequestMethod.POST)
	public String pay(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String, Object> ret=new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		//Mr.huang 2017/09/12 飞的好低的小蜜蜂
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml="";
		String tempStr="";
		while((tempStr=reader.readLine())!=null){
			xml+=tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay=GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);
		
		
		System.out.println("返回的参数："+respData.toString());
		
		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是："+returncode+";resultcode是"+resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if(returncode.equals("SUCCESS")){
			if(resultcode.equals("SUCCESS")){
				int i=0;
				//获取订单号
				String tradeNum=respData.get("out_trade_no");
				String refundNum=respData.get("out_refund_no");
				if(refundNum!=null&&refundNum.indexOf("refund")!=-1){
					String[] rIds =  tradeNum.split("A");
					if(tradeNum.indexOf("bundle")!=-1){
						Long bundleOrderId=Long.parseLong(tradeNum.split("-")[1]);
						BundleOrder bundleOrder=conn_bundleorder.get(bundleOrderId);
						rIds=bundleOrder.getOrderStr().split("A");
						
					}
					for (String ridStr : rIds) { //退款
						OrderInfoPO order = conn_orderInfo.get(Long.parseLong(ridStr));
						order.setOrderState(OrderStateType.REFUNDED);
						conn_orderInfo.save(order);
						sendMessage(order);
					}
				
					stringBuffer.append("<xml><return_code><![CDATA[");
					stringBuffer.append("SUCCESS");
					stringBuffer.append("]]></return_code>");
					stringBuffer.append("<return_msg><![CDATA[");
					stringBuffer.append("OK");
					stringBuffer.append("]]></return_msg>");
					System.out.println("微信支付退款成功!订单号："+tradeNum);
					return stringBuffer.toString();
				}
				String[] orderIds =  tradeNum.split("A");
				//orderNo="bundle-"+order.getId();
				if(tradeNum.indexOf("bundle")!=-1){
					Long bundleOrderId=Long.parseLong(tradeNum.split("-")[1]);
					BundleOrder bundleOrder=conn_bundleorder.get(bundleOrderId);
					orderIds=bundleOrder.getOrderStr().split("A");
				}
				for (String orderId : orderIds) {
					i=i+1;
					OrderInfoPO order = conn_orderInfo.get(Long.parseLong(orderId));
					if(order.getOrderState().toString().equals("NOTPAY")){
						//微信支付
						order.setPayMode(PayType.WEICHAT);
						order.setPayDate(new Date());
						//生成验单码,和二维码图片
						String ydNO = ydNoCode(orderId);
						order.setYdNO(ydNO);
						//支付状态、减库存
						order.setOrderState(OrderStateType.PAYSUCCESS);  
						ProductPO product = conn_product.get(order.getProductId());
						if(product != null){
							//long productNum = order.getProductNum();
							//product.setProductStock(product.getProductStock()-productNum);
							//conn_product.saveOrUpdate(product);
						}
						conn_orderInfo.saveOrUpdate(order);
						sendMessage(order);
					}	
				}
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!"+i+"个订单。订单号："+tradeNum);
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
	@RequestMapping(value = "/parkrefund", method = RequestMethod.POST)
	public String parkreport(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String, Object> ret=new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		//Mr.huang 2017/09/12 飞的好低的小蜜蜂
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml="";
		String tempStr="";
		while((tempStr=reader.readLine())!=null){
			xml+=tempStr;
			System.out.println(tempStr);
		}
		
		GuolaiwanWxPay wxPay=GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);
		
		
		System.out.println("返回的参数："+respData.toString());
		
		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是："+returncode+";resultcode是"+resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if(returncode.equals("SUCCESS")){
			if(resultcode.equals("SUCCESS")){
				int i=0;
				//获取订单号
				String tradeNum=respData.get("out_trade_no");
				String refundNum=respData.get("out_refund_no");
				/*if(refundNum!=null&&refundNum.indexOf("refund")!=-1){
					String[] rIds =  tradeNum.split("A");
					if(tradeNum.indexOf("bundle")!=-1){
						Long bundleOrderId=Long.parseLong(tradeNum.split("-")[1]);
						BundleOrder bundleOrder=conn_bundleorder.get(bundleOrderId);
						rIds=bundleOrder.getOrderStr().split("A");
						
					}
					for (String ridStr : rIds) { //退款
						OrderInfoPO order = conn_orderInfo.get(Long.parseLong(ridStr));
						order.setOrderState(OrderStateType.REFUNDED);
						conn_orderInfo.save(order);
						sendMessage(order);
					}
					
					stringBuffer.append("<xml><return_code><![CDATA[");
					stringBuffer.append("SUCCESS");
					stringBuffer.append("]]></return_code>");
					stringBuffer.append("<return_msg><![CDATA[");
					stringBuffer.append("OK");
					stringBuffer.append("]]></return_msg>");
					System.out.println("微信支付退款成功!订单号："+tradeNum);
					return stringBuffer.toString();
				}
				String[] orderIds =  tradeNum.split("A");
				//orderNo="bundle-"+order.getId();
				if(tradeNum.indexOf("bundle")!=-1){
					Long bundleOrderId=Long.parseLong(tradeNum.split("-")[1]);
					BundleOrder bundleOrder=conn_bundleorder.get(bundleOrderId);
					orderIds=bundleOrder.getOrderStr().split("A");
				}
				for (String orderId : orderIds) {
					i=i+1;
					OrderInfoPO order = conn_orderInfo.get(Long.parseLong(orderId));
					if(order.getOrderState().toString().equals("NOTPAY")){
						//微信支付
						order.setPayMode(PayType.WEICHAT);
						order.setPayDate(new Date());
						//生成验单码,和二维码图片
						String ydNO = ydNoCode(orderId);
						order.setYdNO(ydNO);
						//支付状态、减库存
						order.setOrderState(OrderStateType.PAYSUCCESS);  
						ProductPO product = conn_product.get(order.getProductId());
						if(product != null){
							//long productNum = order.getProductNum();
							//product.setProductStock(product.getProductStock()-productNum);
							//conn_product.saveOrUpdate(product);
						}
						conn_orderInfo.saveOrUpdate(order);
						sendMessage(order);
					}	
				}*/
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!"+i+"个订单。订单号："+tradeNum);
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
	
	@ResponseBody
	@RequestMapping(value = "/payreportpark", method = RequestMethod.POST)
	// TODO
	public String paypark(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String, Object> ret=new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml="";
		String tempStr="";
		while((tempStr=reader.readLine())!=null){
			xml+=tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay=GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);
		
		System.out.println("返回的参数："+respData.toString());
		
		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是："+returncode+";resultcode是"+resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if(returncode.equals("SUCCESS")){
			if(resultcode.equals("SUCCESS")){
				//获取订单号
				String vehicle = null;
				String tradeNum = respData.get("out_trade_no");
				Long orderId= Long.parseLong(tradeNum.split("-")[1]);
				Long attactionsId = Long.parseLong(tradeNum.split("-")[2]);

				//orderNo="bundle-"+order.getId();
			
				//OrderInfoPO order = conn_orderInfo.get(Long.parseLong(orderId));
				//if(order.getOrderState().toString().equals("NOTPAY")){
				
				//	}	
				//    修改 总 车位 剩余车位
			    AttractionsParkingPO att = Attra_ctions.getUid(attactionsId);
			    int remain =  att.getUsedParking();
			    att.setUsedParking(remain-1);
			    Attra_ctions.saveOrUpdate(att);
			    
			    // 生成 二维码 修改订单状态
			    
				OrderPO userByid = Order.getform(orderId);
				if(userByid != null){
					    String ydNO = ydNoCode(userByid.getId()+"");
						userByid.setOrderStatus("PAYSUCCESS");
						userByid.setPath(ydNO);
						Order.saveOrUpdate(userByid);
						// 修改 车位 已使用
						int	parkingNumber = userByid.getParkingNumber();
						String	parkingLayer = 	userByid.getParkingLayer();
						String	district = 	userByid.getParkingDistrict();
						
						CarPositionPO userName =  Car_Position.getAmend(attactionsId,parkingLayer,district);
						long id = userName.getId();
						
					    ParkingPositionPO getTruck  =  Parking_Position.getNumber(id,parkingNumber);
					    getTruck.setUseCondition(1);
						Parking_Position.saveOrUpdate(getTruck);
						
						stringBuffer.append("<xml><return_code><![CDATA[");
						stringBuffer.append("SUCCESS");
						stringBuffer.append("]]></return_code>");
						stringBuffer.append("<return_msg><![CDATA[");
						stringBuffer.append("OK");
						stringBuffer.append("]]></return_msg>");
						System.out.println("微信支付付款成功!订单号："+tradeNum);
				}
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
	
	@ResponseBody
	@RequestMapping(value = "/payreportrenew", method = RequestMethod.POST)
	// TODO
	public String  portrenew(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String, Object> ret=new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml="";
		String tempStr="";
		while((tempStr=reader.readLine())!=null){
			xml+=tempStr;
			System.out.println(tempStr);
		}
		
		GuolaiwanWxPay wxPay=GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);
		
		System.out.println("返回的参数："+respData.toString());
		
		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是："+returncode+";resultcode是"+resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if(returncode.equals("SUCCESS")){
			if(resultcode.equals("SUCCESS")){
				//获取订单号
				String tradeNum = respData.get("out_trade_no");
				Long orderId= Long.parseLong(tradeNum.split("-")[1]);
				Long attactionsId = Long.parseLong(tradeNum.split("-")[2]);
				//获取商品订单号
				String transaction = respData.get("transaction_id");
				// 查询 订单信息 
				OrderPO orderinfor = Order.getform(orderId);
				String booking  =  orderinfor.getBookingTime(); //入场时间
				Long attid  =  orderinfor.getAttractionsId(); // 景区id
				String duetime = orderinfor.getDueTime(); // 离场时间说
				String status = orderinfor.getOrderStatus();//订单状态
				String layer = orderinfor.getParkingLayer();//层
				String district = orderinfor.getParkingDistrict();//区  
				Integer number = orderinfor.getParkingNumber();//车位编号  
				String  name  = orderinfor.getParkingName();//车场名称  
				String  plate  = orderinfor.getPlatenumber();//车牌号  
				Long userid  = orderinfor.getOrderId(); // 用户id
				orderinfor.setOrderStatus("PAST");
				Order.saveOrUpdate(orderinfor);
				
				//新创订单 
				OrderPO odp = new OrderPO();
				odp.setBookingTime(booking);
				odp.setDueTime(duetime);
				odp.setStoppingTime(0.0);
				odp.setOrderStatus(status);
				odp.setParkingLayer(layer);
				odp.setParkingName(name);
				odp.setParkingDistrict(district);
				odp.setParkingNumber(number);
				odp.setAttractionsId(attid);
				odp.setParkingCost(0);
				odp.setOrderId(userid);
				odp.setPlatenumber(plate);
				odp.setOrderNo(tradeNum);
				odp.setCommodityNumber(transaction);
				Order.saveOrUpdate(odp);
				
				
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!订单号："+tradeNum);
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
	
	
	
	
	
	
	@Autowired UserInfoDAO conn_user;
	@Autowired MerchantDAO conn_merchant;
	@Autowired MerchantUserDao conn_merchantUser;
	@Autowired
	private AddressDAO conn_address;
    private void sendMessage(OrderInfoPO orderInfoPO){
    	
    	ProductPO productPO=conn_product.get(orderInfoPO.getProductId());
    	MerchantPO merchantPO=conn_merchant.get(orderInfoPO.getShopId());
    	
    	//用户推送消息
    	Double amount=Double.parseDouble(orderInfoPO.getPayMoney()+"")/100;
    	DecimalFormat df=new DecimalFormat("0.00");  
    	UserInfoPO buyUser=conn_user.get(orderInfoPO.getUserId());
    	if(buyUser!=null){
    		JSONObject obj=new JSONObject();
    		obj.put("touser", buyUser.getUserOpenID());
        	obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
        	obj.put("url", "");
        	JSONObject microProObj=new JSONObject();
        	microProObj.put("appid", "");
        	microProObj.put("pagepath", "");
        	obj.put("miniprogram", microProObj);
        	JSONObject dataObject=new JSONObject();
        	JSONObject firstObj=new JSONObject();
        	firstObj.put("value", "您的订单支付成功");
        	firstObj.put("color", "");
        	dataObject.put("first", firstObj);
        	
        	
        	JSONObject nameObj=new JSONObject();
        	nameObj.put("value", buyUser.getUserNickname());
        	nameObj.put("color", "");
        	dataObject.put("keyword1", nameObj);
        	
        	JSONObject accountTypeObj=new JSONObject();
        	accountTypeObj.put("value", orderInfoPO.getId());
        	accountTypeObj.put("color", "");
        	dataObject.put("keyword2", accountTypeObj);
        	
        	
        	JSONObject accountObj=new JSONObject();
        	accountObj.put("value", df.format(amount));
        	accountObj.put("color", "");
        	dataObject.put("keyword3", accountObj);
        	JSONObject timeObj=new JSONObject();
        	timeObj.put("value",productPO==null?"到店支付订单:"+merchantPO.getShopName():productPO.getProductName());
        	timeObj.put("color", "");
        	dataObject.put("keyword4", timeObj);
        	JSONObject remarkObj=new JSONObject();
        	remarkObj.put("value", "感谢使用过来玩服务");
        	remarkObj.put("color", "");
        	dataObject.put("remark", remarkObj);
        	obj.put("data", dataObject);
        	SendMsgUtil.sendTemplate(obj.toJSONString());
    	}
    	
    	
    	
    	//商户推送消息
    	//UserInfoPO userInfoPO=merchantPO.getUser();
    	List<MerchantUser> merchantUsers=conn_merchantUser.findByField("merchantId", merchantPO.getId());
    	for (MerchantUser merchantUser : merchantUsers) {
    		UserInfoPO userInfoPO=conn_user.get(merchantUser.getUserId());
    		JSONObject obj=new JSONObject();
    		obj.put("touser",userInfoPO.getUserOpenID() );
        	obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
        	obj.put("url", "");
        	JSONObject microProObj=new JSONObject();
        	microProObj.put("appid", "");
        	microProObj.put("pagepath", "");
        	obj.put("miniprogram", microProObj);
        	JSONObject dataObject=new JSONObject();
        	JSONObject firstObj=new JSONObject();
        	firstObj.put("value", "新的过来玩订单");
        	firstObj.put("color", "");
        	dataObject.put("first", firstObj);
        	
        	
        	JSONObject nameObj=new JSONObject();
        	nameObj.put("value", buyUser.getUserNickname());
        	nameObj.put("color", "");
        	dataObject.put("keyword1", nameObj);
        	
        	JSONObject accountTypeObj=new JSONObject();
        	accountTypeObj.put("value", orderInfoPO.getOrderNO());
        	accountTypeObj.put("color", "");
        	dataObject.put("keyword2", accountTypeObj);
        	
        	
        	JSONObject accountObj=new JSONObject();
        	accountObj.put("value", df.format(amount));
        	accountObj.put("color", "");
        	dataObject.put("keyword3", accountObj);
        	JSONObject timeObj=new JSONObject();
        	timeObj.put("value", productPO==null?"到店支付订单:"+merchantPO.getShopName():productPO.getProductName());
        	timeObj.put("color", "");
        	dataObject.put("keyword4", timeObj);
        	JSONObject remarkObj=new JSONObject();
        	remarkObj.put("value", "请做好接待工作");
        	remarkObj.put("color", "");
        	dataObject.put("remark", remarkObj);
        	obj.put("data", dataObject);
        	SendMsgUtil.sendTemplate(obj.toJSONString());
		}
    	
    	
    	JSONObject obj=new JSONObject();
		obj.put("touser","opVUYv9LtqKAbiaXInBqI01hlpYg");
    	obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
    	obj.put("url", "");
    	JSONObject microProObj=new JSONObject();
    	microProObj.put("appid", "");
    	microProObj.put("pagepath", "");
    	obj.put("miniprogram", microProObj);
    	JSONObject dataObject=new JSONObject();
    	JSONObject firstObj=new JSONObject();
    	firstObj.put("value", "新的过来玩订单");
    	firstObj.put("color", "");
    	dataObject.put("first", firstObj);
    	
    	
    	JSONObject nameObj=new JSONObject();
    	nameObj.put("value", conn_address.get(orderInfoPO.getMailAddress()).getConsigneeName());
    	nameObj.put("color", "");
    	dataObject.put("keyword1", nameObj);

    	
    	JSONObject accountTypeObj=new JSONObject();
    	accountTypeObj.put("value", orderInfoPO.getId());
    	accountTypeObj.put("color", "");
    	dataObject.put("keyword2", accountTypeObj);
    	
    	
    	JSONObject accountObj=new JSONObject();
    	accountObj.put("value", df.format(amount));
    	accountObj.put("color", "");
    	dataObject.put("keyword3", accountObj);
    	JSONObject timeObj=new JSONObject();
    	timeObj.put("value", productPO==null?"到店支付订单:"+merchantPO.getShopName():productPO.getProductName());
    	timeObj.put("color", "");
    	
    	String pNum="";
    	if(conn_address.get(orderInfoPO.getMailAddress())!=null){
    		pNum=conn_address.get(orderInfoPO.getMailAddress()).getConsigneePhone();
    	}
    	if(pNum==null||pNum.isEmpty()){
    		pNum=buyUser.getUserPhone();
    	}
    	dataObject.put("keyword4", timeObj);
    	JSONObject remarkObj=new JSONObject();
    	remarkObj.put("value", "请做好接待工作(用户电话:"+pNum+")");
    	remarkObj.put("color", "");
    	dataObject.put("remark", remarkObj);
    	obj.put("data", dataObject);
    	SendMsgUtil.sendTemplate(obj.toJSONString());
    	
    	//opVUYv9LtqKAbiaXInBqI01hlpYg
    	
    	
    	
    	JSONObject obj2=new JSONObject();
		obj2.put("touser","opVUYv-havBnt8CaydM5zmmhkLlw");
    	obj2.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
    	obj2.put("url", "");
    	JSONObject microProObj2=new JSONObject();
    	microProObj2.put("appid", "");
    	microProObj2.put("pagepath", "");
    	obj2.put("miniprogram", microProObj2);
    	JSONObject dataObject2=new JSONObject();
    	JSONObject firstObj2=new JSONObject();
    	firstObj2.put("value", "新的过来玩订单");
    	firstObj2.put("color", "");
    	dataObject2.put("first", firstObj2);
    	
    	
    	JSONObject nameObj2=new JSONObject();
    	nameObj2.put("value", conn_address.get(orderInfoPO.getMailAddress()).getConsigneeName());
    	nameObj2.put("color", "");
    	dataObject2.put("keyword1", nameObj2);

    	
    	JSONObject accountTypeObj2=new JSONObject();
    	accountTypeObj2.put("value", orderInfoPO.getId());
    	accountTypeObj2.put("color", "");
    	dataObject2.put("keyword2", accountTypeObj2);
    	
    	
    	JSONObject accountObj2=new JSONObject();
    	accountObj2.put("value", df.format(amount));
    	accountObj2.put("color", "");
    	dataObject2.put("keyword3", accountObj2);
    	JSONObject timeObj2=new JSONObject();
    	timeObj2.put("value", productPO==null?"到店支付订单:"+merchantPO.getShopName():productPO.getProductName());
    	timeObj2.put("color", "");
    	
    	
    	dataObject2.put("keyword4", timeObj2);
    	JSONObject remarkObj2=new JSONObject();
    	remarkObj2.put("value", "请做好接待工作(用户电话:"+pNum+")");
    	remarkObj2.put("color", "");
    	dataObject2.put("remark", remarkObj2);
    	obj2.put("data", dataObject2);
    	SendMsgUtil.sendTemplate(obj2.toJSONString());
    	
    }
	
	@ResponseBody
	@RequestMapping(value = "/payreport/batch", method = RequestMethod.POST)
	public Map<String,Object> payBatch(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String, Object> ret=new HashMap<String, Object>();
		System.out.println("进来啦进来啦进来啦进来啦进来啦进来啦进来啦进来啦进来啦进来啦进来啦进来啦");
		//Mr.huang 2017/09/12 飞的好低的小蜜蜂
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml="";
		String tempStr="";
		while((tempStr=reader.readLine())!=null){
			xml+=tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay=GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);
		
		
		System.out.println("返回的参数："+respData.toString());
		
		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是："+returncode+";resultcode是"+resultcode);
		if(returncode.equals("SUCCESS")){
			if(resultcode.equals("SUCCESS")){
				//并不好用，要求是一个返回xml格式的，先放在了map里吧
				ret.put("return_code", "SUCCESS");
				ret.put("return_msg", "OK");
				System.out.println("微信支付交易成功");
				
				//获取订单号
				String orderId = URLDecoder.decode(respData.get("out_trade_no"));
				String[] idArr = orderId.split(",");
				List<Long> ids = new ArrayList<Long>();
				for(String id:idArr){
					if(id != null){
						ids.add(Long.valueOf(id));
					}
				}
				
				List<OrderInfoPO> orders = conn_orderInfo.getAllByIds(ids);
				for(OrderInfoPO order:orders){
					if(order.getOrderState().toString().equals("NOTPAY")){
						//微信支付
						order.setPayMode(PayType.WEICHAT);
						
						//生成验单码,和二维码图片
						String ydNO = ydNoCode(order.getOrderNO());
						order.setYdNO(ydNO);
						//支付状态、减库存
						order.setOrderState(OrderStateType.PAYSUCCESS);
						ProductPO product = conn_product.get(order.getProductId());
						if(product != null){
							long productNum = order.getProductNum();
							product.setProductStock(product.getProductStock()-productNum);
							conn_product.saveOrUpdate(product);
						}
						conn_orderInfo.saveOrUpdate(order);
					}
				}
			}else{
				ret.put("return_code", "FAIL");
				ret.put("return_msg", "交易失败");
				System.out.println("微信支付交易失败");
			}
		}else{
			ret.put("return_code", "FAIL");
			ret.put("return_msg", "签名失败");
			System.out.println("微信支付签名失败");
		}
		
		return ret;
	}
}
