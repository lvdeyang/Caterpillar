package com.guolaiwan.app.web.website.car.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.merchant.car.dao.FlightDAO;
import com.guolaiwan.bussiness.merchant.car.dao.RouteDAO;
import com.guolaiwan.bussiness.merchant.car.po.FlightPO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.util.binary.Sha1Util;

@Controller
@RequestMapping("/car")
public class WebCarController{

	@Autowired
	private RouteDAO conn_route;
	@Autowired
	private FlightDAO conn_flight;


	// 登录页面
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("web/car/index");
		return mv;
	}

	//班次
	@RequestMapping(value="/flight",method=RequestMethod.GET)
	public ModelAndView resFli(HttpServletRequest request) throws UnsupportedEncodingException {
		/*if(request.getParameter("fromArea")!=null){
			String fromArea = new String(request.getParameter("fromArea").getBytes("ISO-8859-1"), "utf-8");
		}if(request.getParameter("fromArea")!=null){
			String goArea = new String(request.getParameter("goArea").getBytes("ISO-8859-1"), "utf-8");
		}*/
		String fType = new String(request.getParameter("flight").getBytes("ISO-8859-1"), "utf-8");
	/*	String routeLab = fromArea+"-"+goArea;*/
		/*//路线
		RoutePO route = conn_route.getRouteByLab(routeLab);*/
		List<FlightPO> flights = new ArrayList<FlightPO>();
		if(fType.equals("bc")){
			flights = conn_flight.findAllCT(1, 10);
		}else if(fType.equals("pc")){
			flights = conn_flight.findAllCR(1, 10);
		}else{
			flights =null;
		}
		Map<String, Object> strMap = new HashMap<String,Object>();
		strMap.put("flights",flights);
		ModelAndView mv = new ModelAndView("web/car/reseat/flight",strMap);
		return mv;
	}



	//包车
	@RequestMapping(value="/contract/flight",method=RequestMethod.GET)
	public ModelAndView conFli(HttpServletRequest request) throws UnsupportedEncodingException {
		String fromArea = new String(request.getParameter("fromArea").getBytes("ISO-8859-1"), "utf-8");
		String goArea = new String(request.getParameter("goArea").getBytes("ISO-8859-1"), "utf-8");
		String routeLab = fromArea+"-"+goArea;
		//路线
		RoutePO route = conn_route.getRouteByLab(routeLab);
		List<FlightPO> conflights = new ArrayList<FlightPO>();
		
		
		Map<String, Object> strMap = new HashMap<String,Object>();
		strMap.put("route",route);
		strMap.put("conflights",conflights);
		ModelAndView mv = new ModelAndView("web/car/contract/flight",strMap);
		return mv;
	}

}
