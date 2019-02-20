package pub.caterpillar.app.carpool.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.app.carpool.dao.CarDAO;
import pub.caterpillar.app.carpool.dao.DriverDAO;
import pub.caterpillar.app.carpool.dao.FleetDAO;
import pub.caterpillar.app.carpool.dao.OrderDAO;
import pub.caterpillar.app.carpool.dao.RouteDAO;
import pub.caterpillar.app.carpool.dao.SiteDAO;
import pub.caterpillar.app.carpool.dto.DriverDTO;
import pub.caterpillar.app.carpool.enumeration.CarStatus;
import pub.caterpillar.app.carpool.enumeration.DriverDirections;
import pub.caterpillar.app.carpool.enumeration.DriverStatus;
import pub.caterpillar.app.carpool.po.CarPO;
import pub.caterpillar.app.carpool.po.DriverPO;
import pub.caterpillar.app.carpool.po.FleetPO;
import pub.caterpillar.app.carpool.po.OrderPO;
import pub.caterpillar.app.carpool.po.RoutePO;
import pub.caterpillar.app.carpool.po.SitePO;
import pub.caterpillar.app.carpool.service.OrderService;
import pub.caterpillar.app.carpool.vo.DriverVO;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping(value = "/driver")
public class DriverController {

	@Autowired
	private OrderDAO conn_order;
	
	@Autowired
	private FleetDAO conn_fleet;
	
	@Autowired
	private DriverDAO conn_driver;
	
	@Autowired
	private CarDAO conn_car;
	
	@Autowired
	private RouteDAO conn_route;
	
	@Autowired
	private SiteDAO conn_site;
	
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public Object queryAll(HttpServletRequest request) throws Exception{
		
		List<DriverDTO> entitys = conn_driver.queryAllDriverDetails();
		List<DriverVO> view_drivers = DriverVO.getConverter(DriverVO.class).convert(entitys, DriverVO.class);
		
		return view_drivers;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/by/order", method = RequestMethod.GET)
	public Object queryByOrder(long orderId, HttpServletRequest request) throws Exception{
		OrderPO order = conn_order.get(orderId);
		List<DriverDTO> drivers = conn_driver.queryByRouteAndStatusAndFreeSeatNum(order.getRouteId(), order.getPassengerNum());
		List<DriverVO> view_drivers = DriverVO.getConverter(DriverVO.class).convert(drivers, DriverVO.class);
		return view_drivers;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Object update(
			@PathVariable Long id,
			String name,
			String mobile,
			Long fleetId,
			Long carId, 
			Long routeId,
			Long revertId,
			HttpServletRequest request) throws Exception{
		
		DriverPO driver = conn_driver.get(id);
		driver.setName(name);
		driver.setMobile(mobile);
		conn_driver.saveOrUpdate(driver);
		
		if(fleetId!=null && fleetId.intValue()!=-1){
			FleetPO oldFleet = driver.getFleet();
			if(oldFleet!=null && !oldFleet.getId().equals(fleetId)){
				oldFleet = conn_fleet.get(oldFleet.getId());
				oldFleet.getDrivers().remove(driver);
				driver.setFleet(null);
				conn_fleet.saveOrUpdate(oldFleet);
				
				FleetPO newFleet = conn_fleet.get(fleetId);
				if(newFleet.getDrivers() == null) newFleet.setDrivers(new HashSet<DriverPO>());
				newFleet.getDrivers().add(driver);
				driver.setFleet(newFleet);
				conn_fleet.saveOrUpdate(newFleet);
			}else{
				FleetPO newFleet = conn_fleet.get(fleetId);
				if(newFleet.getDrivers() == null) newFleet.setDrivers(new HashSet<DriverPO>());
				newFleet.getDrivers().add(driver);
				driver.setFleet(newFleet);
				conn_fleet.saveOrUpdate(newFleet);
			}
		}else{
			FleetPO oldFleet = driver.getFleet();
			if(oldFleet != null){
				oldFleet = conn_fleet.get(oldFleet.getId());
				oldFleet.getDrivers().remove(driver);
				driver.setFleet(null);
				conn_fleet.saveOrUpdate(oldFleet);
			}
		}
		FleetPO finalFleet = driver.getFleet();
		
		if(carId!=null && carId.longValue()!=-1){
			CarPO oldCar = driver.getCar();
			if(oldCar!=null && !oldCar.getId().equals(carId)){
				oldCar = conn_car.get(oldCar.getId());
				oldCar.setDriver(null);
				driver.setCar(null);
				conn_car.saveOrUpdate(oldCar);
				
				CarPO newCar = conn_car.get(carId);
				newCar.setDriver(driver);
				driver.setCar(newCar);
				conn_car.saveOrUpdate(newCar);
			}else{
				CarPO newCar = conn_car.get(carId);
				newCar.setDriver(driver);
				driver.setCar(newCar);
				conn_car.saveOrUpdate(newCar);
			}
		}else{
			CarPO oldCar = driver.getCar();
			if(oldCar != null){
				oldCar = conn_car.get(oldCar.getId());
				oldCar.setDriver(null);
				driver.setCar(null);
				conn_car.saveOrUpdate(oldCar);
			}
		}
		CarPO finalCar = driver.getCar();
		
		if(routeId!=null && routeId.longValue()!=-1){
			RoutePO oldRoute = driver.getRoute();
			if(oldRoute!=null && !oldRoute.getId().equals(routeId)){
				oldRoute = conn_route.get(oldRoute.getId());
				oldRoute.getRouteDrivers().remove(driver);
				driver.setRoute(null);
				conn_route.saveOrUpdate(oldRoute);
				
				RoutePO newRoute = conn_route.get(routeId);
				if(newRoute.getRouteDrivers() == null) newRoute.setRouteDrivers(new HashSet<DriverPO>());
				newRoute.getRouteDrivers().add(driver);
				driver.setRoute(newRoute);
				conn_route.saveOrUpdate(newRoute);
			}else{
				RoutePO newRoute = conn_route.get(routeId);
				if(newRoute.getRouteDrivers() == null) newRoute.setRouteDrivers(new HashSet<DriverPO>());
				newRoute.getRouteDrivers().add(driver);
				driver.setRoute(newRoute);
				conn_route.saveOrUpdate(newRoute);
			}
		}else{
			RoutePO oldRoute = driver.getRoute();
			if(oldRoute != null){
				oldRoute = conn_route.get(oldRoute.getId());
				oldRoute.getRouteDrivers().remove(driver);
				driver.setRoute(null);
				conn_route.saveOrUpdate(oldRoute);
			}
		}
		RoutePO finalRoute = driver.getRoute();
		
		if(revertId!=null && revertId.longValue()!=-1){
			RoutePO oldRevert = driver.getRevert();
			if(oldRevert!=null && !oldRevert.getId().equals(revertId)){
				oldRevert = conn_route.get(oldRevert.getId());
				oldRevert.getRevertDrivers().remove(driver);
				driver.setRevert(null);
				conn_route.saveOrUpdate(oldRevert);
				
				RoutePO newRevert = conn_route.get(revertId);
				if(newRevert.getRevertDrivers() == null) newRevert.setRevertDrivers(new HashSet<DriverPO>());
				newRevert.getRevertDrivers().add(driver);
				driver.setRevert(newRevert);
				conn_route.saveOrUpdate(newRevert);
			}else{
				RoutePO newRevert = conn_route.get(revertId);
				if(newRevert.getRevertDrivers() == null) newRevert.setRevertDrivers(new HashSet<DriverPO>());
				newRevert.getRevertDrivers().add(driver);
				driver.setRevert(newRevert);
				conn_route.saveOrUpdate(newRevert);
			}
		}else{
			RoutePO oldRevert = driver.getRevert();
			if(oldRevert != null){
				oldRevert = conn_route.get(oldRevert.getId());
				oldRevert.getRevertDrivers().remove(driver);
				driver.setRevert(null);
				conn_route.saveOrUpdate(oldRevert);
			}
		}
		RoutePO finalRevert = driver.getRevert();
		
		SitePO routeDeparture = null;
		SitePO routeDestination = null;
		if(finalRoute != null){
			routeDeparture = conn_site.get(finalRoute.getDeparture());
			routeDestination = conn_site.get(finalRoute.getDestination());
		}
		
		SitePO revertDeparture = null;
		SitePO revertDestination = null;
		if(finalRevert != null){
			revertDeparture = conn_site.get(finalRevert.getDeparture());
			revertDestination = conn_site.get(finalRevert.getDestination());	
		}
		
		DriverDTO entity = new DriverDTO().set(driver, finalFleet, finalCar, finalRoute, routeDeparture, routeDestination, finalRevert, revertDeparture, revertDestination);
		DriverVO view_driver = new DriverVO().set(entity);
		
		return view_driver;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		
		conn_driver.delete(id);
		
		return null;
		
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(
			String name,
			String mobile,
			Long fleetId,
			Long carId,
			Long routeId,
			Long revertId,
			HttpServletRequest request) throws Exception{
		
		DriverPO driver = new DriverPO();
		driver.setName(name);
		driver.setMobile(mobile);
		driver.setDirection(DriverDirections.ROUTE);
		//这个地方对司机的上岗下岗控制还没有做
		driver.setStatus(DriverStatus.ON);
		conn_driver.save(driver);
		
		FleetPO fleet = null;
		if(fleetId != null){
			fleet = conn_fleet.get(fleetId);
			if(fleet != null){
				if(fleet.getDrivers() == null) fleet.setDrivers(new HashSet<DriverPO>());
				fleet.getDrivers().add(driver);
				driver.setFleet(fleet);
				conn_fleet.saveOrUpdate(fleet);
			}
		}
		
		CarPO car = null;
		if(carId != null){
			car = conn_car.get(carId);
			if(car != null){
				car.setDriver(driver);
				driver.setCar(car);
				conn_car.saveOrUpdate(car);
			}
		}
		
		RoutePO route = null;
		if(routeId != null){
			route = conn_route.get(routeId);
			if(route != null){
				if(route.getRouteDrivers() == null) route.setRouteDrivers(new HashSet<DriverPO>());
				route.getRouteDrivers().add(driver);
				driver.setRoute(route);
				conn_route.saveOrUpdate(route);
			}
		}
		
		RoutePO revert = null;
		if(revertId != null){
			revert = conn_route.get(revertId);
			if(revertId != null){
				if(revert.getRevertDrivers() == null) revert.setRevertDrivers(new HashSet<DriverPO>());
				revert.getRevertDrivers().add(driver);
				driver.setRevert(revert);
				conn_route.saveOrUpdate(revert);
			}
		}
		
		SitePO routeDeparture = null;
		SitePO routeDestination = null;
		if(route != null){
			routeDeparture = conn_site.get(route.getDeparture());
			routeDestination = conn_site.get(route.getDestination());
		}
		
		SitePO revertDeparture = null;
		SitePO revertDestination = null;
		if(revert != null){
			revertDeparture = conn_site.get(revert.getDeparture());
			revertDestination = conn_site.get(revert.getDestination());
		}
		
		DriverDTO entity = new DriverDTO().set(driver, fleet, car, route, routeDeparture, routeDestination, revert, revertDeparture, revertDestination);
		DriverVO view_driver = new DriverVO().set(entity);
		
		return view_driver;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Object delete(HttpServletRequest request) throws Exception{
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> ids = JSON.parseArray(params.getString("ids"), Long.class);
		
		conn_driver.deleteAllByIds(ids);
		
		return null;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/reset/password/{id}")
	public Object queryKey(
			@PathVariable Long id,
			HttpServletRequest request){
		
		DriverPO driver = conn_driver.get(id);
		
		String username = driver.getUsername();
		
		return username;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/prev/switch/route/{mobile}", method = RequestMethod.GET)
	public Object prevSwitchRoute(
			@PathVariable String mobile,
			HttpServletRequest request) throws Exception{
		
		DriverPO driver = conn_driver.queryByMobile(mobile);
		
		StringBufferWrapper directions = new StringBufferWrapper();
		RoutePO route = null;
		if(DriverDirections.ROUTE.equals(driver.getDirection())){
			route = driver.getRevert();
			directions.append(DriverDirections.REVERT.getName());
		}else if(DriverDirections.REVERT.equals(driver.getDirection())){
			route = driver.getRoute();
			directions.append(DriverDirections.ROUTE.getName());
		}
		
		SitePO departure = conn_site.get(route.getDeparture());
		SitePO destination = conn_site.get(route.getDestination());
		
		directions.append("：")
				  .append(departure.getName())
				  .append(" - ")
				  .append(destination.getName());
		
		return directions.toString();
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/switch/route/{mobile}", method = RequestMethod.POST)
	public Object switchRoute(
			@PathVariable String mobile,
			HttpServletRequest request) throws Exception{
		
		DriverPO driver = conn_driver.queryByMobile(mobile);
		
		StringBufferWrapper directions = new StringBufferWrapper();
		RoutePO route = null;
		if(DriverDirections.ROUTE.equals(driver.getDirection())){
			driver.setDirection(DriverDirections.REVERT);
			route = driver.getRevert();
		}else if(DriverDirections.REVERT.equals(driver.getDirection())){
			driver.setDirection(DriverDirections.ROUTE);
			route = driver.getRoute();
		}
		conn_driver.saveOrUpdate(driver);
		
		SitePO departure = conn_site.get(route.getDeparture());
		SitePO destination = conn_site.get(route.getDestination());
		
		directions.append(departure.getName())
				  .append(" - ")
				  .append(destination.getName());
		
		return directions.toString();
	}
	
	@RequestMapping(value = "/start/off/{mobile}", method = RequestMethod.POST)
	public ModelAndView startOff(
			@PathVariable String mobile, 
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		
		DriverPO driver = conn_driver.queryByMobile(mobile);
		CarPO car = driver.getCar();
		car.setStatus(CarStatus.STARTOFF);
		conn_driver.saveOrUpdate(driver);
		
		mv = new ModelAndView(new StringBufferWrapper().append("redirect:/driver/index/").append(mobile).toString());
		
		return mv;
	}
	
	@RequestMapping(value = "/pause/{mobile}", method = RequestMethod.POST)
	public ModelAndView pause(
			@PathVariable String mobile,
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		
		DriverPO driver = conn_driver.queryByMobile(mobile);
		CarPO car = driver.getCar();
		car.setStatus(CarStatus.WAITING);
		
		mv = new ModelAndView(new StringBufferWrapper().append("redirect:/driver/index/").append(mobile).toString());
		
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/complete/{mobile}", method = RequestMethod.POST)
	public ModelAndView complete(
			@PathVariable String mobile,
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		
		//司机完成订单
		orderService.completeOrder(mobile);
		
		mv = new ModelAndView(new StringBufferWrapper().append("redirect:/driver/index/").append(mobile).toString());
		
		return mv;
	}
	
}
