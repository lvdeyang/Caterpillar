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
import com.guolaiwan.bussiness.sec.po.SecCompanyPo;
import com.guolaiwan.bussiness.sec.po.SecPointPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/sec/point")
public class SecPointController extends BaseController {

	@Autowired
	private SecPointDAO conn_secpoint;

	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView companyList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("id", request.getParameter("id"));
		ModelAndView mv = new ModelAndView("sec/point/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(HttpServletRequest request,int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String comId=request.getParameter("comId");
		int count = conn_secpoint.countByField("companyId", Long.parseLong(comId));
		List<SecPointPo> points = conn_secpoint.findByField("companyId", Long.parseLong(comId), page, limit);
		for (SecPointPo secPointPo : points) {
			secPointPo.setSetTimeStr(DateUtil.format(secPointPo.getSetTime(),"HH:mm:ss"));
			secPointPo.setSetEndTimeStr(DateUtil.format(secPointPo.getSetEndTime(),"HH:mm:ss"));
		}
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", points);
		return strMap;
	}

	// 添加数据页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("sec/point/add");
		mv.addObject("comId", request.getParameter("comId"));
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
	
		String name=request.getParameter("name");
		String x=request.getParameter("x");
		String y=request.getParameter("y");
		String type=request.getParameter("type");
		long companyId=Long.parseLong(request.getParameter("comId"));
		long distance=Long.parseLong(request.getParameter("distance"));//打卡范围
		String setTime=request.getParameter("setTime");
		String setEndTime=request.getParameter("setEndTime");
		SecPointPo secPointPo=new SecPointPo();
		secPointPo.setCompanyId(companyId);
		secPointPo.setDistance(distance);
		secPointPo.setName(name);
		secPointPo.setType(type);
		secPointPo.setX(x);
		secPointPo.setY(y);
		secPointPo.setSetTime(DateUtil.parse(setTime, "HH:mm:ss"));
		secPointPo.setSetEndTime(DateUtil.parse(setEndTime,"HH:mm:ss"));
		conn_secpoint.save(secPointPo);
		return "success";
	}

	
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
		conn_secpoint.delete(id);
		return "success";
	}

}
