package com.sumavision.tetris.device;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.user.UserVO;
@Component
public class DeviceQuery {
	@Autowired
	DeviceDAO deviceDAO;
	
	public Page<DevicePO> findAll(int currentPage, int pageSize){
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<DevicePO> sches = deviceDAO.findAll(page);
		return sches;
	}
	
	
}
