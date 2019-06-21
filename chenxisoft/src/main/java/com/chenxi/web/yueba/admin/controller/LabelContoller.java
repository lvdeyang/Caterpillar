package com.chenxi.web.yueba.admin.controller;

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
import com.chenxi.web.dao.ProductDao;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ClassesPo;
import com.chenxi.web.po.OnlineClassesPo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.yueba.admin.dao.LabelDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.LabelPo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/label")
public class LabelContoller extends BaseController {
	@Autowired
	LabelDao conn_label;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("yuebaadmin/label", strMap);
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", conn_label.countAll());
		strMap.put("data", conn_label.findAll(page, limit));
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		if(request.getParameter("id")!=null){
			conn_label.delete(Long.parseLong(request.getParameter("id")));
		}
		return "success";
	}
	
	@RequestMapping("/add")
	public ModelAndView addClasses(){
		ModelAndView mv = new ModelAndView("yuebaadmin/addLabel");
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		LabelPo labelPo=new LabelPo();
		labelPo.setName(name);
		labelPo.setCode(code);
		conn_label.save(labelPo);
		return "success";
	}
	
	
}
