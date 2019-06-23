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
import com.chenxi.web.yueba.admin.dao.ComboDao;
import com.chenxi.web.yueba.admin.dao.DaysTypeDao;
import com.chenxi.web.yueba.admin.dao.LabelDao;
import com.chenxi.web.yueba.admin.dao.RegionDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.ComboPo;
import com.chenxi.web.yueba.admin.po.LabelPo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/combo")
public class ComboContoller extends BaseController {
	@Autowired
	ComboDao conn_combo;
	@Autowired
	DaysTypeDao conn_daystype;
	@Autowired
	RegionDao conn_region;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("yuebaadmin/combo", strMap);
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", conn_combo.countAll());
		strMap.put("data", conn_combo.findAll(page, limit));
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		if(request.getParameter("id")!=null){
			conn_combo.delete(Long.parseLong(request.getParameter("id")));
		}
		return "success";
	}
	
	@RequestMapping("/add")
	public ModelAndView addClasses(){
		ModelAndView mv = new ModelAndView("yuebaadmin/addCombo");
		mv.addObject("regionList", conn_region.findAll());
		mv.addObject("daysList", conn_daystype.findAll());
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String days = request.getParameter("days");
		String region = request.getParameter("region");
		String price =request.getParameter("price");
		ComboPo comboPo=new ComboPo();
		comboPo.setDays(Integer.parseInt(days));
		comboPo.setRegion(region);
		comboPo.setPrice(Double.parseDouble(price));
		conn_combo.save(comboPo);
		return "success";
	}
	
	
}
