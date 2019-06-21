package com.chenxi.web.yueba.admin.controller;

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

import com.chenxi.web.dao.UserDao;
import com.chenxi.web.yueba.admin.dao.OrderDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.OrderPo;

@Controller
@RequestMapping("/order")
public class OrderContoller {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("yuebaadmin/order", strMap);
		return mv;
	}
	@Autowired
	OrderDao conn_order;
	@Autowired
	UserDao conn_user;
	@Autowired
	WorkerDao conn_worker;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", conn_order.countAll());
		List<OrderPo> orderPos=conn_order.findAll(page, limit);
		for (OrderPo orderPo : orderPos) {
			
		}
		strMap.put("data", orderPos);
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
}
