package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.LanPO;
import com.guolaiwan.bussiness.admin.po.LogisticsPo;
import com.guolaiwan.bussiness.admin.po.LogisticsPoint;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LogisticPointVO extends AbstractBaseVO<LogisticPointVO, LogisticsPoint> {
	
	private String name;

	// 坐标经度
	private String longitude;
	// 坐标纬度
	private String latitude;
	
	public String getName() {
		return name;
	}

	public LogisticPointVO setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public LogisticPointVO set(LogisticsPoint entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setLatitude(entity.getLatitude())
		.setLongitude(entity.getLongitude())
		.setName(entity.getName());
		return this;
	}

	public String getLongitude() {
		return longitude;
	}

	public LogisticPointVO setLongitude(String longitude) {
		this.longitude = longitude;
		return this;
	}

	public String getLatitude() {
		return latitude;
	}

	public LogisticPointVO setLatitude(String latitude) {
		this.latitude = latitude;
		return this;
	}

	
}