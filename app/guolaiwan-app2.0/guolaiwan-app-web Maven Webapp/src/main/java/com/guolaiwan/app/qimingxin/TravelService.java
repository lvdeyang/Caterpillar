package com.guolaiwan.app.qimingxin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.bussiness.admin.dao.NhEticketsDao;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.NhEticketsPo;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

public class TravelService {

	@Autowired
	private NhEticketsDao nhEticketsDao;

	@Autowired
	private OrderInfoDAO orderInfoDAO;
	/*
	 * public static String appkey = "4ac93b78-231c-4bf8-9c19-c0671dfd5efd"
	 * test地址; public static String securitykey =
	 * "YEakFURjOlldIzlIKGaXfBclJNHjHVTq"; public static String url =
	 * "http://apitest.qmx028.com";
	 */
	public static Map<String, Object> mapBodyMap = new HashMap<String, Object>();
	public static String appkey = "bc995720-3cc3-4e00-8ee0-c5e9538f6d37";
	public static String securitykey = "sTfGckcPfmUuHdzqnqyOKpLmSWqiMvLu";
	public static String url = "http://otamp2.qmx028.com/";

	private static Map<String, Object> responseMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appkey", appkey);
		return map;
	}

	/*
	 * 获取产品列表
	 */
	public static String getProductList() {
		Map<String, Object> map = responseMap();
		List<String> list = new ArrayList<String>();
		list.add("723026");
		mapBodyMap.put("method", "LIST");
		mapBodyMap.put("pageIndex", 1);
		mapBodyMap.put("pageSize", 3);
		mapBodyMap.put("snList", list);
		mapBodyMap.put("appkey", appkey);

		// 产品ID
		map.put("body", mapBodyMap);
		long timestamp = new Date().getTime();
		map.put("timestamp", new Date().getTime());
		map.put("sign", MD5Util.MD5(appkey + JSONUtil.parseFromMap(mapBodyMap) + securitykey + timestamp));
		return HttpUtil.post(url + "/open/openapi/v2/getProductList", JSONUtil.parseFromMap(map));
	}

	/*
	 * 获取授权价格
	 */
	public static String queryProductPriceList(String productId) {
		Map<String, Object> map = responseMap();

		mapBodyMap.put("startTime", "2020-01-01");
		mapBodyMap.put("endTime", "2020-01-31");
		mapBodyMap.put("appkey", appkey);
		mapBodyMap.put("productId", productId);
		// 产品ID
		map.put("body", mapBodyMap);
		long timestamp = new Date().getTime();
		map.put("timestamp", new Date().getTime());
		map.put("sign", MD5Util.MD5(appkey + JSONUtil.parseFromMap(mapBodyMap) + securitykey + timestamp));
		return HttpUtil.post(url + "/open/openapi/v2/queryProductPriceList", JSONUtil.parseFromMap(map));
	}

	public static void main(String[] args) {
		String aString = createOrder("13591015211", "yudi", "42100012020011523184313695", "723026", 1, "2020-01-18",
				0.01);
		// 票务参数13591015211###迪迪###42100012020011523184313695###723026###1###2020-01-18###0.01
		// String result = getProductList();
		System.out.println(aString);
	}

	/*
	 * 创建订单
	 */
	public static String createOrder(String contactMobile, String contactName, String otaOrderId, String productId,
			Integer quantity, String useDate, Double salePrice) {

		Map<String, Object> map = responseMap();
		mapBodyMap.put("appkey", appkey);
		mapBodyMap.put("contactMobile", contactMobile);
		mapBodyMap.put("contactName", contactName);
		mapBodyMap.put("otaOrderId", otaOrderId);
		mapBodyMap.put("productId", productId);
		mapBodyMap.put("quantity", quantity);
		mapBodyMap.put("salePrice", salePrice);
		mapBodyMap.put("useDate", useDate);
		map.put("body", mapBodyMap);
		long timestamp = new Date().getTime();
		map.put("timestamp", timestamp);
		map.put("sign", MD5Util.MD5(appkey + JSONUtil.parseFromMap(mapBodyMap) + securitykey + timestamp));
		String result = com.guolaiwan.app.qimingxin.HttpUtil.post(url + "/open/openapi/v2/createOrder",
				JSONUtil.parseFromMap(map), 6000, 6000, "utf-8");
		return result;

	}

	/*
	 * 支付订单
	 */
	public static String payOrder(String orderId) {
		Map<String, Object> map = responseMap();
		mapBodyMap.put("appkey", appkey);
		mapBodyMap.put("orderId", orderId);
		map.put("body", mapBodyMap);
		long timestamp = new Date().getTime();
		map.put("timestamp", timestamp);
		map.put("sign", MD5Util.MD5(appkey + JSONUtil.parseFromMap(mapBodyMap) + securitykey + timestamp));

		String result = com.guolaiwan.app.qimingxin.HttpUtil.post(url + "/open/openapi/v2/payOrder",
				JSONUtil.parseFromMap(map), 6000, 6000, "utf-8");
		System.out.println(result);
		return result;

	}

	/*
	 * 查询订单
	 */
	public static String queryOrder(String orderId) {
		Map<String, Object> map = responseMap();
		mapBodyMap.put("appkey", appkey);
		mapBodyMap.put("orderId", orderId);
		map.put("body", mapBodyMap);
		long timestamp = new Date().getTime();
		map.put("timestamp", timestamp);
		map.put("sign", MD5Util.MD5(appkey + JSONUtil.parseFromMap(mapBodyMap) + securitykey + timestamp));
		return HttpUtil.post(url + "/open/openapi/v2/queryOrder", map);
	}

	/*
	 * 申请退款
	 */
	public static String orderRefund(String orderId, String otaOrderId, List<String> refundList, int refundQuantity) {
		String[] strs = refundList.toArray(new String[refundList.size()]);
		System.out.println(strs);
		System.out.println(refundList);
		Map<String, Object> map = responseMap();
		mapBodyMap.put("appkey", appkey);
		mapBodyMap.put("orderId", orderId);
		mapBodyMap.put("refundId", otaOrderId);
		mapBodyMap.put("refundList", strs);
		mapBodyMap.put("refundQuantity", refundQuantity);

		map.put("body", mapBodyMap);
		long timestamp = new Date().getTime();
		map.put("timestamp", timestamp);
		map.put("sign", MD5Util.MD5(appkey + JSONUtil.parseFromMap(mapBodyMap) + securitykey + timestamp));
		String result = com.guolaiwan.app.qimingxin.HttpUtil.post(url + "/open/openapi/v2/orderRefund",
				JSONUtil.parseFromMap(map), 6000, 6000, "utf-8");
		return result;
	}

	/*
	 * 取消订单
	 */
	public String cancelOrder() {
		return null;

	}

	/*
	 * Map 去空
	 */
	public static Map<String, Object> removeMapEmptyValue(Map<String, Object> paramMap) {
		Set<String> set = paramMap.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String str = it.next();
			if (paramMap.get(str) == null || paramMap.get(str) == "") {
				paramMap.remove(str);
				set = paramMap.keySet();
				it = set.iterator();
			}
		}
		return paramMap;
	}

	public static <T> List<T> castList(Object obj, Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		if (obj instanceof List<?>) {
			for (Object o : (List<?>) obj) {
				result.add(clazz.cast(o));
			}
			return result;
		}
		return null;
	}

}
