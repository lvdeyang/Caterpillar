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
import com.chenxi.web.yueba.admin.dao.SeeRecordDao;
import com.chenxi.web.yueba.admin.po.OrderPo;
import com.chenxi.web.yueba.admin.po.SeeRecordPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/seerecord")
public class MSeeRecordController {
	
	@Autowired
	SeeRecordDao conn_seerecord;
	
	
	@RequestMapping(value = "/mobile/seeworkers", method = RequestMethod.GET)
	public ModelAndView windex(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("yuebamobile/usersee", strMap);
		return mv;
	}
	@RequestMapping(value = "/mobile/seeusers", method = RequestMethod.GET)
	public ModelAndView ulist(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("yuebamobile/workersee", strMap);
		return mv;
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/seeworkerslist", method = RequestMethod.GET)
	public Object getWorkers(HttpServletRequest request,int currPage,int pageCount) throws Exception {
		List<SeeRecordPo> recordPos=conn_seerecord.findByField("userId", 1l, currPage, pageCount);
		for (SeeRecordPo seeRecordPo : recordPos) {
			seeRecordPo.setWorkerName("黄靖宇");
			seeRecordPo.setWorkerPic("/lib/images/1.jpg");
			seeRecordPo.setDateStr(DateUtil.format(seeRecordPo.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));;
		}
		return recordPos;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/seeuserslist", method = RequestMethod.GET)
	public Object getUsers(HttpServletRequest request,int currPage,int pageCount) throws Exception {
		List<SeeRecordPo> recordPos=conn_seerecord.findByField("workerId", 1l, currPage, pageCount);
		for (SeeRecordPo seeRecordPo : recordPos) {
			seeRecordPo.setUserName("黄靖宇");
			seeRecordPo.setUserPic("/lib/images/1.jpg");
			seeRecordPo.setDateStr(DateUtil.format(seeRecordPo.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));;
		}
		return recordPos;
	}
	
}
