package com.chenxi.web.yueba.mobile.controller;

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

import com.chenxi.web.classes.OrderStatus;
import com.chenxi.web.dao.UserDao;
import com.chenxi.web.po.UserPo;
import com.chenxi.web.yueba.admin.dao.OrderDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.OrderPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/order")
public class MOrderController {
	@Autowired
	WorkerDao conn_worker;
	@Autowired
	OrderDao conn_order;
	@Autowired
	UserDao conn_user;
	@RequestMapping(value = "/mobile/info", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,long orderId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		OrderPo orderPo=conn_order.get(orderId);
		WorkerPo workerPo=conn_worker.get(orderPo.getWorkerId());
		strMap.put("order", orderPo);
		strMap.put("worker", workerPo);
		ModelAndView mv = new ModelAndView("yuebamobile/orderinfo", strMap);
		return mv;
	}
	@RequestMapping(value = "/mobile/winfo", method = RequestMethod.GET)
	public ModelAndView windex(HttpServletRequest request,long orderId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		OrderPo orderPo=conn_order.get(orderId);
		strMap.put("order", orderPo);
		ModelAndView mv = new ModelAndView("yuebamobile/worderinfo", strMap);
		return mv;
	}
	
	@RequestMapping(value = "/mobile/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("yuebamobile/orderlist", strMap);
		return mv;
	}
	@RequestMapping(value = "/mobile/wlist", method = RequestMethod.GET)
	public ModelAndView wlist(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("yuebamobile/workerorder", strMap);
		return mv;
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/list.do", method = RequestMethod.GET)
	public Object getOrders(HttpServletRequest request,int currPage,int pageCount,String orderStatus) throws Exception {
		List<OrderPo> orderPos=conn_order.findOrderByStatus(OrderStatus.fromString(orderStatus), currPage, pageCount);
		for (OrderPo orderPo : orderPos) {
			orderPo.setWorkName("黄靖宇");
			orderPo.setFromDateStr(DateUtil.format(orderPo.getFromDate(), "yyyy-MM-dd"));
		}
		return orderPos;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/wlist.do", method = RequestMethod.GET)
	public Object getwObjectOrders(HttpServletRequest request,int currPage,int pageCount) throws Exception {
		List<OrderPo> orderPos=conn_order.findAll(currPage, pageCount);
		for (OrderPo orderPo : orderPos) {
			orderPo.setWorkName("黄靖宇");
			orderPo.setFromDateStr(DateUtil.format(orderPo.getFromDate(), "yyyy-MM-dd"));
		}
		return orderPos;
	}
}
