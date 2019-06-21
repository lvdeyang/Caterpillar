package com.chenxi.web.yueba.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pub.caterpillar.mvc.controller.BaseController;
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,long workerId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("yuebaadmin/label", strMap);
		mv.addObject("workerId",workerId);
		return mv;
	}
}
