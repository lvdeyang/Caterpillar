package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.LanPO;
import com.guolaiwan.bussiness.admin.po.LogisticsPeople;
import com.guolaiwan.bussiness.admin.po.LogisticsPo;
import com.guolaiwan.bussiness.admin.po.LogisticsPoint;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LogisticPeopleVO extends AbstractBaseVO<LogisticPeopleVO, LogisticsPeople> {
	
	private String name;
    private String phone;
	// 坐标经度
	private String longitude;
	// 坐标纬度
	private String latitude;
	
	public String getName() {
		return name;
	}

	public LogisticPeopleVO setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public LogisticPeopleVO set(LogisticsPeople entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setLatitude(entity.getLatitude())
		.setLongitude(entity.getLongitude())
		.setPhone(entity.getPhone())
		.setName(entity.getName());
		return this;
	}

	public String getLongitude() {
		return longitude;
	}

	public LogisticPeopleVO setLongitude(String longitude) {
		this.longitude = longitude;
		return this;
	}

	public String getLatitude() {
		return latitude;
	}

	public LogisticPeopleVO setLatitude(String latitude) {
		this.latitude = latitude;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public LogisticPeopleVO setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	
}