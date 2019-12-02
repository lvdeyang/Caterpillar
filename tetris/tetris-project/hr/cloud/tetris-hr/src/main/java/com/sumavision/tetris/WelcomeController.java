package com.sumavision.tetris;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sumavision.tetris.menu.MenuQuery;
import com.sumavision.tetris.menu.MenuVO;
import com.sumavision.tetris.mvc.constant.HttpConstant;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "")
public class WelcomeController {

	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private MenuQuery menuQuery;
	
	/**
	 * 需要登录后访问<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午4:51:11
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
		mv = new ModelAndView("web/hr/index");
		mv.addObject(HttpConstant.MODEL_TOKEN, token);
		mv.addObject(HttpConstant.MODEL_SESSION_ID, session.getId());
		return mv;
	}
	
	/**
	 * 页面框架初始化数据查询<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午4:51:49
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/prepare/app")
	public Object prepareApp(HttpServletRequest request) throws Exception{
		
		Map<String, Object> appInfo = new HashMap<String, Object>();
		
		//用户信息
		UserVO user = userQuery.current();
		
		appInfo.put("user", user);
		
		//菜单信息
		List<MenuVO> menus = menuQuery.permissionMenus(user);
		
		appInfo.put("menus", menus);
		
		return appInfo;
	}

}
