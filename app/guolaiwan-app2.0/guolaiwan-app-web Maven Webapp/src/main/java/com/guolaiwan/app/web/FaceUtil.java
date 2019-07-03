package com.guolaiwan.app.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.aliyuncs.utils.Base64Helper;

public class FaceUtil {

	private static final String ak_id = "LTAIrM29ytRXqalj";

	private static final String ak_secret = "8gYV3BG9rLRtKhty29VI9ETpsSL5JM";

	private static final String url = "	https://dtplus-cn-shanghai.data.aliyuncs.com/face/verify";

	/*
	 * 计算MD5+BASE64
	 */
	public static String MD5Base64(String s) {
		if (s == null)
			return null;
		String encodeStr = "";
		byte[] utfBytes = s.getBytes();
		MessageDigest mdTemp;
		try {
			mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(utfBytes);
			byte[] md5Bytes = mdTemp.digest();
			Base64Helper b64Encoder = new Base64Helper();
			encodeStr = b64Encoder.encode(md5Bytes);
		} catch (Exception e) {
			throw new Error("Failed to generate MD5 : " + e.getMessage());
		}
		return encodeStr;
	}

	/*
	 * 计算 HMAC-SHA1
	 */
	public static String HMACSha1(String data, String key) {
		String result;
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			result = (new Base64Helper()).encode(rawHmac);
		} catch (Exception e) {
			throw new Error("Failed to generate HMAC : " + e.getMessage());
		}
		return result;
	}

	/*
	 * 等同于javaScript中的 new Date().toUTCString();
	 */
	public static String toGMTString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.UK);
		df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
		return df.format(date);
	}

	/*
	 * 发送POST请求
	 */
	public static String sendPost(String url1, String url2) throws Exception {
		String body = "{\"content_1\": \"" + url1 + "\", \"content_2\":\"" + url2 + "\", \"type\":\"" + "1" + "\"}";
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		int statusCode = 200;
		try {
			URL realUrl = new URL(url);
			/*
			 * http header 参数
			 */
			String method = "POST";
			// 返回值类型
			String accept = "application/json";
			// 请求内容类型
			String content_type = "application/json";
			String path = realUrl.getFile();
			// GMT时间
			String date = toGMTString(new Date());
			// 1.对body做MD5+BASE64加密
			String bodyMd5 = MD5Base64(body);
			String stringToSign = method + "\n" + accept + "\n" + bodyMd5 + "\n" + content_type + "\n" + date + "\n"
					+ path;
			// 2.计算 HMAC-SHA1
			String signature = HMACSha1(stringToSign, ak_secret);
			// 3.得到 authorization header
			String authHeader = "Dataplus " + ak_id + ":" + signature;
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("Accept", accept);
			conn.setRequestProperty("Content-type", content_type);
			conn.setRequestProperty("Date", date);
			// 认证信息
			conn.setRequestProperty("Authorization", authHeader);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(body);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			statusCode = ((HttpURLConnection) conn).getResponseCode();
			if (statusCode != 200) {
				in = new BufferedReader(new InputStreamReader(((HttpURLConnection) conn).getErrorStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			}
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if (statusCode != 200) {
			throw new IOException("\nHttp StatusCode: " + statusCode + "\nErrorMessage: " + result);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		String url1 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2465377548,3910830278&fm=26&gp=0.jpg";
		String url2 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2465377548,3910830278&fm=26&gp=0.jpg";
		String response = sendPost(url1, url2);

		System.out.println(response.toString());
	}
}
