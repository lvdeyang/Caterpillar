package com.guolaiwan.app.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/home")
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("admin/home");
		return mv;
	}
	
}
