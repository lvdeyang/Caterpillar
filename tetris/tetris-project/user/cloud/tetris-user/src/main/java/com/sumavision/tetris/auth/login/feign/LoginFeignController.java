package com.sumavision.tetris.auth.login.feign;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sumavision.tetris.auth.login.LoginQuery;
import com.sumavision.tetris.auth.login.LoginService;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "/login/feign")
public class LoginFeignController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private LoginQuery loginQuery;
	
	/**
	 * 强制用户id登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:08
	 * @param Long userId 用户名id
	 * @return String token
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/do/user/id/login")
	public Object doUserIdLogin(
			Long userId,
			HttpServletRequest request) throws Exception{
		
		return loginService.doUserIdLogin(userId);
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
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/do/password/login")
	public Object doPasswordLogin(
			String username,
			String password,
			String verifyCode,
			HttpServletRequest request) throws Exception{
		
		return loginService.doPasswordLogin(username, password, verifyCode);
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
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/do/app/secret/login")
	public Object doAppSecretLogin(
			String appId, 
			String timestamp, 
			String sign,
			HttpServletRequest request) throws Exception{
		
		return loginService.doAppSecretLogin(appId, timestamp, sign);
	}
	
	/**
	 * 登录成功后重定向的页面<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月7日 下午4:19:00
	 * @param String token 登录token
	 * @return String 重定向的url
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/query/redirect/url")
	public Object queryRedirectUrl(
			String token, 
			HttpServletRequest request) throws Exception{
		
		return loginQuery.queryRedirectUrl(token);
	}
	
	/**
	 * 用户注销登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月8日 上午10:44:21
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/do/logout")
	public Object doLogout(HttpServletRequest request) throws Exception{
		
		loginService.doLogout();
		return null;
	}
	
}
