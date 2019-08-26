package com.sumavision.tetris.auth.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private LoginQuery loginQuery;
	
	/**
	 * 用户名密码登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:08
	 * @param String username 用户名
	 * @param String password 密码
	 * @param String verifyCode 验证码
	 */
	@RequestMapping(value = "/do/password/login")
	public void doPasswordLogin(
			String username,
			String password,
			String verifyCode,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		String token = loginService.doPasswordLogin(username, password, verifyCode);
		String redirectUrl = loginQuery.queryRedirectUrl(token);
		response.sendRedirect(redirectUrl);
	}
	
	/**
	 * 手机验证码登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:12
	 * @param String mobile 手机号
	 * @param String phoneVerifyCode 手机验证码
	 */
	@RequestMapping(value = "/do/phone/login")
	public void doPhoneLogin(
			String mobile,
			String phoneVerifyCode,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
	}
	
	/**
	 * 微信登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:15
	 */
	@RequestMapping(value = "/do/wechat/login")
	public void doWechatLogin(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
	}
	
	/**
	 * 用户注销登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月8日 上午10:48:58
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/do/logout")
	public Object doLogout(HttpServletRequest request) throws Exception{
		
		loginService.doLogout();
		return null;
	}
	
}
