package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.LanPO;
import com.guolaiwan.bussiness.admin.po.LogisticsPo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LogisticVO extends AbstractBaseVO<LogisticVO, LogisticsPo> {
	
	private String name;

	
	
	public String getName() {
		return name;
	}

	public LogisticVO setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public LogisticVO set(LogisticsPo entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setName(entity.getName());
		return this;
	}

	
}