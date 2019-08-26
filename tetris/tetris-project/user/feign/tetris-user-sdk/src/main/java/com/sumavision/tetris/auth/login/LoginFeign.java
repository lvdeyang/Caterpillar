package com.sumavision.tetris.auth.login;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.config.feign.FeignConfiguration;

@FeignClient(name = "tetris-user", configuration = FeignConfiguration.class)
public interface LoginFeign {

	/**
	 * 强制用户id登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:08
	 * @param Long userId 用户名id
	 * @return String token
	 */
	@RequestMapping(value = "/login/feign/do/user/id/login")
	public JSONObject doUserIdLogin(@RequestParam("userId") Long userId) throws Exception; 
	
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
	@RequestMapping(value = "/login/feign/do/password/login")
	public JSONObject doPasswordLogin(
			@RequestParam("username") String username, 
			@RequestParam("password") String password, 
			@RequestParam("verifyCode") String verifyCode) throws Exception;
	
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
	@RequestMapping(value = "/login/feign/do/app/secret/login")
	public JSONObject doAppSecretLogin(
			@RequestParam("appId") String appId, 
			@RequestParam("timestamp") String timestamp, 
			@RequestParam("sign") String sign) throws Exception;
	
	/**
	 * 登录成功后重定向的页面<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月7日 下午4:19:00
	 * @param String token 登录token
	 * @return String 重定向的url
	 */
	@RequestMapping(value = "/login/feign/query/redirect/url")
	public JSONObject queryRedirectUrl(@RequestParam("token") String token) throws Exception;
	
	/**
	 * 用户注销登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月8日 上午10:46:19
	 */
	@RequestMapping(value = "/login/feign/do/logout")
	public JSONObject doLogout() throws Exception;
}
