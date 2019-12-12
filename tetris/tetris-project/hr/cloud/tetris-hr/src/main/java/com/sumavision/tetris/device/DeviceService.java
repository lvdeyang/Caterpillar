package com.sumavision.tetris.device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.HashSetWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.user.UserVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceService {

	@Autowired
	DeviceDAO deviceDAO;
	
	public DevicePO add(
			UserVO user, 
			String name, 
			String ip) throws Exception{
		
		DevicePO device = new DevicePO();
		device.setName(name);
		device.setIp(ip);
		device.setUpdateTime(new Date());

		deviceDAO.save(device);
		return device;
	}
	

	 
	public DevicePO edit(
			DevicePO device, 
			String name, 
			String ip) throws Exception{
		
		device.setName(name);
		device.setIp(ip);
		device.setUpdateTime(new Date());
		deviceDAO.save(device);
		
		return device;
	}
	


	public void remove(DevicePO device) throws Exception{
		
		deviceDAO.delete(device);
	}
}
