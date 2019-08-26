package com.sumavision.tetris.system.theme;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

public class SystemThemeVO extends AbstractBaseVO<SystemThemeVO, SystemThemePO>{

	private String name;
	
	private String url;
	
	public String getName() {
		return name;
	}

	public SystemThemeVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public SystemThemeVO setUrl(String url) {
		this.url = url;
		return this;
	}

	@Override
	public SystemThemeVO set(SystemThemePO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(entity.getUpdateTime()==null?"":DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getName())
			.setUrl(entity.getUrl()==null?"-":entity.getUrl());
		return this;
	}

}
