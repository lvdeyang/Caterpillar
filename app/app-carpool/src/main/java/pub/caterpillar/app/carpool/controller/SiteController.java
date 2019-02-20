package pub.caterpillar.app.carpool.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.app.carpool.dao.RouteDAO;
import pub.caterpillar.app.carpool.dao.SiteDAO;
import pub.caterpillar.app.carpool.po.SitePO;
import pub.caterpillar.app.carpool.vo.SiteVO;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping(value = "/site")
public class SiteController {

	@Autowired
	private SiteDAO conn_site;
	
	@Autowired
	private RouteDAO conn_route;
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public Object queryAll(HttpServletRequest request) throws Exception{
		
		List<SitePO> entitys = conn_site.findAll();
		List<SiteVO> view_sites = SiteVO.getConverter(SiteVO.class).convert(entitys, SiteVO.class);
		
		return view_sites;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/filter/site/{filtered}", method = RequestMethod.GET)
	public Object filterSite(
			@PathVariable Long filtered,
			HttpServletRequest request) throws Exception{
		
		List<SitePO> entitys = conn_site.filterById(filtered);
		List<SiteVO> view_sites = SiteVO.getConverter(SiteVO.class).convert(entitys, SiteVO.class);
		
		return view_sites;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Object update(
			@PathVariable Long id,
			String name,
			HttpServletRequest request) throws Exception{
		
		SitePO entity = conn_site.get(id);
		
		entity.setName(name);
		
		conn_site.saveOrUpdate(entity);
		
		SiteVO view_site = new SiteVO().set(entity);
		
		return view_site;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		
		conn_site.delete(id);
		
		return null;
		
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(
			String name,
			HttpServletRequest request) throws Exception{
		
		SitePO entity = new SitePO();
		entity.setName(name);
		conn_site.save(entity);
		
		SiteVO view_site = new SiteVO().set(entity);
		
		return view_site;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Object delete(HttpServletRequest request) throws Exception{
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> ids = JSON.parseArray(params.getString("ids"), Long.class);
		
		conn_site.deleteAllByIds(ids);
		
		return null;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/depatures", method = RequestMethod.GET)
	public Object queryDepatures(HttpServletRequest request) throws Exception{
		List<Long> depatureIds = conn_route.queryDepatureIds();
		if(depatureIds==null || depatureIds.size()<=0) return null;
		List<SitePO> depatures = conn_site.getAllByIds(depatureIds);
		
		List<SiteVO> view_departures = SiteVO.getConverter(SiteVO.class).convert(depatures, SiteVO.class);
		return view_departures;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/destinations/{depatureId}", method = RequestMethod.GET)
	public Object queryDestinations(
			@PathVariable Long depatureId,
			HttpServletRequest request) throws Exception{
		
		List<Long> destinationIds = conn_route.queryDestinationIds(depatureId);
		if(destinationIds==null || destinationIds.size()<=0) return null;
		List<SitePO> destinations = conn_site.getAllByIds(destinationIds);
		
		List<SiteVO> view_destinations = SiteVO.getConverter(SiteVO.class).convert(destinations, SiteVO.class);
		return view_destinations;
	}
	
}
