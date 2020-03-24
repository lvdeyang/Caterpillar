package com.sumavision.tetris.api.android;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.api.exception.MobileNotMatchedException;
import com.sumavision.tetris.api.exception.MobileVerificationCodeErrorException;
import com.sumavision.tetris.api.exception.MobileVerificationCodeTimeoutException;
import com.sumavision.tetris.auth.login.LoginService;
import com.sumavision.tetris.mvc.ext.context.HttpSessionContext;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.mvc.wrapper.CachedHttpServletRequestWrapper;
import com.sumavision.tetris.mvc.wrapper.JSONHttpServletRequestWrapper;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserService;

@Controller
@RequestMapping(value = "/api/android/auth")
public class ApiAndroidAuthController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserQuery userQuery;
	
	/**
	 * 修改密码<br/>
	 * <b>作者:</b>ldy<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月16日 上午11:37:15
	 * @param username 用户民
	 * @param mobile 手机号
	 * @param password 密码
	 * @param passwordRepeat 密码重复
	 * @param verificationCode 验证码
	 * @param sessionToken session标识
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/modify/passward")
	public Object modifyPassward(
			String username,
			String mobile,
			String password,
			String passwordRepeat,
			String verificationCode,
			String sessionToken,
			HttpServletRequest request) throws Exception{
		
		HttpSession session = HttpSessionContext.get(sessionToken);
		if(session == null){
			throw new MobileVerificationCodeTimeoutException(username, mobile);
		}
		
		String number = session.getAttribute("verification-code-phone").toString();
		if(!number.equals(verificationCode)){
			throw new MobileVerificationCodeErrorException(username, mobile);
		}
		
		String phone = session.getAttribute("verification-phone").toString();
		if(!phone.equals(mobile)){
			throw new MobileNotMatchedException(mobile, phone);
		}
		
		userService.modifyPassword(username, mobile, password, passwordRepeat);
		
		return null;
	}
	
	/**
	 * 用户名密码登录<br/>
	 * <p>url:/api/terminal/auth/do/username/password/login</p>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月13日 上午9:43:12
	 * @param String username 用户名
	 * @param String password 密码
	 * @param String verificationCode 验证码
	 * @param String sessionToken session token
	 * @return String token 用户登录token
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/do/username/password/login")
	public Object doUsernamePasswordLogin(
			String username,
			String password,
			String verificationCode,
			String sessionToken,
			HttpServletRequest request) throws Exception{
		
//		HttpSession session = HttpSessionContext.get(sessionToken);
//		if(session == null){
//			throw new ImageVerificationCodeTimeoutException(username);
//		}
//		
//		String existCode = session.getAttribute("verification-code-image").toString();
//		if(!existCode.equals(verificationCode)){
//			throw new ImageVerificationCodeErrorException(username);
//		}
		
		String token = loginService.doPasswordLogin(username, password, null);
		
		return token;
	}
	
	/**
	 * 手机验证码登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:12
	 * @param String mobile 手机号
	 * @param String phoneVerifyCode 手机验证码
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/do/phone/login")
	public Object doPhonePasswordLogin(
			String mobile,
			String password,
			HttpServletRequest request) throws Exception{
		return null;
	}
	
	/**
	 * 微信登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:15
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/do/wechat/login")
	public Object doWechatLogin(HttpServletRequest request) throws Exception{
		return null;
	}
	
	/**
	 * 查询用户信息<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月2日 上午9:37:32
	 * @return UserVO 当前用户信息
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/query/user/info")
	public Object queryUserInfo(HttpServletRequest request) throws Exception{
		
		return userQuery.current();
	}
	
	/**
	 * 用户注销登录<br/>
	 * <b>作者:</b>Administrator<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月8日 上午10:37:07
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/do/logout")
	public Object doLogout(HttpServletRequest request) throws Exception{
		loginService.doLogout();
		return null;
	}
}
