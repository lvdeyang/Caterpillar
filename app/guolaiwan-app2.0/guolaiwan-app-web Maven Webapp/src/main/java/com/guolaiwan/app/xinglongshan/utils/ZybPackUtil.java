package com.guolaiwan.app.xinglongshan.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guolaiwan.app.xinglongshan.dto.Header;
import com.guolaiwan.app.xinglongshan.dto.IdentityInfo;
import com.guolaiwan.app.xinglongshan.dto.Order;
import com.guolaiwan.app.xinglongshan.dto.OrderRequest;
import com.guolaiwan.app.xinglongshan.dto.PWBRequest;
import com.guolaiwan.app.xinglongshan.dto.PWBResponse;
import com.guolaiwan.app.xinglongshan.dto.ReturnTicket;
import com.guolaiwan.app.xinglongshan.dto.SubOrder;
import com.guolaiwan.app.xinglongshan.dto.TicketOrder;
import com.thoughtworks.xstream.XStream;



public class ZybPackUtil {
	private static final Logger logger = LoggerFactory.getLogger(ZybPackUtil.class);


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "<PWBResponse>  <code>6</code>  <description>子订单编号已存在！</description></PWBResponse>";
		xmlToObject(a);
	
	}
	public static void setXmlAlias(XStream xStream) {
	  	xStream.registerConverter(new DateConverter());
		xStream.alias("PWBRequest", PWBRequest.class);
		xStream.alias("PWBResponse", PWBResponse.class);
		xStream.alias("identityInfo", IdentityInfo.class);
		xStream.alias("header", Header.class);
		xStream.alias("orderRequest", OrderRequest.class);
		xStream.alias("order", Order.class);
		xStream.alias("returnTicket", ReturnTicket.class);
		xStream.alias("ticketOrder", TicketOrder.class);
		xStream.alias("subOrder", SubOrder.class);
		xStream.alias("subOrders", List.class);
	}
	
	
	public static String  ObjectToXml(PWBRequest pwbRequest){
		XStream xStream = new XStream();
		setXmlAlias(xStream);
		String xmlMsg= xStream.toXML(pwbRequest);
		return xmlMsg;
	}
	public static PWBResponse xmlToObject(String xml){
		XStream xStream = new XStream();
		setXmlAlias(xStream);
		PWBResponse response= (PWBResponse)xStream.fromXML(xml);
		return response;
	}
	
	/**
	 * 
	 * @param pwbRequest 请求数据组装
	 * @param key  私钥
	 * @param url  地址
	 * @return
	 */
	public static PWBResponse  requestZyb(PWBRequest pwbRequest,String key,String url){
		String xmlMsg = ObjectToXml(pwbRequest);
		System.out.println(xmlMsg);
		String sign = MD5.md5("xmlMsg="+xmlMsg+ key);
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("xmlMsg", new String[]{xmlMsg});
		map.put("sign", new String[]{sign});
		System.out.println(map.toString());
		String returnMsg = HttpPostUtil.postNameValueUTF_8(url, null, map);
		System.out.println(returnMsg);
		if(null==returnMsg){
			throw new BusinessException("请求智游宝返回空");
		}
		return ZybPackUtil.xmlToObject(returnMsg);
	}
	
	

}
