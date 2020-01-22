package com.guolaiwan.app.aoyou;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.aoyou.bo.AoYouOrder;
import com.guolaiwan.app.aoyou.util.UnicodeUtil;
import com.guolaiwan.app.qimingxin.Param;

import pub.caterpillar.communication.http.client.HttpClient;

@Service
public class AoYouV2Service {

//	//测试地址
//	private static final String URL="https://fxapitest.aoyou.com/v2/ota/";
//	//渠道ID号
//	private static final String channel_id="glw_test";	
//	//渠道密钥
//	private static final String channel_secret="a88ffb04e6f0357195820b7f570d6f2b";
//	//应用ID
//	private static final String app_id="21wQJDtNcEdVZBefCnZkbnI3v7OLLP7Ue90VOJSvIKA%3D";	
	
	//正式地址
	private static final String URL="https://fxapi.aoyou.com/v2/ota/";
	//渠道ID号
	private static final String channel_id="glw";	
	//渠道密钥
	private static final String channel_secret="aeda3ef3930912816cbb52f251a8ed26";
	//应用ID
	private static final String app_id="21wQJDtNcEdVZBefCnZkbnI3v7OLLP7Ue90VOJSvIKA%3D";
		
		
	//应用ID
	private static String access_token="";	
	
	/**
	 * 获取AccessToken
	 * @return
	 * @throws Exception 
	 */
	public static String getAccessToken() throws Exception{
		Map map = new HashMap<String, String>();
		map.put("channel_id", channel_id);
		map.put("channel_secret", channel_secret);
		map.put("app_id", app_id);
		String result = HttpClient.postJson(URL + "get-access-token",JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject jsonObj = JSONObject.parseObject(result);
		access_token = (String) jsonObj.getJSONObject("errdata").get("access_token");
		System.out.println(access_token);
		return UnicodeUtil.unicodeStr2String(result);
	}
	
	/**
	 * 获取场馆列表
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject getVenueList(int page) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("page", page);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "venue-list?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		if(!"00000".equals(reJson.get("errcode"))){
			if("没找到登录信息".equals(reJson.get("errmsg").toString())) {
				getAccessToken();
				result = HttpClient.postJson(URL + "venue-list?access_token=" + access_token,
						JSON.parseObject(JSONObject.toJSONString(map)));
				reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
			}
		}
		return reJson;
	}
	
	/**
	 * 获取产品列表
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject getprodList(String venueId, int page, String keyWord) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("venue_id", venueId);
		map.put("page", page);
		map.put("keyword", keyWord);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "prod-list?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		if(!"00000".equals(reJson.get("errcode"))){
			if("没找到登录信息".equals(reJson.get("errmsg").toString())) {
				getAccessToken();
				result = HttpClient.postJson(URL + "prod-list?access_token=" + access_token,
						JSON.parseObject(JSONObject.toJSONString(map)));
				reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
			}
		}
		return reJson;
	}
	
	/**
	 * 获取产品详情
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject getproductInfo(String prodId) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("prod_id", prodId);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "product-info?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		if(!"00000".equals(reJson.get("errcode"))){
			if("没找到登录信息".equals(reJson.get("errmsg").toString())) {
				getAccessToken();
				result = HttpClient.postJson(URL + "product-info?access_token=" + access_token,
						JSON.parseObject(JSONObject.toJSONString(map)));
				reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
			}
		}
		return reJson;
	}
	
	/**
	 * 创建订单
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject createOrder(AoYouOrder aoYouOrder) throws Exception{
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "create-order?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(aoYouOrder)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		if(!"00000".equals(reJson.get("errcode"))){
			if("没找到登录信息".equals(reJson.get("errmsg").toString())) {
				getAccessToken();
				result = HttpClient.postJson(URL + "create-order?access_token=" + access_token,
						JSON.parseObject(JSONObject.toJSONString(aoYouOrder)));
				reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
			}
		}
		return reJson;
	}
	
	/**
	 * 取消订单
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject cancelOrder(String orderNo, String mobileNo) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("order_no", orderNo);
		map.put("mobile_no", mobileNo);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "cancel-order?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		if(!"00000".equals(reJson.get("errcode"))){
			if("没找到登录信息".equals(reJson.get("errmsg").toString())) {
				getAccessToken();
				result = HttpClient.postJson(URL + "cancel-order?access_token=" + access_token,
						JSON.parseObject(JSONObject.toJSONString(map)));
				reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
			}
		}
		return reJson;
	}
	
	/**
	 * 支付订单
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject submitOrder(String orderNo, String mobileNo) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("order_no", orderNo);
		map.put("mobile_no", mobileNo);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "submit-order?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		if(!"00000".equals(reJson.get("errcode"))){
			if("没找到登录信息".equals(reJson.get("errmsg").toString())) {
				getAccessToken();
				result = HttpClient.postJson(URL + "submit-order?access_token=" + access_token,
						JSON.parseObject(JSONObject.toJSONString(map)));
				reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
			}
		}
		return reJson;
	}
	
	/**
	 * 退票
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject refund(String orderNo, String mobileNo, String refundReason) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("order_no", orderNo);
		map.put("mobile_no", mobileNo);
		map.put("refund_reason", refundReason);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "refund?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		if(!"00000".equals(reJson.get("errcode"))){
			if("没找到登录信息".equals(reJson.get("errmsg").toString())) {
				getAccessToken();
				result = HttpClient.postJson(URL + "refund?access_token=" + access_token,
						JSON.parseObject(JSONObject.toJSONString(map)));
				reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
			}
		}
		return reJson;
	}
	
	/**
	 * 查询订单
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject getOrderInfo(String orderNo) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("order_no", orderNo);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "order-info?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		if(!"00000".equals(reJson.get("errcode"))){
			if("没找到登录信息".equals(reJson.get("errmsg").toString())) {
				getAccessToken();
				result = HttpClient.postJson(URL + "order-info?access_token=" + access_token,
						JSON.parseObject(JSONObject.toJSONString(map)));
				reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
			}
		}
		return reJson;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(getVenueList(1));
			
			System.out.println(getprodList("12", 1, ""));
			
			System.out.println(getproductInfo("50"));
			
//			AoYouOrder aoYouOrder = new AoYouOrder();
//			aoYouOrder.setTrade_no("glw-aoyou-202001211324548952");
//			aoYouOrder.setProd_id(50);
//			aoYouOrder.setUser_name("test");
//			aoYouOrder.setMobile_no("18701322998");
//			aoYouOrder.setProd_count(1);
//			aoYouOrder.setBook_date("2019-01-18");
//			System.out.println(createOrder(aoYouOrder));
			
//			System.out.println(cancelOrder("XCX621800201161445202569","18701322998"));
			
//			System.out.println(submitOrder("XCX621800201211416387688","18701322998"));
			
//			System.out.println(refund("XCX621800201211416387688", "18701322998", "test"));
			
			System.out.println(getOrderInfo("XCX621800201211416387688"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
