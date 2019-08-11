package com.guolaiwan.app.interfac.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.commons.util.binary.Md5Utils;
import pub.caterpillar.communication.http.client.HttpClient;



/**
 * 凤凰山的接口
 * @author Administrator
 *
 */
public class TianShiTongChengAPI {

	
	
	/*
	 * method string 必须 固定值 item_orders format String 可选 返回文件格式，见format 参数说明
	 * _pid Number 必须 合作伙伴id _sig String 必须 签名。见签名参数 is_pay Number 可选
	 * 是否已付款，1：已付款（创建订单同时发送门票短信）；0：未付款（只创建订单,确认付款请调用“修改订单接口”） orders_id
	 * string 可选 第三方订单ID，可避免网络不好时重复下单 item_id Number 必须 要购买的票ID size Number
	 * 可选 购买票数,缺省1 name String 必须 购票人名称 mobile Number 必须
	 * 购票人手机号(成功后短信将发送门票码号到该手机号) players Array 可选
	 * 参团人员列表；订单需要供多人使用时，可传入此名单供商家参考；注意：此参数与订单验证基本无关； name string 可选 参团人员名字
	 * Mobile Number 可选 参团人员手机 Id_number String 可选 参团人员的身份证号 start_date Date
	 * 可选 开始游玩时间，缺省当前时间 start_date_auto number 可选 是否自动设置游玩时间，1:是；0:否
	 * price_type Number 可选 价格类型；1成人，2儿童，缺省1 (2013-10-28开始只接受值1，不再支持其他类型)
	 * remark string 可选 订单备注信息 price price 可选
	 * 产品单价（特殊系统需要，一般不要传），如果有则校验产品单价与本系统当前单价是否一致 back_cash price 可选
	 * 返现金额（特殊系统需要，一般不要传），如果有则校验产品每份返现与本系统当前返现是否一致 id_number string 无
	 * 身份证号码，是否需要提供由产品决定
	 */

	/*
	 * 测试_pid:4 测试授权码:111
	 * 测试地址:http://demo.demo1.sjdzp.com/Api/Seller/api.json 测试产品ID: 41
	 * 测试下单时不会真正发送短信 后台登录地址：http://demo.demo1.sjdzp.com 登录账号 demo_seller 密码
	 * 123456
	 */
	public static String sendPost(String orderId,String productId,String buynum,String buyUserName,String mobile,String startDate){
			Map<String, Object> paramsMap = new TreeMap<String, Object>(new Comparator<String>() {
		
				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					return o1.compareTo(o2);
				}
			});
		
			/*paramsMap.put("method", "item_orders");
			paramsMap.put("_pid", "4");
			paramsMap.put("is_pay", "1");
			paramsMap.put("orders_id", "1");
			paramsMap.put("item_id", "41");
			paramsMap.put("size", "1");
			paramsMap.put("name", "黄靖宇");
			paramsMap.put("mobile", "13810728953");
			paramsMap.put("start_date", "2019-08-15");*/
			
			paramsMap.put("method", "item_orders");
			paramsMap.put("_pid", "1796133");
			paramsMap.put("is_pay", "1");
			paramsMap.put("orders_id", orderId);
			paramsMap.put("item_id", productId);
			paramsMap.put("size", buynum);
			paramsMap.put("name", buyUserName);
			paramsMap.put("mobile", mobile);
			paramsMap.put("start_date", startDate);
			
			String signStr = "";
			for (String key : paramsMap.keySet()) {
				signStr += key + "=" + paramsMap.get(key) + "&";
			}
			
			/*李姐给的参数
			 * http://hhs.sjdzp.com/Api/Seller/api.json?g_cid=58010
			 * 1796133
			 * 51b277d6c5a6f2b997a98939a62dd1d6
			 * */

		
			paramsMap.put("_sig", Md5Utils.MD5("51b277d6c5a6f2b997a98939a62dd1d6&" + signStr + "51b277d6c5a6f2b997a98939a62dd1d6").toUpperCase());
			JSONObject json = (JSONObject) JSONObject.toJSON(paramsMap);
			System.out.println(json.toJSONString());
		
			try {
				String result = HttpClient.postHttps("http://hhs.sjdzp.com/Api/Seller/api.json", json);
				System.out.println(result);
				return result;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "ERROR";
	}
}
