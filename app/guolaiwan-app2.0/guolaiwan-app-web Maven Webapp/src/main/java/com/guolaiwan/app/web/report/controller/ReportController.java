package com.guolaiwan.app.web.report.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pub.caterpillar.commons.util.date.DateUtil;

@Controller
@RequestMapping("/mreport")
public class ReportController {
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("merchant/mreport/report");
		return mv;
	}
}
