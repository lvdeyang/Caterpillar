package com.chenxi.web.mobile.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.spider.service.XinlangCepingCollection;
import com.chenxi.web.dao.ArticleContentDao;
import com.chenxi.web.dao.ArticleDao;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/spider")
public class SpiderController {
	

	@Autowired
	private XinlangCepingCollection xinlangCollection;
	
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/xinlangceping", method = RequestMethod.GET)
	public Object getxinlancepings(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		xinlangCollection.start(4);
		return ret;
	}
	
	
	
}
