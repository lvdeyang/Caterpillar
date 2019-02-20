package com.guolaiwan.app.web.admin.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.bussiness.admin.dao.LiveDAO;
import com.guolaiwan.bussiness.admin.enumeration.CompanyType;
import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.enumeration.LiveType;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.LivePO;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/live")
public class LiveController extends BaseController {

	@Autowired
	private LiveDAO conn_live;
	
	//列表页面
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws Exception{
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("admin/live/list", strMap);
		return mv;
	}

	//获取列表
	@ResponseBody
	@RequestMapping(value="/list.do",method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page ,int limit) throws Exception{
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<LivePO> lives  = conn_live.findAll(page, limit);
		
		int count = conn_live.countAll();
		strMap.put("data", lives);
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("code", "0");
		return strMap;
	}
	
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do",method= RequestMethod.POST)
	public String add(HttpServletRequest request){
		LivePO live = new LivePO();
		live.setUpdateTime(new Date());
		
		conn_live.save(live);

		return "success";
	}
	
	//修改数据
	@ResponseBody
	@RequestMapping(value="/edit.do",method= RequestMethod.POST)
	public String edit(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String value = request.getParameter("value");
		
		LivePO live = conn_live.get(id);
		
		live.setLiveStatusType(LiveStatusType.fromString(value));
		

		conn_live.save(live);
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/edit1.do",method= RequestMethod.POST)
	public String edit1(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String value = request.getParameter("value");
		String field =request.getParameter("field");
		
		LivePO live = conn_live.get(id);
		if(field.equals("liveName")){
			live.setLiveName(value);
		}else{
			live.setLiveStatusType(LiveStatusType.fromString(value));
		}
		
		

		conn_live.save(live);
		return "success";
	}
	
}
