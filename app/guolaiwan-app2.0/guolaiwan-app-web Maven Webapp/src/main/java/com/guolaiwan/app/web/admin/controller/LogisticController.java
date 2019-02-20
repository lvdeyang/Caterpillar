package com.guolaiwan.app.web.admin.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ChildProductVO;
import com.guolaiwan.app.web.admin.vo.LanVO;
import com.guolaiwan.app.web.admin.vo.LogisticPeopleVO;
import com.guolaiwan.app.web.admin.vo.LogisticPointVO;
import com.guolaiwan.app.web.admin.vo.LogisticVO;
import com.guolaiwan.bussiness.admin.dao.ChildPicAndContentDAO;
import com.guolaiwan.bussiness.admin.dao.LanDAO;
import com.guolaiwan.bussiness.admin.dao.LogisticsDao;
import com.guolaiwan.bussiness.admin.dao.LogisticsPeopleDao;
import com.guolaiwan.bussiness.admin.dao.LogisticsPointDao;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.po.ChildPicAndContentPO;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.LanPO;
import com.guolaiwan.bussiness.admin.po.LogisticsPeople;
import com.guolaiwan.bussiness.admin.po.LogisticsPo;
import com.guolaiwan.bussiness.admin.po.LogisticsPoint;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.PicproRelationPO;
import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.mvc.controller.BaseController;

@RequestMapping(value = "/admin/logistic")
@Controller
public class LogisticController extends BaseController {

	@Autowired
	private LogisticsDao conn_logistic;

	

	@ResponseBody
	@RequestMapping(value = "/addLogistic.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		String name = request.getParameter("logisticName");
		LogisticsPo logisticsPo = new LogisticsPo();
		logisticsPo.setName(name);
		conn_logistic.save(logisticsPo);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/getLogisticList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList() throws Exception {
		List<LogisticsPo> logisticsPos=conn_logistic.findAll();
		List<LogisticVO> listvo = LogisticVO.getConverter(LogisticVO.class).convert(logisticsPos, LogisticVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("admin/logistic/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/updateLogistic/{id}" , method = RequestMethod.GET)
	public ModelAndView toUpdate(@PathVariable long id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView mav = null;
		LogisticsPo logisticsPo=conn_logistic.get(id);
		LogisticVO vo=new LogisticVO();
		vo.set(logisticsPo);
		map.put("oneLogisticList", vo);
		mav = new ModelAndView("admin/logistic/update");
		mav.addAllObjects(map);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateLogistic.do" , method = RequestMethod.POST , produces = "application/json; charset=utf-8")
	public String update(HttpServletRequest request , HttpServletResponse response){
		String id = request.getParameter("logisticId");
		String name = request.getParameter("logisticName");
		LogisticsPo logisticsPo=conn_logistic.get(Long.parseLong(id));
		logisticsPo.setName(name);
		conn_logistic.save(logisticsPo);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/addv" , method = RequestMethod.GET)
	public ModelAndView toInsert(){
		Map<String, Object> strMap = new HashMap<String, Object>();
		// 获取所有模块和子模块
		ModelAndView mv = new ModelAndView("admin/logistic/add", strMap);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/del" , method = RequestMethod.POST , produces = "application/json; charset=utf-8")
	public String del(HttpServletRequest request , HttpServletResponse response){
		String id = request.getParameter("id");
		
		conn_logistic.delete(Long.parseLong(id));
		return "success";
	}
	
	@Autowired
	private LogisticsPointDao conn_logisticpoint;
	
	@RequestMapping(value = "/pointlist", method = RequestMethod.GET)
	public ModelAndView pList(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long logId = Long.parseLong(request.getParameter("uuid"));
		int count = conn_logisticpoint.countByField("logisticsPo.id", logId);
		strMap.put("logId", logId);
		strMap.put("count", count);
		ModelAndView mv = new ModelAndView("admin/logistic/pointlist", strMap);
		return mv;
	}

	// 子产品页面
	@ResponseBody
	@RequestMapping(value = "/pointList.do", method = RequestMethod.POST)
	public Map<String, Object> getpList(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		long logId = Long.parseLong(request.getParameter("logId"));
		List<LogisticsPoint> pList = conn_logisticpoint.findByField("logisticsPo.id", logId);
		List<LogisticPointVO> pListV = LogisticPointVO.getConverter(LogisticPointVO.class).convert(pList,
				LogisticPointVO.class);

		strMap.put("pList", pListV);
		return strMap;
	}


	// 添加子产品页面
	@RequestMapping(value = "/pointaddv", method = RequestMethod.GET)
	public ModelAndView addp(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long logId = Long.parseLong(request.getParameter("logId"));
		strMap.put("logId", logId);
		ModelAndView mv = new ModelAndView("admin/logistic/addpoint", strMap);
		return mv;
	}


	// 添加子产品
	@ResponseBody
	@RequestMapping(value = "/pointadd.do", method = RequestMethod.POST)
	public String addpdo(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
	    String longitude= request.getParameter("longitude");
		// 坐标纬度
		String latitude= request.getParameter("latitude");
		String logId=request.getParameter("logId");
		LogisticsPo logisticsPo=conn_logistic.get(Long.parseLong(logId));
		
		LogisticsPoint point=new LogisticsPoint();
		point.setName(name);
		point.setLatitude(latitude);
		point.setLongitude(longitude);
		point.setLogisticsPo(logisticsPo);
		conn_logisticpoint.save(point);
		logisticsPo.getLogisticsPoints().add(point);
		conn_logistic.save(logisticsPo);
		return "success";
	}

	// 添加子产品页面
	@RequestMapping(value = "/pointupdatev", method = RequestMethod.GET)
	public ModelAndView updatepoint(HttpServletRequest request) throws Exception {
	
		
		Map<String, Object> strMap = new HashMap<String, Object>();
		long logId = Long.parseLong(request.getParameter("logId"));
		String pId = request.getParameter("uuid");
		LogisticsPoint point = conn_logisticpoint.get(Long.parseLong(pId));// 获取子产品
		// 获取关联图片对象
		strMap.put("pId", pId);
		strMap.put("point", point);
		ModelAndView mv = new ModelAndView("admin/logistic/modifypoint", strMap);
		return mv;
	}

	// 修改子产品
	@ResponseBody
	@RequestMapping(value = "/pointmodify.do", method = RequestMethod.POST)
	public String modifypointdo(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
	    String longitude= request.getParameter("longitude");
		// 坐标纬度
		String latitude= request.getParameter("latitude");
		String pId=request.getParameter("uuid");
		
		LogisticsPoint point=conn_logisticpoint.get(Long.parseLong(pId));
		point.setName(name);
		point.setLatitude(latitude);
		point.setLongitude(longitude);
		
		conn_logisticpoint.save(point);
	
		return "success";
	}

	// 删除子产品表的数据
	@ResponseBody
	@RequestMapping(value = "/pointdel.do", method = RequestMethod.POST)
	public String delpoint(HttpServletRequest request) throws Exception {

		String pId = request.getParameter("uuid");
		LogisticsPoint point=conn_logisticpoint.get(Long.parseLong(pId));
		Set<LogisticsPoint> pList= point.getLogisticsPo().getLogisticsPoints();
		pList.remove(point);
		conn_logistic.save(point.getLogisticsPo());
		conn_logisticpoint.delete(Long.parseLong(pId));
		return "success";
	}

	
	
	
	@Autowired
	private LogisticsPeopleDao conn_logisticPeople;
	@RequestMapping(value = "/peoplelist", method = RequestMethod.GET)
	public ModelAndView peList(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long pId = Long.parseLong(request.getParameter("uuid"));
		int count = conn_logisticPeople.countByField("logisticsPoint.id", pId);
		strMap.put("pId", pId);
		strMap.put("count", count);
		ModelAndView mv = new ModelAndView("admin/logistic/peoplelist", strMap);
		return mv;
	}

	// 子产品页面
	@ResponseBody
	@RequestMapping(value = "/peopleList.do", method = RequestMethod.POST)
	public Map<String, Object> getpeList(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		long pId = Long.parseLong(request.getParameter("pId"));
		List<LogisticsPeople> peList = conn_logisticPeople.findByField("logisticsPoint.id", pId);
		List<LogisticPeopleVO> peListV = LogisticPeopleVO.getConverter(LogisticPeopleVO.class).convert(peList,
				LogisticPeopleVO.class);

		strMap.put("peList", peListV);
		return strMap;
	}


	// 添加子产品页面
	@RequestMapping(value = "/peopleaddv", method = RequestMethod.GET)
	public ModelAndView addpe(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long pId = Long.parseLong(request.getParameter("pId"));
		strMap.put("pId", pId);
		ModelAndView mv = new ModelAndView("admin/logistic/addpeople", strMap);
		return mv;
	}


	// 添加子产品
	@ResponseBody
	@RequestMapping(value = "/peopleadd.do", method = RequestMethod.POST)
	public String addpedo(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
	    String phone= request.getParameter("phone");
		
		String pId=request.getParameter("pId");
		LogisticsPoint logisticsPoint=conn_logisticpoint.get(Long.parseLong(pId));
		
		LogisticsPeople people=new LogisticsPeople();
		people.setName(name);
		people.setPhone(phone);

		people.setLogisticsPoint(logisticsPoint);
		conn_logisticPeople.save(people);
		logisticsPoint.getLogisticsPeoples().add(people);
		conn_logisticpoint.save(logisticsPoint);
		return "success";
	}

	// 添加子产品页面
	@RequestMapping(value = "/peopleupdatev", method = RequestMethod.GET)
	public ModelAndView updatepeo(HttpServletRequest request) throws Exception {
	
		
		Map<String, Object> strMap = new HashMap<String, Object>();
		long pId = Long.parseLong(request.getParameter("pId"));
		String peId = request.getParameter("uuid");
		LogisticsPeople people = conn_logisticPeople.get(Long.parseLong(peId));// 获取子产品
		// 获取关联图片对象
		strMap.put("peId", peId);
		strMap.put("people", people);
		ModelAndView mv = new ModelAndView("admin/logistic/modifypeople", strMap);
		return mv;
	}

	// 修改子产品
	@ResponseBody
	@RequestMapping(value = "/peoplemodify.do", method = RequestMethod.POST)
	public String modifypeople(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
	    String phone= request.getParameter("phone");
		
		String peId=request.getParameter("uuid");
		
		LogisticsPeople people=conn_logisticPeople.get(Long.parseLong(peId));
		people.setName(name);
		people.setPhone(phone);
		
		conn_logisticPeople.save(people);
	
		return "success";
	}

	// 删除子产品表的数据
	@ResponseBody
	@RequestMapping(value = "/peopledel.do", method = RequestMethod.POST)
	public String delpeople(HttpServletRequest request) throws Exception {

		String peId = request.getParameter("uuid");
		LogisticsPeople people=conn_logisticPeople.get(Long.parseLong(peId));
		people.getLogisticsPoint().getLogisticsPeoples().remove(people);
		conn_logisticpoint.save(people.getLogisticsPoint());
		// 删 子产品表
		conn_logisticPeople.delete(Long.parseLong(peId));
		return "success";
	}

	
}