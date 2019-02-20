package com.guolaiwan.bussiness.admin.dto;

import java.util.Date;

import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.admin.po.PagePO;

public class PageDTO {

	private Long id;
	
	private String uuid;
	
	private Date updateTime;
	
	private String name;
	
	private String type;
	
	private Long menuid;
	
	private String menuname;

	public Long getId() {
		return id;
	}

	public PageDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public PageDTO setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public PageDTO setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getName() {
		return name;
	}

	public PageDTO setName(String name) {
		this.name = name;
		return this;
	}

	public String getType() {
		return type;
	}

	public PageDTO setType(String type) {
		this.type = type;
		return this;
	}

	public Long getMenuid() {
		return menuid;
	}

	public PageDTO setMenuid(Long menuid) {
		this.menuid = menuid;
		return this;
	}

	public String getMenuname() {
		return menuname;
	}

	public PageDTO setMenuname(String menuname) {
		this.menuname = menuname;
		return this;
	}
	
	public PageDTO set(PagePO page, MenuPO menu){
		this.setId(page.getId())
		    .setUuid(page.getUuid())
		    .setUpdateTime(page.getUpdateTime())
		    .setName(page.getName())
		    .setType(page.getType().toString())
		    .setMenuid(menu.getId())
		    .setMenuname(menu.getName());
		return this;
	}
	
}
