package com.guolaiwan.app.web.admin.layout.vo;

import com.guolaiwan.bussiness.admin.dto.PageDTO;
import com.guolaiwan.bussiness.admin.enumeration.PageType;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class PageVO extends AbstractBaseVO<PageVO, PageDTO> {

	//页面名称
	private String name;
	
	//页面类型
	private String type;
	
	//关联菜单id
	private Long menuid;
	
	//关联菜单名称
	private String menuname;
	
	public String getName() {
		return name;
	}

	public PageVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getType() {
		return type;
	}

	public PageVO setType(String type) {
		this.type = type;
		return this;
	}

	public Long getMenuid() {
		return menuid;
	}

	public PageVO setMenuid(Long menuid) {
		this.menuid = menuid;
		return this;
	}

	public String getMenuname() {
		return menuname;
	}

	public PageVO setMenuname(String menuname) {
		this.menuname = menuname;
		return this;
	}

	@Override
	public PageVO set(PageDTO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getName())
			.setType(PageType.fromString(entity.getType()).getName())
			.setMenuid(entity.getMenuid())
			.setMenuname(entity.getMenuname());
		return this;
	}

}
