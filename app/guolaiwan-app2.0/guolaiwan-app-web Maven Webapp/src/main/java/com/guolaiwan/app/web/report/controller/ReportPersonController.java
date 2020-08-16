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
		List<String> keys=new ArrayList<String>();
		List<Integer> values=new ArrayList<Integer>();
		List<ReportDTO> result=reportOrderDAO.getSexData(1, 10);
		List<ReportDTO> result1=reportorderAllDao.getSexData(1,10);
		
		keys.add("男");
		keys.add("女");
		int nancount=0;
		int nvcount=0;
		for (ReportDTO reportDTO : result1) {
		   	if(reportDTO.getSexString().equals("男")){
		   		nancount+=reportDTO.getAcount();
		   	}else{
		   		nvcount+=reportDTO.getAcount();
		   	}
		}
        for (ReportDTO reportDTO1 : result) {
        	if(reportDTO1.getSexString().equals("男")){
		   		nancount+=reportDTO1.getAcount();
		   	}else{
		   		nvcount+=reportDTO1.getAcount();
		   	}
		}
        values.add(nancount);
        values.add(nvcount);
        map.put("keys", keys);
        map.put("values", values);
		return success(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getageData")
	public Map<String, Object> getageData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReportDTO> result=reportOrderDAO.getageData(1, 10);
		return success(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getregionData")
	public Map<String, Object> getregionData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReportDTO> result=reportOrderDAO.getageData(1, 10);
		return success(result);
	}
	@Autowired
	OrderInfoDAO conn_orderinfo;
	
	@ResponseBody
	@RequestMapping(value = "/todaydata")
	public Map<String, Object> getTodayAmount(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReportDTO> result=reportOrderDAO.getageData(1, 10);
		List<Integer> keys=new ArrayList<Integer>();
		List<Integer> values=new ArrayList<Integer>();
		Date today=new Date();
		String dateStr=DateUtil.format(today, "yyyy-MM-dd");
		for(int i=0;i<24;i++){
			keys.add(i+1);
			Date start=DateUtil.parse(dateStr+" "+getHourStr(i)+":00:00", "yyyy-MM-dd HH:mm:ss");
			Date end=DateUtil.parse(dateStr+" "+getHourStr(i)+":00:00", "yyyy-MM-dd HH:mm:ss");
			int count1=conn_orderinfo.GetCountByHour(start,end);
			int count2=reportOrderDAO.GetCountByHour(start,end);
			int count3=reportorderAllDao.GetCountByHour(start, end);
			values.add(count1+count2+count3);
			
		}
		map.put("keys", keys);
		map.put("values", values);
		return map;
	}
	private String getHourStr(Integer i){
		if(i<10){
			return "0"+i;
		}else{
			return i+"";
		}
	}
	

}
