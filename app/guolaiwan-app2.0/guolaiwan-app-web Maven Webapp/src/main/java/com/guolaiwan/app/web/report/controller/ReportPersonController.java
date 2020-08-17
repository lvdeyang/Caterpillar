package com.guolaiwan.app.web.report.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import com.guolaiwan.bussiness.merchant.dao.CarMessageDAO;
import com.guolaiwan.bussiness.merchant.dao.ReportOrderAllDAO;
import com.guolaiwan.bussiness.merchant.dao.ReportOrderDAO;
import com.guolaiwan.bussiness.merchant.dto.ReportDTO;
import com.guolaiwan.bussiness.operation.dao.WebsiteRecordDAO;

import net.sf.ehcache.search.aggregator.Count;
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
	@RequestMapping(value = "/getFaceData")
	public Map<String, Object> getFaceData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		Date today=new Date();
		String dateStr=DateUtil.format(today, "yyyy-MM-dd");
		
		Date start=DateUtil.parse(dateStr+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date end=DateUtil.parse(dateStr+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		int count1=conn_orderinfo.GetCountByHour(start,end);
		int count2=reportOrderDAO.GetCountByHour(start,end);
		int count3=reportorderAllDao.GetCountByHour(start, end);
		map.put("count", count1+count2+count3);

		return map;
	}
	
	
	
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
		return map;
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
	
	
	@ResponseBody
	@RequestMapping(value = "/getMerchantData")
	public Map<String, Object> getMerchantData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReportDTO> reportDTOs=conn_orderinfo.getmerchantData(1, 12);
		List<ReportDTO> reportDTOs2=reportOrderDAO.getmerchantData(1, 12);
		List<ReportDTO> reportDTOs3=reportorderAllDao.getmerchantData(1, 12);
		Map<String,Integer> resultMap=new HashMap<String, Integer>();
		
		for (ReportDTO reportDTO : reportDTOs3) {
			if(resultMap.containsKey(reportDTO.getMerchantName())){
				resultMap.put(reportDTO.getMerchantName(), resultMap.get(reportDTO.getMerchantName())+reportDTO.getAcount());
			}else{
				resultMap.put(reportDTO.getMerchantName(), reportDTO.getAcount());
			}
		}
		
		for (ReportDTO reportDTO2 : reportDTOs2) {
			if(resultMap.containsKey(reportDTO2.getMerchantName())){
				resultMap.put(reportDTO2.getMerchantName(), resultMap.get(reportDTO2.getMerchantName())+reportDTO2.getAcount());
			}else{
				resultMap.put(reportDTO2.getMerchantName(), reportDTO2.getAcount());
			}
		}
		
		for (ReportDTO reportDTO3 : reportDTOs) {
			if(resultMap.containsKey(reportDTO3.getMerchantName())){
				resultMap.put(reportDTO3.getMerchantName(), resultMap.get(reportDTO3.getMerchantName())+reportDTO3.getAcount());
			}else{
				resultMap.put(reportDTO3.getMerchantName(), reportDTO3.getAcount());
			}
		}
		
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(resultMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {

                return o2.getValue().compareTo(o1.getValue());
            }
        });
        
        List<String> keys=new ArrayList<String>();
        List<Integer> values=new ArrayList<Integer>();
        List<Integer> values1=new ArrayList<Integer>();
        int index=0;
        for (Map.Entry<String, Integer> entry : list) {
			if(index<12){
				keys.add(entry.getKey().length()>5?entry.getKey().substring(0,5):entry.getKey());
				values.add(entry.getValue());
				Random rand = new Random();
				values1.add(entry.getValue()/(rand.nextInt(8)+1));
				index++;
			}
		}
		
        map.put("keys", keys);
        map.put("values", values);
        map.put("values1", values1);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getsevenData")
	public Map<String, Object> getsevenData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReportDTO> result=reportOrderDAO.getageData(1, 10);
		List<String> keys=new ArrayList<String>();
		List<Integer> values=new ArrayList<Integer>();
		List<Integer> values1=new ArrayList<Integer>();
		Date today=new Date();
		
		for(int i=7;i>0;i--){
			Date curDate=DateUtil.addDay(-i);
			String dateStr=DateUtil.format(curDate, "yyyy-MM-dd");
			Date start=DateUtil.parse(dateStr+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date end=DateUtil.parse(dateStr+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
			int count1=conn_orderinfo.GetCountByHour(start,end);
			int count2=reportOrderDAO.GetCountByHour(start,end);
			int count3=reportorderAllDao.GetCountByHour(start, end);
			keys.add(DateUtil.format(curDate, "MM.dd"));
			values.add(count1+count2+count3);
			values1.add(count1);
		}
		map.put("keys", keys);
		map.put("values", values);
		map.put("values1", values1);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getjiudianData")
	public Map<String, Object> getjiudianData(HttpServletRequest request) throws Exception {
		Long[] ids=new Long[]{22l,254l,34l,33l};
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReportDTO> reportDTOs=conn_orderinfo.getjiudianData(1, 12,ids);
		List<ReportDTO> reportDTOs2=reportOrderDAO.getjiudianData(1, 12,ids);
		List<ReportDTO> reportDTOs3=reportorderAllDao.getjiudianData(1, 12,ids);
		Map<String,Integer> resultMap=new HashMap<String, Integer>();
		
		for (ReportDTO reportDTO : reportDTOs3) {
			if(resultMap.containsKey(reportDTO.getMerchantName())){
				resultMap.put(reportDTO.getMerchantName(), resultMap.get(reportDTO.getMerchantName())+reportDTO.getAcount());
			}else{
				resultMap.put(reportDTO.getMerchantName(), reportDTO.getAcount());
			}
		}
		
		for (ReportDTO reportDTO2 : reportDTOs2) {
			if(resultMap.containsKey(reportDTO2.getMerchantName())){
				resultMap.put(reportDTO2.getMerchantName(), resultMap.get(reportDTO2.getMerchantName())+reportDTO2.getAcount());
			}else{
				resultMap.put(reportDTO2.getMerchantName(), reportDTO2.getAcount());
			}
		}
		
		for (ReportDTO reportDTO3 : reportDTOs) {
			if(resultMap.containsKey(reportDTO3.getMerchantName())){
				resultMap.put(reportDTO3.getMerchantName(), resultMap.get(reportDTO3.getMerchantName())+reportDTO3.getAcount());
			}else{
				resultMap.put(reportDTO3.getMerchantName(), reportDTO3.getAcount());
			}
		}
		
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(resultMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {

                return o2.getValue().compareTo(o1.getValue());
            }
        });
        
        List<String> keys=new ArrayList<String>();
        List<Integer> values=new ArrayList<Integer>();
        List<Integer> values1=new ArrayList<Integer>();
        int index=0;
        for (Map.Entry<String, Integer> entry : list) {
			if(index<12){
				keys.add(entry.getKey().length()>5?entry.getKey().substring(0,5):entry.getKey());
				values.add(entry.getValue());
				Random rand = new Random();
				values1.add(entry.getValue()/(rand.nextInt(8)+1));
				index++;
			}
		}
		
        map.put("keys", keys);
        map.put("values", values);
        map.put("values1", values1);
		return map;
	}
	
	//车辆相关
	@Autowired
	CarMessageDAO conn_carmsg;
	@ResponseBody
	@RequestMapping(value = "/getallCarData")
	public Map<String, Object> getallCarData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReportDTO> reportDTOs=conn_carmsg.getAllCarcount();
		map.put("count", reportDTOs.get(0).getAcount());
		return map;
	}

	
	@ResponseBody
	@RequestMapping(value = "/getcartypeData")
	public Map<String, Object> getcartypeData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> keys=new ArrayList<String>();
		List<Integer> values=new ArrayList<Integer>();
		List<ReportDTO> result=conn_carmsg.gettypeData(1, 10);
	
		keys.add("小型车");
		keys.add("大型车");
		int nancount=0;
		int nvcount=0;
		for (ReportDTO reportDTO : result) {
		   	if(reportDTO.getTypeString().equals("大型车")){
		   		nancount+=reportDTO.getAcount();
		   	}else{
		   		nvcount+=reportDTO.getAcount();
		   	}
		}
       
        values.add(nancount);
        values.add(nvcount);
        map.put("keys", keys);
        map.put("values", values);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getcarregionData")
	public Map<String, Object> getcarregionData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> keys=new ArrayList<String>();
		List<Integer> values=new ArrayList<Integer>();
		List<ReportDTO> result=conn_carmsg.getregionData(1, 5);
	
		for (ReportDTO reportDTO : result) {
			keys.add(reportDTO.getRegion());
			values.add(reportDTO.getAcount());
		}
        map.put("keys", keys);
        map.put("values", values);
		return map;
	}
}
