package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.MenuPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class MenuVO extends AbstractBaseVO<MenuVO, MenuPO>{

	// 名称
	private String name;
	// 地址
	private String url;
	// 是否显示
	private int showis;
	// 排序
	private int sort;
	// 父类
	private int parentId;
	
	
	public String getName() {
		return name;
	}


	public MenuVO setName(String name) {
		this.name = name;
		return this;
	}


	public String getUrl() {
		return url;
	}


	public MenuVO setUrl(String url) {
		this.url = url;
		return this;
	}


	public int getShowis() {
		return showis;
	}


	public MenuVO setShowis(int showis) {
		this.showis = showis;
		return this;
	}


	public int getSort() {
		return sort;
	}


	public MenuVO setSort(int sort) {
		this.sort = sort;
		return this;
	}


	public int getParentId() {
		return parentId;
	}


	public MenuVO setParentId(int parentId) {
		this.parentId = parentId;
		return this;
	}


	@Override
	public MenuVO set(MenuPO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setName(entity.getName())
		.setUrl(entity.getUrl())
		.setShowis(entity.getShowis())
		.setSort(entity.getSort())
		.setParentId(entity.getParentId());
		return this;
	}
}
