package com.chenxi.web.yueba.mobile.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.po.SysViewRecordPo;
import com.chenxi.web.po.UserPo;
import com.chenxi.web.yueba.admin.dao.AssignDao;

@Controller
@RequestMapping("/assign")
public class AssignController {
	@Autowired
	AssignDao conn_assign;
	
	@RequestMapping(value = "/mobile/add", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String workerId=session.getAttribute("workerId")+"";
		if(workerId.isEmpty()){
			workerId="0";
		}
		strMap.put("workerId", workerId);
		ModelAndView mv = new ModelAndView("yuebamobile/addassign", strMap);
		return mv;
	}
}
