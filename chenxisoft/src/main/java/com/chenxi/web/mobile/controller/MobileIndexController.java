package com.chenxi.web.mobile.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenxi.web.dao.CacheDao;
import com.chenxi.web.dao.UserDao;
import com.chenxi.web.po.CachePo;
import com.chenxi.web.po.UserPo;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.communication.http.client.HttpClient;
import pub.caterpillar.weixin.constants.WXContants;

@Controller
@RequestMapping("/login")
public class MobileIndexController {
	boolean istest=true;
	@Autowired
	CacheDao conn_cache;
	
	@RequestMapping(value = "/mobile/index1", method = RequestMethod.GET)
	public ModelAndView index1(HttpServletRequest request, String rUrl) throws Exception {

		ModelAndView mv = null;
		if (!istest) {
			String gState = new Date().getTime() + "";
			if (rUrl != null) {

				CachePo cachePo = new CachePo();
				cachePo.setWxKey(gState);
				cachePo.setWxVal(rUrl);
				conn_cache.save(cachePo);
			}

			String redirect = "http://www.yueba.net.cn/chexisoft/login/mobile/index2";
			redirect = URLEncoder.encode(redirect);
			StringBufferWrapper weixinLogin = new StringBufferWrapper()
					.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").append("")
					.append("&redirect_uri=").append(redirect)
					.append("&response_type=code&scope=snsapi_userinfo&state=" + gState + "#wechat_redirect");
			mv = new ModelAndView("redirect:" + weixinLogin.toString());
			System.out.println(weixinLogin.toString());
		} else {
			String gState = new Date().getTime() + "";
			CachePo cachePo = new CachePo();
			cachePo.setWxKey(gState);
			cachePo.setWxVal(rUrl);
			conn_cache.save(cachePo);
			mv = new ModelAndView("redirect:/login/mobile/index2");
		}
		return mv;
	}

	@Autowired
	private UserDao conn_user;

	@RequestMapping(value = "/mobile/index2", method = RequestMethod.GET)
	public ModelAndView index2(String code, String state, HttpServletRequest request) throws Exception {
		List<CachePo> cachePos = conn_cache.findByField("wxKey", state);

		String rUrl = "";
		if (!cachePos.isEmpty()) {
			rUrl = cachePos.get(0).getWxVal();
		}
		conn_cache.delete(cachePos.get(0));
		ModelAndView mv = null;
		String openid = "";
		String nickname = "";
		String headimgurl = "";
		// 获取授权access_token
		if (!istest) {
			JSONObject params = new JSONObject();
			params.put("appid", "");
			params.put("secret", "");
			params.put("code", code);
			params.put("grant_type", "authorization_code");
			String result = HttpClient.get("https://api.weixin.qq.com/sns/oauth2/access_token", params);
			JSONObject accessTokenInfo = JSON.parseObject(result);
			String access_token = accessTokenInfo.getString("access_token");
			openid = accessTokenInfo.getString("openid");
			// 拉取用户详细信息
			params = new JSONObject();
			params.put("access_token", access_token);
			params.put("openid", openid);
			params.put("lang", "zh_CN");
			result = HttpClient.get("https://api.weixin.qq.com/sns/userinfo", params);
			JSONObject userInfo = JSON.parseObject(result);
			nickname =userInfo.getString("nickname");
			headimgurl = URLDecoder.decode(userInfo.getString("headimgurl"));
		} else {
			openid = "opVUYv6B2eIPzpj4yCJBonei5yMg";
		}
		/**/
		// 测试

		UserPo user = null;
		List<UserPo> users = conn_user.findUserByOpenId(openid);
		if (users != null) {
			for (UserPo userPO2 : users) {
				user = userPO2;
				break;
			}
		}

		if (user == null) {
			user = new UserPo();
			user.setUpdateTime(new Date());
			user.setOpenId(openid);
			// 测试
			if (!istest) {
				user.setHeadPic(headimgurl);
				user.setNickName(nickname);
			}
			conn_user.save(user);
		}
		HttpSession session = request.getSession();
		session.setAttribute("userId", user.getId());
		session.setAttribute("openid", openid);
		mv = new ModelAndView("redirect:" + rUrl);
		return mv;
	}
}
