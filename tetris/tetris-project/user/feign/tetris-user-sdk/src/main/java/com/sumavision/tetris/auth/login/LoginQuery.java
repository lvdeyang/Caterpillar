package com.sumavision.tetris.auth.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sumavision.tetris.mvc.ext.response.parser.JsonBodyResponseParser;

@Component
public class LoginQuery {

	@Autowired
	private LoginFeign loginFeign;
	
	/**
	 * 登录成功后重定向的页面<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月7日 下午4:19:00
	 * @param String token 登录token
	 * @return String 重定向的url
	 */
	public String queryRedirectUrl(String token) throws Exception{
		return JsonBodyResponseParser.parseObject(loginFeign.queryRedirectUrl(token), String.class);
	}
	
}
