package com.sumavision.tetris.device;

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
@RequestMapping(value = "/hr/device")
public class DeviceController {

	@Autowired
	private UserQuery userQuery;
	@Autowired
	private DeviceQuery deviceQuery;

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			Integer currentPage,
			Integer pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验		
		Page<DevicePO> page = deviceQuery.findAll(currentPage, pageSize);
		List<DevicePO> entities =page.getContent();
		List<DeviceVO> devices = DeviceVO.getConverter(DeviceVO.class).convert(entities, DeviceVO.class);
		
		Long total = page.getTotalElements();
			
		return new HashMapWrapper<String, Object>().put("rows", devices)
													   .put("total", total)
													   .getMap();
	}
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/alllist")
	public Object alllist(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		
		List<DevicePO> entities =deviceDao.findAll();
		List<DeviceVO> devices = DeviceVO.getConverter(DeviceVO.class).convert(entities, DeviceVO.class);

			
		return devices;
	}
	

	@Autowired
	DeviceService deviceService;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			String name,
			String ip,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		//TODO 权限校验
		DevicePO devicePO =deviceService.add(user, name, ip);
		return new DeviceVO().set(devicePO);
	}	
	
	@Autowired
	DeviceDAO deviceDao;
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/edit/{id}")
	public Object edit(
			@PathVariable Long id,
			String name,
			String ip,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
	
		DevicePO device = deviceDao.findOne(id);
	
		
		device = deviceService.edit(device, name, ip);
		
		return new DeviceVO().set(device);
	}
	

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/remove/{id}")
	public Object remove(
			@PathVariable Long id) throws Exception{
		
		
		DevicePO device = deviceDao.findOne(id);
		
		
		deviceService.remove(device);
		
		
		return null;
	}
	
	
}
