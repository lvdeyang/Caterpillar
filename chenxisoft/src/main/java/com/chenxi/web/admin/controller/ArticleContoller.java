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
@RequestMapping("/article")
public class ArticleContoller extends BaseController {
	@Autowired ArticleDao conn_article;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("admin/article", strMap);
		return mv;
	}
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", conn_article.countAll());
		strMap.put("data", conn_article.findAll(page, limit));
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
	
	@Autowired
	OnlineClassesDao conn_onlineclasses;
	
	@ResponseBody
	@RequestMapping(value="/addonline.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		if(request.getParameter("ids")!=null){
			String ids=request.getParameter("ids");
			String classIdString=request.getParameter("classId");
			String[] idlist=ids.split(",");
			for (String idStr : idlist) {
				long resId=Long.parseLong(idStr);
				long classId=Long.parseLong(classIdString);
				ArticlePo articlePo=conn_article.get(resId);
				OnlineClassesPo onlineClassesPo=new OnlineClassesPo();
				onlineClassesPo.setClassesId(classId);
				onlineClassesPo.setContentId(articlePo.getId());
				onlineClassesPo.setContentName(articlePo.getTitle());
				onlineClassesPo.setContentMouduler(articlePo.getMoudular());
				conn_onlineclasses.save(onlineClassesPo);
			}
		}
		
		
	
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/delarticle.do", method= RequestMethod.POST)
	public String delArticle(HttpServletRequest request) throws Exception {
		if(request.getParameter("id")!=null){
			conn_article.delete(Long.parseLong(request.getParameter("id")));
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		if(request.getParameter("id")!=null){
			conn_onlineclasses.delete(Long.parseLong(request.getParameter("id")));
		}
		
		
	
		return "success";
	}
	
	
}
