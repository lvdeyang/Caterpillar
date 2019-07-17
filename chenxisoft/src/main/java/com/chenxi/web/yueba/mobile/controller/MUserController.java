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
@RequestMapping("/user")
public class MUserController {
	@Autowired
	WorkerDao conn_worker;
	@Autowired
	OrderDao conn_order;
	@Autowired
	UserDao conn_user;
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/domodify", method = RequestMethod.POST)
	public Object domodify(HttpServletRequest request) throws Exception {
		String userPhone=request.getParameter("userPhone");
		String id=request.getParameter("id");
		UserPo userPo=conn_user.get(Long.parseLong(id+""));
		userPo.setPhone(userPhone);
		conn_user.update(userPo);
		return "success";
	}
	
}
