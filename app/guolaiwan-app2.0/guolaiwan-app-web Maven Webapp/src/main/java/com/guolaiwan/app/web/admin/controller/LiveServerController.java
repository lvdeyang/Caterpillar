package com.guolaiwan.app.web.admin.controller;

import java.util.Date;
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

import com.guolaiwan.bussiness.admin.dao.LiveServerDAO;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.LiveServerPO;

@Controller
@RequestMapping("/admin/liveserver")
public class LiveServerController {
	@Autowired
	LiveServerDAO conn_liveserver;
	
	
	// 列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		ModelAndView mv = new ModelAndView("admin/liveserver/list", strMap);
		return mv;
	}
	// 获取直播信息列表
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<LiveServerPO> liveservers = conn_liveserver.findAll(page, limit);
		int count = conn_liveserver.countAll();
		strMap.put("data", liveservers);
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("code", "0");
		return strMap;
	}
	
	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		LiveServerPO liveServerPO=new LiveServerPO();
		liveServerPO.setIp(request.getParameter("ip"));
		conn_liveserver.save(liveServerPO);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String del(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		conn_liveserver.delete(Long.parseLong(id));
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView toInsert() {
		Map<String, Object> strMap = new HashMap<String, Object>();
		// 获取所有模块和子模块
		ModelAndView mv = new ModelAndView("admin/liveserver/add", strMap);
		return mv;
	}
}
