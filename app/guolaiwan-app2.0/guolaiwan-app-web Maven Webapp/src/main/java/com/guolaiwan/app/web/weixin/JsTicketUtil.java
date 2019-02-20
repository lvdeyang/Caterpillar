package com.guolaiwan.app.web.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class JsTicketUtil {

	/**
	 * 得到jsApi-ticket
	 * 
	 * @return
	 */

	public static String getJsapiTicket() {
        CacheTokenBo bo = CacheToken.getInstance().fetchTokenBo();
        if(bo==null){
        	String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?";
    		String params = "grant_type=client_credential&appid=" + WxConfig.appId + "&secret=" + WxConfig.appsrcret + "";
    		String result = httpGet(requestUrl + params);
    		String accessToken = com.alibaba.fastjson.JSONObject.parseObject(result).getString("access_token");

    		requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";
    		params = "access_token=" + accessToken + "&type=jsapi";
    		result = httpGet(requestUrl + params);
    		String jsapi_ticket = com.alibaba.fastjson.JSONObject.parseObject(result).getString("ticket");
    		bo=new CacheTokenBo();
    		bo.setFecthTime(new Date());
    		bo.setTicket(jsapi_ticket);
    		bo.setToken(accessToken);
    		CacheToken.getInstance().setBo(bo);
    		return jsapi_ticket;
        }else{
            return bo.getTicket();
        }
		
		
	}

	public static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	public static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
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
