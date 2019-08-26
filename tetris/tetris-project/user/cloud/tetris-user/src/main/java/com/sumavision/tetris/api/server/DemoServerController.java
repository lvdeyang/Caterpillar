package com.sumavision.tetris.api.server;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserQuery;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/server/demo")
public class DemoServerController {

	@Autowired
	private UserQuery userQuery;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/test")
	public Object test(HttpServletRequest request) throws Exception{
		return userQuery.current();
	}
	
}
