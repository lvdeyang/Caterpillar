package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_menu")
public class MenuPO extends AbstractBasePO {

	// 权限名称
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
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getShowis() {
		return showis;
	}
	public void setShowis(int showis) {
		this.showis = showis;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
}
