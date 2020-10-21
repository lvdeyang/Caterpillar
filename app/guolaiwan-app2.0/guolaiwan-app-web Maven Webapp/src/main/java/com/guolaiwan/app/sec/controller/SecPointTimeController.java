package com.guolaiwan.app.sec.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.sec.dao.SecCompanyDAO;
import com.guolaiwan.bussiness.sec.dao.SecPointDAO;
import com.guolaiwan.bussiness.sec.dao.SecPointTimeDAO;
import com.guolaiwan.bussiness.sec.po.SecCompanyPo;
import com.guolaiwan.bussiness.sec.po.SecPointPo;
import com.guolaiwan.bussiness.sec.po.SecPointTimePo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/sec/pointtime")
public class SecPointTimeController extends BaseController {

	@Autowired
	private SecPointTimeDAO conn_secpointtime;

	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView pointTimeList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("id", request.getParameter("id"));
		ModelAndView mv = new ModelAndView("sec/pointtime/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(HttpServletRequest request,int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String pointId=request.getParameter("pointId");
		int count = conn_secpointtime.countByField("secPointId", Long.parseLong(pointId));
		List<SecPointTimePo> pointTimes = conn_secpointtime.findByField("secPointId", Long.parseLong(pointId), page, limit);
		for (SecPointTimePo secPointTimePo : pointTimes) {
			secPointTimePo.setSetTimeStr(DateUtil.format(secPointTimePo.getSetTime(),"HH:mm:ss"));
			secPointTimePo.setSetEndTimeStr(DateUtil.format(secPointTimePo.getSetEndTime(),"HH:mm:ss"));
			secPointTimePo.setSetStartTimeStr(DateUtil.format(secPointTimePo.getSetStartTime(),"HH:mm:ss"));

		}
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", pointTimes);
		return strMap;
	}

	// 添加数据页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("sec/pointtime/add");
		mv.addObject("pointId", request.getParameter("pointId"));
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {

		long pointId=Long.parseLong(request.getParameter("pointId"));
	
		String setTime=request.getParameter("setTime");
		String setStartTime=request.getParameter("setStartTime");
		String setEndTime=request.getParameter("setEndTime");
		SecPointTimePo secPointTimePo=new SecPointTimePo();
		secPointTimePo.setSetTime(DateUtil.parse(setTime, "HH:mm:ss"));
		secPointTimePo.setSetStartTime(DateUtil.parse(setStartTime,"HH:mm:ss"));
		secPointTimePo.setSetEndTime(DateUtil.parse(setEndTime,"HH:mm:ss"));
		secPointTimePo.setSecPointId(pointId);
		conn_secpointtime.save(secPointTimePo);
		return "success";
	}

	
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
		conn_secpointtime.delete(id);
		return "success";
	}

}
