package com.chenxi.web.yueba.mobile.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.sun.tools.javadoc.Start;

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
	public ModelAndView wlist(HttpServletRequest request,long workerId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("workerId", workerId);
		HttpSession session = request.getSession();
		UserPo user=conn_user.get(Long.parseLong(session.getAttribute("userId")+""));
		strMap.put("user", user);
		ModelAndView mv = new ModelAndView("yuebamobile/workerorder", strMap);
		return mv;
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/list.do", method = RequestMethod.GET)
	public Object getOrders(HttpServletRequest request,int currPage,int pageCount,String orderStatus) throws Exception {
		List<OrderPo> orderPos=conn_order.findOrderByStatus(OrderStatus.fromString(orderStatus), currPage, pageCount);
		for (OrderPo orderPo : orderPos) {
			
			orderPo.setFromDateStr(DateUtil.format(orderPo.getFromDate(), "yyyy-MM-dd"));
		}
		return orderPos;
	}
	
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/wlist.do", method = RequestMethod.GET)
	public Object getwObjectOrders(HttpServletRequest request,int currPage,int pageCount,long workerId) throws Exception {
		HttpSession session = request.getSession();
		if(workerId==0){
			workerId=Long.parseLong(session.getAttribute("workerId")+"");	
		}
		List<OrderPo> orderPos=conn_order.findOrderByWorkerPage(workerId,currPage, pageCount);
		for (OrderPo orderPo : orderPos) {
			WorkerPo workerPo=conn_worker.get(orderPo.getWorkerId());
			orderPo.setWorkName(workerPo.getRealName());
			orderPo.setFromDateStr(DateUtil.format(orderPo.getFromDate(), "yyyy-MM-dd"));
		}
		return orderPos;
	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/checkCurentRage", method = RequestMethod.GET)
	public Object checkCurentRage(HttpServletRequest request,String currentDate,long workerId) throws Exception {
		if(currentDate==null||currentDate.isEmpty()){
			currentDate=DateUtil.format(new Date(),"yyyy-MM-dd");
		}
		Map<String, Object> ret=new HashMap<String, Object>();
		List<OrderPo> orderList=conn_order.findOrderByCurrAndWorkerId(currentDate, workerId);
		Date minDate=new Date();
		Date maxDate=new Date();
		boolean isMinchange=false;
		for (OrderPo orderPo : orderList) {
			Date endDate=DateUtil.addDay(orderPo.getFromDate(),26);
			Calendar ca=Calendar.getInstance();
			ca.setTime(DateUtil.parse(currentDate,"yyyy-MM-dd"));
			Date firstCurrMonth=DateUtil.getMonthStartAndEndDate(ca)[0];
			Date lastCurrMonth=DateUtil.getMonthStartAndEndDate(ca)[1];
			if(!DateUtil.compare(firstCurrMonth,endDate)&&!DateUtil.compare(endDate,lastCurrMonth)){
				minDate=endDate;
				isMinchange=true;
			}
		}
		boolean isMaxchange=false;
		for (OrderPo orderPo : orderList) {
			if(!DateUtil.compare(minDate, orderPo.getFromDate())){
				maxDate=DateUtil.addDay(orderPo.getFromDate(),-26);
				isMaxchange=true;
				break;
			}
		}
		
		
		String minStr="1910-01-01";
		String maxStr="2999-01-01";
		if(isMinchange){
			minStr=DateUtil.format(minDate,"yyyy-MM-dd");
		}
				
		ret.put("min", minStr);
		
		if(isMaxchange){
			maxStr=DateUtil.format(maxDate,"yyyy-MM-dd");
		}
		ret.put("max", maxStr);
		
		
		return ret;
		
	}
	
	@RequestMapping(value = "/mobile/addindex", method = RequestMethod.GET)
	public ModelAndView addindex(HttpServletRequest request,long workerId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		WorkerPo workerPo=conn_worker.get(workerId);
		strMap.put("worker", workerPo);
		ModelAndView mv = new ModelAndView("yuebamobile/addorder", strMap);
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/dosave", method = RequestMethod.POST)
	public Object dosave(HttpServletRequest request) throws Exception {
		String fromDate=request.getParameter("fromDate");
		String id=request.getParameter("id");
		OrderPo order=new OrderPo();
		order.setDays(26);
		order.setFromDate(DateUtil.parse(fromDate,"yyyy-MM-dd"));
		order.setMleft(0);
		order.setPrice(0);
		order.setUserId(0);
		order.setWorkerId(Long.parseLong(id));
		WorkerPo workerPo=conn_worker.get(Long.parseLong(id));
		order.setWorderPhoto(workerPo.getPhoto());
		order.setWorkName(workerPo.getRealName());
		conn_order.save(order);
		return "success";
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/del", method = RequestMethod.GET)
	public Object del(HttpServletRequest request,long orderId) throws Exception {
		
		conn_order.delete(orderId);
		return "success";
	}
	
	
	@RequestMapping(value = "/mobile/modify", method = RequestMethod.GET)
	public ModelAndView modify(HttpServletRequest request,long orderId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		OrderPo orderPo=conn_order.get(orderId);
		strMap.put("order", orderPo);
		ModelAndView mv = new ModelAndView("yuebamobile/modifyorder", strMap);
		return mv;
	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/domodify", method = RequestMethod.POST)
	public Object domodify(HttpServletRequest request) throws Exception {
		String userPhone=request.getParameter("userPhone");
		String id=request.getParameter("id");
		OrderPo order=conn_order.get(Long.parseLong(id));
		order.setUserPhone(userPhone);
		conn_order.update(order);
		return "success";
	}
	
}
