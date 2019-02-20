package com.guolaiwan.app.web.admin.menu.vo;

import com.guolaiwan.bussiness.admin.po.MenuPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class MenuVO extends AbstractBaseVO<MenuVO, MenuPO> {

	//菜单名称
	private String name;
	
	//菜单详情
	private String summary;
	
	//渲染类型
	private String renderType;
	
	public String getName() {
		return name;
	}

	public MenuVO setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getSummary() {
		return summary;
	}

	public MenuVO setSummary(String summary) {
		this.summary = summary;
		return this;
	}

	public String getRenderType() {
		return renderType;
	}

	public MenuVO setRenderType(String renderType) {
		this.renderType = renderType;
		return this;
	}

	@Override
	public MenuVO set(MenuPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getName())
			.setSummary(entity.getSummary()==null?"":entity.getSummary())
			.setRenderType(entity.getRenderType()==null?null:entity.getRenderType().getName());
		return this;
	}

}
