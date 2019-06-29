package com.chenxi.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.dao.ArticleDao;
import com.chenxi.web.dao.OnlineClassesDao;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ClassesPo;
import com.chenxi.web.po.OnlineClassesPo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("admin/home", strMap);
		return mv;
	}
	
}
