package com.guolaiwan.app.web.merchant.car.controller;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.merchant.car.vo.FlightVO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.merchant.car.dao.DriverDAO;
import com.guolaiwan.bussiness.merchant.car.dao.FlightDAO;
import com.guolaiwan.bussiness.merchant.car.dao.RouteDAO;
import com.guolaiwan.bussiness.merchant.car.enumeration.FlightType;
import com.guolaiwan.bussiness.merchant.car.po.DriverPO;
import com.guolaiwan.bussiness.merchant.car.po.FlightPO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/merchant/flight")
public class FlightController extends BaseController{
	@Autowired
	private FlightDAO conn_flight;
	@Autowired
	private RouteDAO conn_route;
	@Autowired
	private DriverDAO conn_driver;

	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getFlight(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		long route = Long.parseLong(request.getParameter("route"));
		strMap.put("route",route);
		strMap.put("allcount", conn_route.get(route).getFlights().size());
		
		ModelAndView mv = new ModelAndView("merchant/car/flight/list",strMap);
		return mv;
	}

	//添加页面
	@RequestMapping(value="/addv")
	public ModelAndView addFlight(HttpServletRequest request){
		ModelAndView mv=null;
		Map<String, Object> strMap = new HashMap<String, Object>();
		long route = Long.parseLong(request.getParameter("route"));
		RoutePO routePO = conn_route.get(route);
		//获取司机
		MerchantPO merchant = routePO.getMerchant();
		List<DriverPO> drivers = merchant.getDrivers();	
		strMap.put("route",route);
		strMap.put("drivers",drivers);
		String type = request.getParameter("type");
		if(type.equals("cartime")){
			mv = new ModelAndView("merchant/car/flight/add",strMap);
		}else{
			mv = new ModelAndView("merchant/car/flight/addb",strMap);
		}
		return mv;
	}
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="list.do",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	public Map<String,Object> GetList(int pagecurr,long route) throws Exception{
		
		RoutePO routePo = conn_route.get(route);
		List<FlightPO> flightpo = conn_flight.findAllR(routePo, pagecurr, 10);
		List<FlightVO> flightvo = FlightVO.getConverter(FlightVO.class).convert(flightpo, FlightVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("alist", flightvo);
		return map;			
	}
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		long driverId = Long.parseLong(request.getParameter("driverId"));//司机
		DriverPO driver = conn_driver.get(driverId);
		String flightType=request.getParameter("flightType");
		//发车时间
		long price = (long)(Double.parseDouble(request.getParameter("price"))*100);//价钱
		
		
		long route = Long.parseLong(request.getParameter("route"));//路线
		RoutePO routePo = conn_route.get(route);
		FlightPO flight = new FlightPO();
		flight.setDriverId(driverId);
		flight.setDriver(driver.getName());
		flight.setFlightType(FlightType.fromString(flightType));
		
		
		if(request.getParameter("goTime")==null|| request.getParameter("goTime").length()<=0){
			flight.setName(routePo.getName()+"包车");
		}else{
			Time time = Time.valueOf( request.getParameter("goTime"));
			flight.setGoTime(time);
			flight.setName(routePo.getName()+request.getParameter("goTime"));
		}
		flight.setPrice(price);
		flight.setRoute(routePo);
		
		
		
		conn_flight.save(flight);
		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		FlightPO flight = conn_flight.get(uuid);
		//解关联
		flight.setRoute(null);
		
		conn_flight.delete(flight);
		return "success";
	}
	//显示修改页面
	@RequestMapping(value="/updatev",method= RequestMethod.GET)
	public ModelAndView UpdateView(HttpServletRequest request) throws Exception{

		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid =request.getParameter("uuid");
		FlightPO flightp = conn_flight.get(uuid);
		
		FlightVO flight = new FlightVO().set(flightp);
		List<DriverPO> drivers = flightp.getRoute().getMerchant().getDrivers();
		strMap.put("list", flight);
		strMap.put("drivers", drivers);
		ModelAndView mv = new ModelAndView("merchant/car/flight/Modify");
		mv.addAllObjects(strMap);

		return mv;
	}
	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		long driverId = Long.parseLong(request.getParameter("driverId"));
		DriverPO driverPO = conn_driver.get(driverId);
		String driver = driverPO.getName();
		String goTime =request.getParameter("goTime");
		long price = (long)(Double.parseDouble(request.getParameter("price"))*100);//价钱
		
		
		FlightPO flight = conn_flight.get(uuid);
		RoutePO routePo = flight.getRoute();
		
		//固定班次
		if(flight.getFlightType().equals(FlightType.CARTIME)){
			Time time = Time.valueOf( request.getParameter("goTime"));
			flight.setGoTime(time);
			flight.setName(routePo.getName()+request.getParameter("goTime"));
		}
		flight.setDriver(driver);
		flight.setDriverId(driverId);
		flight.setPrice(price);
		
		
		conn_flight.update(flight);
		return "success";
	}
	
	
	
}