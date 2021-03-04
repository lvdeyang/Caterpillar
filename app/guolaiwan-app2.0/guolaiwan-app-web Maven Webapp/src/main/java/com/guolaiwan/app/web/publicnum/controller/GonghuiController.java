package com.guolaiwan.app.web.publicnum.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.bussiness.javacv.FishNewLiveService;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/gonghui")
public class GonghuiController {

	@RequestMapping(value = "/video/index")
	public ModelAndView merchantIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/gonghui/home");
		return mv;
	}
	
	
	
}
