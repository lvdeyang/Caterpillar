package com.guolaiwan.app.qimingxin;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

public class TravelService {

	public static Map<String, Object> mapBodyMap = new HashMap<String, Object>();
	public static String appkey = Param.getValue("NhappKey");
	public static String securitykey = Param.getValue("NhsecretKey");
	public static String url = Param.getValue("NhUrl");

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
		mapBodyMap.put("appkey", appkey);
		mapBodyMap.put("method", "LIST");
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

		mapBodyMap.put("startTime", "2019-12-29");
		mapBodyMap.put("endTime", "2020-12-31");
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
		String result = com.guolaiwan.app.qimingxin.HttpUtil.post(url + "/open/openapi/v2/createOrder", JSONUtil.parseFromMap(map),
				6000, 6000, "utf-8");

		System.out.println(result);
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

		String result = com.guolaiwan.app.qimingxin.HttpUtil.post(url + "/open/openapi/v2/payOrder", JSONUtil.parseFromMap(map),
				6000, 6000, "utf-8");
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
		String result = com.guolaiwan.app.qimingxin.HttpUtil.post(url + "/open/openapi/v2/orderRefund", JSONUtil.parseFromMap(map),
				6000, 6000, "utf-8");
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
