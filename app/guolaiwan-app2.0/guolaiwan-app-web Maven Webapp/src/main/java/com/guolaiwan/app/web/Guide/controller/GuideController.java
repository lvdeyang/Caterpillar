package com.guolaiwan.app.web.Guide.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/guide")
public class GuideController {

	
	
	
	
	
	@RequestMapping(value = "/visitors/guidemap")
	public ModelAndView camera(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("guide/guidemap/wanbuddhayuan");
		return mv;
	}
}
