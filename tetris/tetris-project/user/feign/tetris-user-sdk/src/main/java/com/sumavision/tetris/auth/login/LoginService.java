package com.sumavision.tetris.auth.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.mvc.ext.response.parser.JsonBodyResponseParser;

@Component
public class LoginService {

	@Autowired
	private LoginFeign loginFeign;

	/**
	 * 强制用户id登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:08
	 * @param Long userId 用户名id
	 * @return String token
	 */
	public String doUserIdLogin(Long userId) throws Exception{
		return JsonBodyResponseParser.parseObject(loginFeign.doUserIdLogin(userId), String.class);
	}
	
	/**
	 * 用户名密码登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:08
	 * @param String username 用户名
	 * @param String password 密码
	 * @param String verifyCode 验证码
	 * @return String token
	 */
	public String doPasswordLogin(
			String username,
			String password,
			String verifyCode) throws Exception{
		JSONObject response = loginFeign.doPasswordLogin(username, password, verifyCode);
		return JsonBodyResponseParser.parseObject(response, String.class);
	}
	
	/**
	 * 开发者登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月22日 下午4:30:59
	 * @param String appId 开发者id
	 * @param String timestamp 时间戳
	 * @param String sign 签名
	 * @return String token
	 */
	public String doAppSecretLogin(
			String appId,
			String timestamp,
			String sign) throws Exception{
		JSONObject response = loginFeign.doAppSecretLogin(appId, timestamp, sign);
		return JsonBodyResponseParser.parseObject(response, String.class);
	}
	
	/**
	 * 用户注销登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月8日 上午10:47:08
	 */
	public void doLogout() throws Exception{
		loginFeign.doLogout();
	}
	
}
