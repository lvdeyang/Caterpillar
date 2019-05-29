package com.chenxi.web.mobile.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.dao.ArticleDao;
import com.chenxi.web.dao.ProductDao;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/cartest")
public class CarTestController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("mobile/cartest", strMap);
		return mv;
	}
	
	@Autowired
	ArticleDao conn_article;
	@Autowired 
	ProductDao conn_product;
	
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object getActivityProducts(HttpServletRequest request, HttpServletResponse response, int currPage,int pageCount,String type)
			throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		if(type.equals("recomm")){
			
		}
		else if(type.equals("article")){
			ret.put("articles", conn_article.findAll(currPage, pageCount));
		}
		else if(type.equals("product")){
			ret.put("products", conn_product.findAll(currPage, pageCount));
		}
	    
		return ret;
	}

}
