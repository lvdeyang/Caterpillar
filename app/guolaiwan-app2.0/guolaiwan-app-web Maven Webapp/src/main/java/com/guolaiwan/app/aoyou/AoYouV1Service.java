package com.guolaiwan.app.aoyou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.aoyou.bo.AoYouOrder;
import com.guolaiwan.app.aoyou.bo.AoYouOrder.AoYouOrderDetail;
import com.guolaiwan.app.aoyou.bo.AoYouOrder.BookDetail;
import com.guolaiwan.app.aoyou.util.UnicodeUtil;

import pub.caterpillar.communication.http.client.HttpClient;

@Service
public class AoYouV1Service {

//	//测试地址
//	private static final String URL="https://fxapitest.aoyou.com/v1/ota/";
//	//渠道ID号
//	private static final String channel_id="glw_test";	
//	//渠道密钥
//	private static final String channel_secret="a88ffb04e6f0357195820b7f570d6f2b";
//	//应用ID
//	private static final String app_id="21wQJDtNcEdVZBefCnZkbnI3v7OLLP7Ue90VOJSvIKA%3D";
	
	//正式地址
	private static final String URL="https://fxapi.aoyou.com/v1/ota/";
	//渠道ID号
	private static final String channel_id="glw";	
	//渠道密钥
	private static final String channel_secret="aeda3ef3930912816cbb52f251a8ed26";
	//应用ID
	private static final String app_id="mcbdEODE5AFSSbfSFWZALwEtoDmruuw017IINIV9ziE%3D";
		
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
	 * 获取产品分类列表
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject getProdCategoryList(int page) throws Exception{
		String result = "";
		Map map = new HashMap<String, String>();
		map.put("page", page);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		result = HttpClient.postJson(URL + "prod-category-list?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		return reJson;
	}
	
	/**
	 * 获取票种列表
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject getprodList(int categoryId, int page, String keyWord) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("category_id", categoryId);
		map.put("page", page);
		map.put("keyword", keyWord);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "prod-list?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		return reJson;
	}
	
	/**
	 * 获取产品详情
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject getproductInfo(int prodId) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("prod_id", prodId);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "product-info?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		return reJson;
	}
	
	/**
	 * 预约情况查询
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject bookInfoQuery(int prodId, String beginDate, String endDate) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("prod_id", prodId);
		map.put("begin_date", beginDate);
		map.put("end_date", endDate);
		if("".equals(access_token) || null == access_token){
			getAccessToken();
		}
		String result = HttpClient.postJson(URL + "book-info-query?access_token=" + access_token,
				JSON.parseObject(JSONObject.toJSONString(map)));
		JSONObject reJson = JSON.parseObject(UnicodeUtil.unicodeStr2String(result));
		if(!"00000".equals(reJson.get("errcode"))){
			if("没找到登录信息".equals(reJson.get("errmsg").toString())) {
				getAccessToken();
				result = HttpClient.postJson(URL + "book-info-query?access_token=" + access_token,
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
	public static JSONObject cancelOrder(String saleorderNo, String mobileNo) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("saleorder_no", saleorderNo);
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
	public static JSONObject submitOrder(String saleorderNo, String mobileNo) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("saleorder_no", saleorderNo);
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
	 * 查询订单
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject getOrderInfo(String orderNo, String saleorderNo) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("orderno", orderNo);
		map.put("saleorder_no", saleorderNo);
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
	
	/**
	 * 退票
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject refund(String saleorderNo, int orderTotal, int orderAmount, String mobileNo, String refundReason) throws Exception{
		Map map = new HashMap<String, String>();
		map.put("saleorder_no", saleorderNo);
		map.put("order_total", orderTotal);
		map.put("order_amount", orderAmount);
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
	
	public static void main(String[] args) {
		try {
//			System.out.println(getProdCategoryList(1));
			
			System.out.println(getprodList(0, 1, ""));
			
//			System.out.println(getproductInfo(131));
			
			System.out.println(bookInfoQuery(131, "20200120", "20200120"));
			
//			AoYouOrder aoYouOrder = new AoYouOrder();
//			aoYouOrder.setTrade_no("glw-aoyou-2020011610401259");
//			aoYouOrder.setProd_id(131);
//			aoYouOrder.setMobile_no("18701322998");
//			aoYouOrder.setProd_count(3);
//			List aoYouOrderDetailList = new ArrayList();
//			AoYouOrderDetail aoYouOrderDetail1 = new AoYouOrderDetail();
//			aoYouOrderDetail1.setTicket_count(1);
//			aoYouOrderDetail1.setHolder_name("test1");
//			aoYouOrderDetail1.setHolder_mobile("18701322998");
//			aoYouOrderDetail1.setId_type(1);
//			aoYouOrderDetail1.setId_no("130281198912230031");
//			aoYouOrderDetailList.add(aoYouOrderDetail1);
//			AoYouOrderDetail aoYouOrderDetail2 = new AoYouOrderDetail();
//			aoYouOrderDetail2.setTicket_count(1);
//			aoYouOrderDetail2.setHolder_name("test2");
//			aoYouOrderDetail2.setHolder_mobile("18701322998");
//			aoYouOrderDetail2.setId_type(1);
//			aoYouOrderDetail2.setId_no("211224199612057159");
//			aoYouOrderDetailList.add(aoYouOrderDetail2);
//			AoYouOrderDetail aoYouOrderDetail3 = new AoYouOrderDetail();
//			aoYouOrderDetail3.setTicket_count(1);
//			aoYouOrderDetail3.setHolder_name("test3");
//			aoYouOrderDetail3.setHolder_mobile("18701322998");
//			aoYouOrderDetail3.setId_type(1);
//			aoYouOrderDetail3.setId_no("130281199009100016");
//			aoYouOrderDetailList.add(aoYouOrderDetail3);
//			aoYouOrder.setOrder_detail(aoYouOrderDetailList);
//			BookDetail bookDetail = new BookDetail();
//			bookDetail.setBook_date("2020-01-25");
//			bookDetail.setBook_period("1");
//			bookDetail.setVenue_code("10001");
//			bookDetail.setBook_count(3);
//			aoYouOrder.setBook_detail(bookDetail);
//			System.out.println(createOrder(aoYouOrder));
			
//			System.out.println(cancelOrder("XCX621800201180916107380","18701322998"));
			
//			System.out.println(submitOrder("XCX621800201201011287510","18701322998"));
			
			System.out.println(getOrderInfo("1400940630221456","XCX621800201201011287510"));
			
//			System.out.println(refund("XCX621800201161043432691", 1, 8000, "18701322998", "test"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
