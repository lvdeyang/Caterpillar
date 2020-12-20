package com.guolaiwan.app.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.guolaiwan.app.interfac.util.HttpUtils;


public class GatewayUtil {
	private static String appId="20201209786166834742165504";
	private static String channel_code="9f05907b016dc75bf6f06350055b2024";
	//private static String privateKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCSMT/njiTB8jvTr291k0RSPb5pjzcTZ4AOQfmjLNE8MYX74Bo7X7FbQzsmUGhW8hvjG3MRe58yt1RZ9z+I5ELqUzF3cO0fVO9jPxCoGqUzrG2DYqWS5NmghLsTx2PR0BUY9LUHp0Za60YKRaM6ibp4yi0wd6I10zawQewW7QWLd6uhX4qTVQZ8KD2b859/beStaRhfSkyK/M3DPJK53U7DOj4hPo4YFNJwWRnFf3kQULgtNVyyhRk4IU3yvHkL4iVgddvwZPzvzW18yGI0Kg5BY+75J6WG6vSiZSm4IYygbYG0K/u3OHR5UZ1fSf++lu0KoK+46Gu4LWyQ/H8AvrdLAgMBAAECggEAMYRI9u9od+DKbtr/T72/6HNNaYe+ye3GrA2gtqpZfJ/xl+zCNdYlxpmxETsu/DQpSfVkNkm/FHiQM6DRflaCa8gd+yzexDwKkX0s8SOFTUlcFqJ1HpoxNHOAwiRJJSnr23YXUy5PYEBMivInZY59AKjKQZ5j7QNLhMvvYZyKwkAVbZ53LbO+Pivg+GlSXd0FYArA7XCUucMR8mSczj7DAfQ36OWpF10WNwrft6YXRwnblsFU4LiTyJcMDFRcxOrx5+XYRgIdsBwIyxhuJGdXp8mF6fTkGtzisRZg9qbUzXHE2O35vAEHEOmcKxNO0uG3Rm0IFibrQbVq+cQFXqFQ8QKBgQDC9ypClcnzKyMhhUNOvGNAAjnD0zbC3KMOEu+huXAScxUFeAKy6CwCCO667pm2pAJuWWOXv2WDSitFHDrY4IJiFiU5a6iPXl1OIp+W1Fjerl6cO6pD7CSDHmNKaZPRb2woUR5pBS32KQRAXM1OvKF4kdEUSdbeAfduEFq5KNqvJwKBgQC/9VUf/lcEn/5cjWbSKq4pKmkktTUGyE0cySiH4kkZ5EFue3RXxfI02eX3wVtbobwSO72FukBDXa3VUUzovZxA0vqTqo7ycZx9SJHRnYW3TLRkHT84UiGxurSafdzT7+iqhaQoXYtqSzhWqK1t4sqk7h3iZEMyUgf+jtoD/xINPQKBgQCoZxX5rahigkA2FAWvXMnd4A5FN4t1hV7SKUkTSDmltgHSV3b9Uc2xdEXA8ZeUStZOuqdry8bDQK1fZKLv8fys/LPqhTNzou61LvLqNUEOF09hY6gOQ0rGdtkMzWUA4l1p9aduzyqEFam2bnJoMEbI/iq6e4089jXiu/2hMciywQKBgQCyJGd/bXYsIJ7aHY/SczF1mppBKrmvNav6juRCVsoocSAX/BIYcuc4DQ/SQWsbOpljAnX76MFCw2lTkkOhqnWb4zAQYjSO4sirq/FHarxxQ4oVbt8A0ZfbbL22sh+BwB9YQPX7T/TWDECYi6mHVPVi4mhxdUK3LFGm98Nz+f8ysQKBgQCGFYKorOw9VnVpGkC9PYDD72AQlhhcnvNWBwMCOLKBbhJuaxvQI/Gh/3LbadwDzhRIiV434uoP2prDVldIhZYB2e4DxsJdBpA2qpxW49DQbwb5149BrxoG1+mXuaT3PZZRg+taIhTDJ2Pa3CtiLdjAPuVrOYc5s9f/tL93HhBqIg==";
	private static String privateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCU7xzzak1yxaK7hWo9FYgX+cBs0ckem2YRJpf+PgD54OWCtwJXFaxZ2aKxWjU8KSwz9is6mW0W3fob3PIPRTyr92JVRK0laOF8PSTT2nlQ/ULVOv4NJT6AfHop0KGD7lqi+OtA4ieUNRHM3X1wQ4Re4mCTMovWiXW+lTPgujoyxnKgpvGXNvP4BJlEWMkEmeFWg+rFlfsjekawg9mONdSvY1gUfjDwugeJJrKR8thz5gIazbwn7PfH4gv8UDy7pJt1pdGk7WsNynqM4+2spW11hc5WwIWQ674VwEIm1V75WNgRq4QGyjci3gj0QDrSAW/CaYF8clHvlJiohOQCb9XZAgMBAAECggEASuEllySdJ9VCCjH7xhZrRFKnco7PJXpQCNN0m7kk2H1AJ0ZIAOy+qLYJXE3UchjpRn7AIUg+Vr7oHtYpRZAz/un0IJbUzvg6VjKPr4wqbpPG5FoynP1avVLYQOnKKwDNxR6NeSO7boJ4Hp5FkQoBJYt/rsI9GHIoJtfxK022MQxbs4X8PM4pWce1jMe6SghuMxAuHCg7l5QNERIueo5M7qO/MBphJHX2FSlG+tAxV0ONbg6rCyUBSgjaFcGDjoDaBhK7f15Nh4Qo9Bk0pPUNLD+gR8ut8ipTEPkiJO12q7ZifKYdqnoeQSWyqD6RgThpVxLSwABM1B1k/3xjKID1YQKBgQDbFKL8V4h/XD1JWw4SM/NNB9doQ1z0L04LavmwJ/iNt07BSO4QdWW3sB4VjR/0Ih4b8qNd5oekb5KXXOUqAtnoO9UhpxQOOiq8DMMZlvldKErkxNrh4Gavi/DojPXRZKe854IFGj2qfWZzsjBietUJUt0sDl6Rfje+qtmG61/vJQKBgQCuCEflIQVA4XIiXROHJOlU7c19RP9lZkK5/xvPNQG4rIao8g5CpRK/+1kaSoVyITsk57nMHZAti/m2TNFStcYsm2B0blXSEUixVlPx6u1zEjFCPPp2mPIJHurwI7RbzR7C2aq+hPBNWPB9u6CLGWxeXA+2+bsU3IARsLYEpwb3pQKBgESNSY7CaSDJIRhCGL4TaW9SI9IpOf3ii35BJA4+A6V8lXUft/aIdaKrxk4iEbyXrGo8W2g3iXwnuWwJkl+58qubhU55dGobQ2ICcE4ikrbWs9Cv1HnXeOo3VNO/v++cmmwe2O11z5P4l0VenYtDOkQnnLN7b5YBM7pCxbxVDHjNAoGAeJ9ktXtdmMqpwQeiMVl0IFlyxBC+tBlcxm37aHLk7osmCpM7LxYbGNWtvHtKYwCFbd7u+HrFI8yRF7nGm/1BjkljYU/YS/GO4Ntk85hRXCCP48lAEFOR7qBvVmJo7/gMCiG0JrkdlX6/sWYKsF6+rehBPCF7ecobOiexASiDu8ECgYBsslB/cdK6bgPhpB0tWTZh29QRQXZzH3wdDHjb4UF/he5D01PQqyj2h2quFZYN5hD/PYSNlm4jrXqtKePOBF5dI0ZpGWRgmfZ6Y3ttQG22sAjjfJlwN1EDo+fffWexcTb/0T+M3FFXr9qKOy6VDaeQvF2XTQCBt2+GnIR5OV+7lA==";
	//test
	private static String url="http://gateway.kycctech.cn";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String orderNo="0009";
			JSONObject json=testOpenInvocie("buyerTaxNo123123", "杨彪", "河北省遵化市法院13819078765", "22223333", 
					"12.00", "", "12.00", "1", orderNo,"次","旅客运输服务");
			JSONObject resJson= json.getJSONObject("response");
			testFormatBuild(resJson.getString("invoiceCode"),resJson.getString("invoiceNo"),orderNo);
			//testFormatBuild("013001610011", "75127318");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static JSONObject testOpenInvocie(String buyerTaxNo,String buyerName,String buyerAddressPhone
			,String buyerBankAccount,String totalprice,String texPrice,String totalTexPrice,String count,
			String orderNo,String goodsUnit,String goodsName) throws Exception {
	    // 公共请求参数
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("app_id", appId);
	    params.put("method", "zhuoyan.openInvoice");
	    params.put("format", "json");
	    params.put("charset", "utf-8");
	    params.put("sign_type", "RSA2");
	    params.put("channel_code", channel_code);
	    params.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
	    params.put("version", "1.0");

	    // 业务参数
	    Map<String, Object> bizContent = new HashMap<>();


	    bizContent.put("sellerTaxNo", "9113020080479255X6");
	    bizContent.put("sellerName", "唐山交通运输集团有限公司第二客运分公司");
	    bizContent.put("sellerBankAccount", "农行唐山广场支行5074400140001884");
	    bizContent.put("sellerAddressPhone", "唐山市路南区车站路134号，03152826646");
	    bizContent.put("deviceType", "0");
	    bizContent.put("serialNo", orderNo);
	    bizContent.put("invoiceSpecialMark", "00");
	    bizContent.put("invoiceTypeCode", "026");
	    bizContent.put("invoiceTerminalCode", "929908472318");
	    //客户信息
	    //bizContent.put("buyerTaxNo", "buyerTaxNo123123");
	    //bizContent.put("buyerName", "李购买");
	    //bizContent.put("buyerAddressPhone", "北京朝阳13410002000");
	    //bizContent.put("buyerBankAccount", "22223333");
	    bizContent.put("buyerTaxNo", buyerTaxNo);
	    bizContent.put("buyerName", buyerName);
	    bizContent.put("buyerAddressPhone", buyerAddressPhone);
	    bizContent.put("buyerBankAccount", buyerBankAccount);
	    
	    
	    bizContent.put("drawer", "刘嘉蔷");
	    bizContent.put("checker", "范军英");
	    bizContent.put("payee", "刘婷婷");
	    bizContent.put("invoiceType", "0");
	    bizContent.put("invoiceListMark", "0");
//	        bizContent.put("redInfoNo", ""); // -- 红票相关
//	        bizContent.put("originalInvoiceCode", "013001310011");
//	        bizContent.put("originalInvoiceNo", "");
	    bizContent.put("taxationMode", "0");
	    bizContent.put("deductibleAmount", "");
	    //商品价格信息
	    //bizContent.put("invoiceTotalPrice", "145.63");
	    //bizContent.put("invoiceTotalTax", "4.37");
	    //bizContent.put("invoiceTotalPriceTax", "150.00");
	    
	    bizContent.put("invoiceTotalPrice", totalprice);
	    bizContent.put("invoiceTotalTax", texPrice);
	    bizContent.put("invoiceTotalPriceTax", totalTexPrice);
	    
	    bizContent.put("taxDiskNo", "S/N33:33-929908472318");
	    bizContent.put("taxDiskKey", "88888888");
	    bizContent.put("taxDiskPassword", "11111111");
	    
	    bizContent.put("goodsCodeVersion", "");
	    bizContent.put("consolidatedTaxRate", "0.03");
	    bizContent.put("notificationNo", "");
	    bizContent.put("remarks", "");
	    //流水号
	    bizContent.put("reqSerialNumber", orderNo);
	    bizContent.put("autoSplit", "false");

	    List list = new ArrayList();
	    Map<String, Object> detail = new HashMap<>();
	    detail.put("goodsLineNo", "1");
	    detail.put("goodsLineNature", "0");
	    //商品编码
	    detail.put("goodsCode", "3010101020101010000");  // 商品编码不能小于19位
	    //自行编码
	    detail.put("goodsExtendCode", "112211");
	    detail.put("goodsName", goodsName);
	    //商品税目
	    detail.put("goodsTaxItem", "");
	    //规格型号
	    detail.put("goodsSpecification", "");
	    //计量单位
	    detail.put("goodsUnit", goodsUnit);
	    //商品数量
	    //detail.put("goodsQuantity", "1");
	    //单价
	    //detail.put("goodsPrice", "145.63");
	    //总金额
	    //detail.put("goodsTotalPrice", "150.00");
	    //税额
	    //detail.put("goodsTotalTax", "4.37");
	    
	    detail.put("goodsQuantity", count);
	    detail.put("goodsPrice", totalprice);
	    detail.put("goodsTotalPrice", totalTexPrice);
	    detail.put("goodsTotalTax", texPrice);
	    
	    detail.put("goodsTaxRate", "0");
	    detail.put("goodsDiscountLineNo", "");
	    detail.put("priceTaxMark", "1");
	    detail.put("vatSpecialManagement", "");
	    detail.put("freeTaxMark", "3");
	    detail.put("preferentialMark", "0");
	    list.add(detail);
	    bizContent.put("invoiceDetailsList", list);
	    params.put("biz_content", JSON.toJSONString(bizContent));

	    System.out.println("----------- 请求信息 -----------");
	    System.out.println("请求参数：" + params);
	    System.out.println("商户秘钥：" + privateKey);
	    String content = AlipaySignature.getSignContent(params);
	    System.out.println("待签名内容：" + content);
	    String sign = AlipaySignature.rsa256Sign(content, privateKey, "utf-8");
	    System.out.println("签名(sign)：" + sign);

	    params.put("sign", sign);

	    System.out.println("----------- 返回结果 -----------");
	    org.apache.http.HttpResponse response=HttpUtils.doPost(url, "", "", new HashMap<String, String>(), null, params);
	    
	    String responseData = EntityUtils.toString(response.getEntity());
	    System.out.println(responseData);
	    JSONObject json=JSONObject.parseObject(responseData);
	    return json;
	}

	
	public static void testFormatBuild(String invoiceCode,String invoiceNo,String orderNo) throws Exception {
	    // 公共请求参数
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("app_id", appId);
	    params.put("method", "zhuoyan.formatBuild");
	    params.put("format", "json");
	    params.put("charset", "utf-8");
	    params.put("sign_type", "RSA2");
	    params.put("channel_code", channel_code);
	    params.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
	    params.put("version", "1.0");

	    // 业务参数
	    Map<String, Object> bizContent = new HashMap<>();

	    bizContent.put("sellerTaxNo","9113020080479255X6");
	    bizContent.put("serialNo",orderNo);
	    bizContent.put("invoiceCode",invoiceCode);
	    bizContent.put("invoiceNo",invoiceNo);
	    bizContent.put("pushType","0");
	    bizContent.put("buyerEmail","");
	    bizContent.put("buyerPhone","");
	    bizContent.put("pushChannel","");
	    bizContent.put("custdata","");


	    params.put("biz_content", JSON.toJSONString(bizContent));

	    System.out.println("----------- 请求信息 -----------");
	    System.out.println("请求参数：" + params);
	    System.out.println("商户秘钥：" + privateKey);
	    String content = AlipaySignature.getSignContent(params);
	    System.out.println("待签名内容：" + content);
	    String sign = AlipaySignature.rsa256Sign(content, privateKey, "utf-8");
	    System.out.println("签名(sign)：" + sign);

	    params.put("sign", sign);

	    System.out.println("----------- 返回结果 -----------");
        org.apache.http.HttpResponse response=
        		HttpUtils.doPost(url, "", "", new HashMap<String, String>(), null, params);
	    
	    String responseData = EntityUtils.toString(response.getEntity());
	    System.out.println(responseData);
	}

}
