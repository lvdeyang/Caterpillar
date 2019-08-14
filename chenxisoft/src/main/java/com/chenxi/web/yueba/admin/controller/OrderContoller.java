package com.chenxi.web.yueba.admin.controller;

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

import com.chenxi.web.dao.UserDao;
import com.chenxi.web.yueba.admin.dao.DaysTypeDao;
import com.chenxi.web.yueba.admin.dao.LevelDao;
import com.chenxi.web.yueba.admin.dao.OrderDao;
import com.chenxi.web.yueba.admin.dao.RegionDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.ComboPo;
import com.chenxi.web.yueba.admin.po.OrderPo;

import pub.caterpillar.commons.util.date.DateUtil;

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
	@Autowired
	DaysTypeDao conn_daystype;
	@Autowired
	RegionDao conn_region;
	@Autowired
	LevelDao conn_level;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", conn_order.countAll());
		List<OrderPo> orderPos=conn_order.findAll(page, limit);
		for (OrderPo orderPo : orderPos) {
			orderPo.setFromDateStr(DateUtil.format(orderPo.getFromDate(), "yyyy年MM月dd日"));
		}
		strMap.put("data", orderPos);
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
	
	
	@RequestMapping(value = "/addorder", method = RequestMethod.GET)
	public ModelAndView addorder(HttpServletRequest request,long workerId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		
		OrderPo currentOrder=conn_order.findMaxWorderOrder(workerId);
        String minDate=DateUtil.format(new Date(),"yyyy-MM-dd");
        if(currentOrder!=null){
        	minDate=DateUtil.format(DateUtil.addDay(currentOrder.getFromDate(),currentOrder.getDays()+1),"yyyy-MM-dd");
        }
        
		strMap.put("minDate", minDate);
		
		
		strMap.put("workerId", workerId);
		strMap.put("regionList", conn_region.findAll());
		strMap.put("daysList", conn_daystype.findAll());
		strMap.put("levelList",conn_level.findAll());
		ModelAndView mv = new ModelAndView("yuebaadmin/addorder", strMap);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String days = request.getParameter("days");
		String region = request.getParameter("region");
		String price =request.getParameter("price");
		String level= request.getParameter("level");
		String fromDate= request.getParameter("fromDate");
		String userName= request.getParameter("userName");
		String userPhone= request.getParameter("userPhone");
		String workerId= request.getParameter("workerId");
		String left=request.getParameter("left");
		OrderPo orderPo=new OrderPo();
		orderPo.setWorkerId(Long.parseLong(workerId));
		orderPo.setWorkName(conn_worker.get(Long.parseLong(workerId)).getRealName());
		orderPo.setDays(Integer.parseInt(days));
		orderPo.setRegion(region);
		orderPo.setPrice(Double.parseDouble(price));
		orderPo.setFromDate(DateUtil.parse(fromDate, "yyyy-MM-dd"));
		orderPo.setUserName(userName);
		orderPo.setUserPhone(userPhone);
		orderPo.setMleft(Double.parseDouble(left));
		conn_order.save(orderPo);
		
		return "success";
	}
	
}
