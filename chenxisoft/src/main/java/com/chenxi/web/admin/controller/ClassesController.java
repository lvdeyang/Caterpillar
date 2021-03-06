package com.chenxi.web.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.dao.ClassesDao;
import com.chenxi.web.po.ClassesPo;

@Controller
@RequestMapping("/classes")
public class ClassesController {
	@Autowired
	ClassesDao conn_classes;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("admin/classes", strMap);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list() throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("list", conn_classes.findAll());
		return strMap;
	}
	

	@RequestMapping("/add")
	public ModelAndView addClasses(){
		ModelAndView mv = new ModelAndView("admin/addClass");
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		ClassesPo classesPo=new ClassesPo();
		classesPo.setCode(code);
		classesPo.setName(name);
		conn_classes.save(classesPo);
		return "success";
	}
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		if(request.getParameter("id")!=null){
			conn_classes.delete(Long.parseLong(request.getParameter("id")));
		}

		return "success";
	}
}
