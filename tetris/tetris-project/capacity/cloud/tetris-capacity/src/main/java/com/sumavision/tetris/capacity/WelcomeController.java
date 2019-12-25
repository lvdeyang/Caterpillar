package com.sumavision.tetris.capacity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sumavision.tetris.mvc.constant.HttpConstant;
import com.sumavision.tetris.user.UserQuery;

@Controller
@RequestMapping(value = "")
public class WelcomeController {

	@Autowired
	private UserQuery userQuery;
	
	/**
	 * 需要登录后访问<br/>
	 * <b>作者:</b>ldy<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月16日 下午4:51:11
	 * @param String token 登录后的token
	 */
	@RequestMapping(value = "/index/{token}")
	public ModelAndView index(
			@PathVariable String token,
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
		//初始化一个session
		HttpSession session = request.getSession(false);
		if(session == null){
			session = request.getSession();
			session.setMaxInactiveInterval(HttpConstant.SESSION_TIMEOUT);
		}
		
		mv = new ModelAndView("web/capacity/index");
		mv.addObject(HttpConstant.MODEL_TOKEN, token);
		mv.addObject(HttpConstant.MODEL_SESSION_ID, session.getId());
		return mv;
	}
}
