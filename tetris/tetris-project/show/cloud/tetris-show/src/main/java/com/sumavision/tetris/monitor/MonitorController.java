package com.sumavision.tetris.monitor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserClassify;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/show/monitor")
public class MonitorController {

	@Autowired
	private UserQuery userQuery;
	@Autowired
	private MonitorQuery monitorQuery;

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			Integer currentPage,
			Integer pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验		
		Page<MonitorPo> page = monitorQuery.findAll(currentPage, pageSize);
		List<MonitorPo> entities =page.getContent();
		
		Long total = page.getTotalElements();
			
		return new HashMapWrapper<String, Object>().put("rows", entities)
													   .put("total", total)
													   .getMap();
	}
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/alllist")
	public Object alllist(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		
		List<MonitorPo> entities =monitorDao.findAll();

			
		return entities;
	}
	

	@Autowired
	MonitorService monitorService;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			String name, 
			String ip,
			int port,
			String userName,
			String password,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		//TODO 权限校验
		MonitorPo monitorPo =monitorService.add(user, name, ip,port,userName,password);
		return monitorPo;
	}	
	
	@Autowired
	MonitorDao monitorDao;
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/edit/{id}")
	public Object edit(
			@PathVariable Long id,
			String name, 
			String ip,
			int port,
			String userName,
			String password,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
	
		MonitorPo monitorPo = monitorDao.findOne(id);
	
		
		monitorPo = monitorService.edit(monitorPo, name, ip,port,userName,password);
		
		return monitorPo;
	}
	

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/remove/{id}")
	public Object remove(
			@PathVariable Long id) throws Exception{
		
		
		MonitorPo monitorPo = monitorDao.findOne(id);
		
		
		monitorService.remove(monitorPo);
		
		
		return null;
	}
	
	
}
