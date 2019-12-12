package com.sumavision.tetris.device;

import java.util.Date;
import java.util.List;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

public class DeviceVO extends AbstractBaseVO<DeviceVO, DevicePO>{

	private String name;
	
	private String ip;
	
	
	
	public String getName() {
		return name;
	}


	public DeviceVO setName(String name) {
		this.name = name;
		return this;
	}

    
	public String getIp() {
		return ip;
	}


	public DeviceVO setIp(String ip) {
		this.ip = ip;
		return this;
	}


	@Override
	public DeviceVO set(DevicePO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(entity.getUpdateTime()==null?"":DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getName())
			.setIp(entity.getIp());
		return this;
	}

}
