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
import com.chenxi.web.dao.UserDao;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ClassesPo;
import com.chenxi.web.po.OnlineClassesPo;
import com.chenxi.web.po.UserPo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/user")
public class UserContoller extends BaseController {
	@Autowired UserDao conn_user;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("admin/user", strMap);
		return mv;
	}
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", conn_user.countAll());
		strMap.put("data", conn_user.findAll(page, limit));
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/setAdmin.do", method= RequestMethod.POST)
	public String set(HttpServletRequest request,long userId) throws Exception {
		UserPo userPo=conn_user.get(userId);
	    userPo.setAdminFlg(1);
	    conn_user.update(userPo);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/cancelAdmin.do", method= RequestMethod.POST)
	public String cancel(HttpServletRequest request,long userId) throws Exception {
		UserPo userPo=conn_user.get(userId);
	    userPo.setAdminFlg(0);
	    conn_user.update(userPo);
		return "success";
	}
	
	
	
	
	
}
