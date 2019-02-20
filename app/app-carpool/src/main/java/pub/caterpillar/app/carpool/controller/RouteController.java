package pub.caterpillar.app.carpool.controller;

import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.app.carpool.dao.FleetDAO;
import pub.caterpillar.app.carpool.dao.RouteDAO;
import pub.caterpillar.app.carpool.dao.SiteDAO;
import pub.caterpillar.app.carpool.dto.RouteDTO;
import pub.caterpillar.app.carpool.po.DriverPO;
import pub.caterpillar.app.carpool.po.FleetPO;
import pub.caterpillar.app.carpool.po.RoutePO;
import pub.caterpillar.app.carpool.po.SitePO;
import pub.caterpillar.app.carpool.vo.RouteVO;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

	@Autowired
	private SiteDAO conn_site;
	
	@Autowired
	private RouteDAO conn_route;
	
	@Autowired
	private FleetDAO conn_fleet;
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public Object queryAll(HttpServletRequest request) throws Exception{
		
		List<RouteDTO> entitys = conn_route.queryAllRouteDetails();
		List<RouteVO> view_routes = RouteVO.getConverter(RouteVO.class).convert(entitys, RouteVO.class);
		
		return view_routes;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/by/fleet/{fleetId}", method = RequestMethod.GET)
	public Object queryByFleet(
			@PathVariable Long fleetId,
			HttpServletRequest request) throws Exception{
		
		List<RouteDTO> entitys = conn_route.queryRouteDetailsByFleet(fleetId);
		List<RouteVO> view_routes = RouteVO.getConverter(RouteVO.class).convert(entitys, RouteVO.class);
		
		return view_routes;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Object update(
			@PathVariable Long id,
			Long departureId,
			Long destinationId,
			String carpoolPrice,
			String charterPrice,
			Long fleetId,
			HttpServletRequest request) throws Exception{
		
		RoutePO entity = conn_route.get(id);
		
		entity.setDeparture(departureId);
		entity.setDestination(destinationId);
		entity.setCarpoolPrice(carpoolPrice);
		entity.setCharterPrice(charterPrice);
		conn_route.saveOrUpdate(entity);
		
		if(fleetId!=null && fleetId.intValue()!=-1){
			FleetPO oldFleet = entity.getFleet();
			if(oldFleet!=null && !oldFleet.getId().equals(fleetId)){
				oldFleet = conn_fleet.get(oldFleet.getId());
				oldFleet.getRoutes().remove(entity);
				entity.setFleet(null);
				conn_fleet.saveOrUpdate(oldFleet);
				
				FleetPO newFleet = conn_fleet.get(fleetId);
				if(newFleet.getRoutes() == null) newFleet.setRoutes(new HashSet<RoutePO>());
				newFleet.getRoutes().add(entity);
				entity.setFleet(newFleet);
				conn_fleet.saveOrUpdate(newFleet);
			}else{
				FleetPO newFleet = conn_fleet.get(fleetId);
				if(newFleet.getRoutes() == null) newFleet.setRoutes(new HashSet<RoutePO>());
				newFleet.getRoutes().add(entity);
				entity.setFleet(newFleet);
				conn_fleet.saveOrUpdate(newFleet);
			}
		}else{
			FleetPO oldFleet = entity.getFleet();
			if(oldFleet != null){
				oldFleet = conn_fleet.get(oldFleet.getId());
				oldFleet.getRoutes().remove(entity);
				entity.setFleet(null);
				conn_fleet.saveOrUpdate(oldFleet);
			}
		}
		FleetPO finalFleet = entity.getFleet();
		
		SitePO departure = conn_site.get(departureId);
		SitePO destination = conn_site.get(destinationId);
		RouteDTO route = new RouteDTO().set(entity, departure, destination, finalFleet);
		RouteVO view_route = new RouteVO().set(route);
		
		return view_route;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		
		conn_route.delete(id);
		
		return null;
		
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(
			Long departureId,
			Long destinationId,
			String carpoolPrice,
			String charterPrice,
			Long fleetId,
			HttpServletRequest request) throws Exception{
		
		RoutePO entity = new RoutePO();
		entity.setDeparture(departureId);
		entity.setDestination(destinationId);
		entity.setCarpoolPrice(carpoolPrice);
		entity.setCharterPrice(charterPrice);
		
		FleetPO fleet = null;
		if(fleetId!=null && fleetId.intValue()!=-1){
			fleet = conn_fleet.get(fleetId);
			if(fleet.getRoutes() == null) fleet.setRoutes(new HashSet<RoutePO>());
			fleet.getRoutes().add(entity);
			entity.setFleet(fleet);
			conn_fleet.saveOrUpdate(fleet);
		}
		
		conn_route.save(entity);
		
		SitePO departure = conn_site.get(departureId);
		SitePO destination = conn_site.get(destinationId);
		RouteDTO route = new RouteDTO().set(entity, departure, destination, fleet);
		RouteVO view_route = new RouteVO().set(route);
		
		return view_route;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Object delete(HttpServletRequest request) throws Exception{
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> ids = JSON.parseArray(params.getString("ids"), Long.class);
		
		conn_route.deleteAllByIds(ids);
		
		return null;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/route/details/{departureId}/{destinationId}", method = RequestMethod.GET)
	public Object queryRouteDetails(
			@PathVariable Long departureId,
			@PathVariable Long destinationId,
			HttpServletRequest request) throws Exception{
		
		List<RouteDTO> entitys = conn_route.queryRouteDetailsByDepartureAndDestination(departureId, destinationId);
		List<RouteVO> view_routes = RouteVO.getConverter(RouteVO.class).convert(entitys, RouteVO.class);
		
		return view_routes;
	}
	
}
