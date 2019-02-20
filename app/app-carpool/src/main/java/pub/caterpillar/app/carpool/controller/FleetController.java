package pub.caterpillar.app.carpool.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.app.carpool.dao.FleetDAO;
import pub.caterpillar.app.carpool.po.FleetPO;
import pub.caterpillar.app.carpool.vo.FleetVO;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping(value = "/fleet")
public class FleetController {

	@Autowired
	private FleetDAO conn_fleet;
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public Object queryAll(HttpServletRequest request) throws Exception{
		
		List<FleetPO> entitys = conn_fleet.findAll();
		List<FleetVO> view_fleets = FleetVO.getConverter(FleetVO.class).convert(entitys, FleetVO.class);
		
		return view_fleets;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Object update(
			@PathVariable Long id,
			String name,
			String mobile,
			HttpServletRequest request) throws Exception{
		
		FleetPO entity = conn_fleet.get(id);
		
		entity.setName(name);
		entity.setMobile(mobile);
		
		conn_fleet.saveOrUpdate(entity);
		
		FleetVO view_fleet = new FleetVO().set(entity);
		
		return view_fleet;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		
		conn_fleet.delete(id);
		
		return null;
		
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(
			String name,
			String mobile,
			HttpServletRequest request) throws Exception{
		
		FleetPO entity = new FleetPO();
		entity.setName(name);
		entity.setMobile(mobile);
		conn_fleet.save(entity);
		
		FleetVO view_fleet = new FleetVO().set(entity);
		
		return view_fleet;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Object delete(HttpServletRequest request) throws Exception{
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> ids = JSON.parseArray(params.getString("ids"), Long.class);
		
		conn_fleet.deleteAllByIds(ids);
		
		return null;
	}
	
}
