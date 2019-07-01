package com.chenxi.web.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.po.ClassesPo;

@Controller
@RequestMapping("/login")
public class LoginContoller {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("admin/login", strMap);
		return mv;
	}
	@ResponseBody
	@RequestMapping(value="/login.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username==null||password==null){
			return "error";
		}
		if(username.equals("sa")&&password.equals("xiaoqingrd")){
			HttpSession session=request.getSession();
			session.setAttribute("admin", "sa");
			session.setAttribute("type", "admin");
			return "success";
		}else{
			return "error";
		}
	}
}
