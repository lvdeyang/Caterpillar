package com.chenxi.web.yueba.mobile.controller;

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
import com.chenxi.web.yueba.admin.dao.SeeRecordDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.OrderPo;
import com.chenxi.web.yueba.admin.po.SeeRecordPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

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
	
	@Autowired
	WorkerDao conn_worker;
	@Autowired
	UserDao conn_user;
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/seeworkerslist", method = RequestMethod.GET)
	public Object getWorkers(HttpServletRequest request,int currPage,int pageCount) throws Exception {
		HttpSession session = request.getSession();
		Object userId=session.getAttribute("userId");
		List<SeeRecordPo> recordPos=conn_seerecord.findByField("userId", Long.parseLong(userId+""), currPage, pageCount);
		for (SeeRecordPo seeRecordPo : recordPos) {
			WorkerPo workerPo=conn_worker.get(seeRecordPo.getWorkerId());
			seeRecordPo.setWorkerName(workerPo.getRealName());
			seeRecordPo.setWorkerPic(workerPo.getPhoto());
			seeRecordPo.setDateStr(DateUtil.format(seeRecordPo.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));;
		}
		return recordPos;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/seeuserslist", method = RequestMethod.GET)
	public Object getUsers(HttpServletRequest request,int currPage,int pageCount) throws Exception {
		HttpSession session = request.getSession();
		Object workerId=session.getAttribute("workerId");
		List<SeeRecordPo> recordPos=conn_seerecord.findByField("workerId", Long.parseLong(workerId+""), currPage, pageCount);
		for (SeeRecordPo seeRecordPo : recordPos) {
			UserPo userPo=conn_user.get(seeRecordPo.getUserId());
			seeRecordPo.setUserName(userPo.getNickName());
			seeRecordPo.setUserPic(userPo.getHeadPic());
			seeRecordPo.setDateStr(DateUtil.format(seeRecordPo.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));;
		}
		return recordPos;
	}
	
}
