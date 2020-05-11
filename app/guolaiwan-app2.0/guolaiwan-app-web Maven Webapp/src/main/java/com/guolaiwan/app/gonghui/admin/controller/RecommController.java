package com.guolaiwan.app.gonghui.admin.controller;

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

import com.guolaiwan.bussiness.gonghui.dao.ArticleDao;
import com.guolaiwan.bussiness.gonghui.dao.RecommDao;
import com.guolaiwan.bussiness.gonghui.po.ArticlePo;
import com.guolaiwan.bussiness.gonghui.po.RecommPo;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/recomm")
public class RecommController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("gonghui/recomm", strMap);
		return mv;
	}
	
	@RequestMapping(value = "/article/index", method = RequestMethod.GET)
	public ModelAndView articleindex(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("gonghui/recommarticle", strMap);
		return mv;
	}
	
	@Autowired
	RecommDao conn_recomm;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object getActivityProducts(HttpServletRequest request, HttpServletResponse response, int page, int limit)
			throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", conn_recomm.countAll());
		strMap.put("data", conn_recomm.findAll(page, limit));
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
	
	@Autowired
	ArticleDao conn_article;
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		if(request.getParameter("ids")!=null){
			String ids=request.getParameter("ids");
			
			String[] idlist=ids.split(",");
			for (String idStr : idlist) {
				long resId=Long.parseLong(idStr);
				ArticlePo articlePo=conn_article.get(resId);
				RecommPo recommPo=new RecommPo();
				recommPo.setContentId(articlePo.getId());
				recommPo.setContentName(articlePo.getTitle());
				conn_recomm.save(recommPo);
			}
		}
		
		
	
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		if(request.getParameter("id")!=null){
			conn_recomm.delete(Long.parseLong(request.getParameter("id")));
		}
		return "success";
	}

}
