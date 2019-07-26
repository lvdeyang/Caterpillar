package com.chenxi.web.yueba.pc.controller;

import java.text.ParseException;
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

import com.chenxi.web.dao.SysViewRecordDao;
import com.chenxi.web.dao.UserDao;
import com.chenxi.web.po.SysViewRecordPo;
import com.chenxi.web.po.UserPo;
import com.chenxi.web.yueba.admin.dao.AssignDao;
import com.chenxi.web.yueba.admin.dao.OrderDao;
import com.chenxi.web.yueba.admin.po.AssignPo;
import com.chenxi.web.yueba.admin.po.OrderPo;

import javassist.expr.NewArray;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/pc")
public class PcController {
	@Autowired
	SysViewRecordDao conn_sysview;
	
	@RequestMapping(value = "/home/index", method = RequestMethod.GET)
	public ModelAndView windex(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysViewRecordPo sysViewRecordPo=new SysViewRecordPo();
		sysViewRecordPo.setUrl("pchome");
		conn_sysview.save(sysViewRecordPo);
		ModelAndView mv = new ModelAndView("pc/index", strMap);
		return mv;
	}
	
	
	
}
