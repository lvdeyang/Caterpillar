package com.guolaiwan.app.web.map.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gaode")
public class GaodeMapController {
	@RequestMapping(value = "/index")
	public ModelAndView merchantIndex(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("map/index"); 
		return mv;
	}
}
