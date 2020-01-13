package com.guolaiwan.app.web.website.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ArticleVO;
import com.guolaiwan.bussiness.admin.po.ArticlePO;

@Controller
@RequestMapping("/yjgb")
public class TestyjgbController {
	@RequestMapping(value = "/home", method= RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap=new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("temp/home",strMap);
        return mv;
	}
	
	@RequestMapping(value = "/column", method= RequestMethod.GET)
	public ModelAndView column(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap=new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("temp/column",strMap);
        return mv;
	}
	
	@RequestMapping(value = "/login", method= RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap=new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("temp/login",strMap);
        return mv;
	}
	
}
