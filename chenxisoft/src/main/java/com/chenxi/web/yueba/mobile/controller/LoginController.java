package com.chenxi.web.yueba.mobile.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenxi.web.weixin.JsTicketUtil;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.weixin.constants.WXContants;
@Controller
@RequestMapping("/login")
public class LoginController {
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/wx/prev")
	public Object preScan(HttpServletRequest request, String url) {
		url = url.replace("FISH", "&");
		System.out.println(url);
		Map ret = new HashMap();
		String jsapi_ticket = JsTicketUtil.getJsapiTicket();
		String nonce_str = JsTicketUtil.create_nonce_str();
		String timestamp = JsTicketUtil.create_timestamp();
		String string1;
		String signature = "";
		int length = url.indexOf("#");
		String uri = url;
		if (length > 0) {
			uri = url.substring(0, length);// 当前网页的URL，不包含#及其后面部分
		}
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = JsTicketUtil.byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println(signature);
		System.out.println(WXContants.AppId);
		ret.put("appId", WXContants.AppId);
		ret.put("url", uri);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}
}
