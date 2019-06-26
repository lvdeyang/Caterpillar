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

import com.chenxi.web.yueba.admin.dao.OrderDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/home")
public class MHomeController {
	@Autowired
	WorkerDao conn_worker;
	@Autowired
	OrderDao conn_order;
	
	@RequestMapping(value = "/mobile/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("yuebamobile/home", strMap);
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getworkers", method = RequestMethod.GET)
	public Object getworkers(HttpServletRequest request,int currPage,int pageCount) throws Exception {
		List<WorkerPo> workerPos=conn_worker.findAll(currPage, pageCount);
		for (WorkerPo workerPo : workerPos) {
			workerPo.setOrderCount(conn_order.countCompleteByWorker(workerPo.getId()));
		}
		return workerPos;
	}
}