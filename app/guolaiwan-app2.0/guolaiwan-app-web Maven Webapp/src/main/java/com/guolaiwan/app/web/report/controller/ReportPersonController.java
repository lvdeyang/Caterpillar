package com.guolaiwan.app.web.report.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.guolaiwan.app.web.admin.vo.OrderInfoEchartVO;
import com.guolaiwan.app.web.admin.vo.ParkEchartVO;
import com.guolaiwan.bussiness.Parking.dao.ParkingPositionDao;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.merchant.dao.ReportOrderAllDAO;
import com.guolaiwan.bussiness.merchant.dao.ReportOrderDAO;
import com.guolaiwan.bussiness.merchant.dto.ReportDTO;
import com.guolaiwan.bussiness.operation.dao.WebsiteRecordDAO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/report/person")
public class ReportPersonController extends BaseController {

	

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("merchant/mreport/person");
		return mv;
	}

	
	@Autowired
	ReportOrderDAO reportOrderDAO;
	@Autowired
	ReportOrderAllDAO reportorderAllDao;
	
	@ResponseBody
	@RequestMapping(value = "/getsexData")
	public Map<String, Object> getsexData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReportDTO> result=reportOrderDAO.getSexData(1, 10);
		return success(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getageData")
	public Map<String, Object> getageData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReportDTO> result=reportOrderDAO.getageData(1, 10);
		return success(result);
	}
	

}
