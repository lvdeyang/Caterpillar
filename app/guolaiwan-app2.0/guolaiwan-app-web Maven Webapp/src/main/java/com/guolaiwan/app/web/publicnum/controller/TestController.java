package com.guolaiwan.app.web.publicnum.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fishtest")
public class TestController {

	@RequestMapping(value = "/index")
	public ModelAndView merchantIndex(HttpServletRequest request, long merchantId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/test");
		return mv;
	}
}
