package com.guolaiwan.app.web.weixin;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import pub.caterpillar.communication.http.client.HttpClient;

public class SendMsgUtil {
	
	
	public static void sendTemplate(String data) {
		try {
			System.out.println(data);
			String result = HttpClient.post("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+getToken(), data);
		    System.out.println(result);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	
	public static String getToken() {

		String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?";
		String params = "grant_type=client_credential&appid=" + WxConfig.appId + "&secret=" + WxConfig.appsrcret + "";
		String result = httpGet(requestUrl + params);
		String accessToken = com.alibaba.fastjson.JSONObject.parseObject(result).getString("access_token");
		return accessToken;
	}
	public static String httpGet(String url) {
		// get请求返回结果
		String strResult = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				strResult = EntityUtils.toString(response.getEntity());
			} else {
				System.out.println("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			System.out.println("get请求提交失败:" + url);
		}
		return strResult;
	}
}
