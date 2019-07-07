package com.chenxi.web.mobile.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.dao.UserDao;
import com.chenxi.web.po.UserPo;

@Controller
@RequestMapping("/person")
public class PersonalController {
	@Autowired
	private UserDao conn_user;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
    	Object userId=session.getAttribute("userId");
    	UserPo userPo=conn_user.get(Long.parseLong(userId+""));
    	strMap.put("user", userPo);
		ModelAndView mv = new ModelAndView("mobile/personal", strMap);
		return mv;
	}
}
