package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;

import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;


@Entity
@Table(name = "t_sys_zbiao")
public class ZbiaoPO extends AbstractBasePO {
	private static final long serialVersionUID = 1L;
	//英文名
	private String name;
	//中文名
	private String enName;
	//模块
	private String modular;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getModular() {
		return modular;
	}
	public void setModular(String modular) {
		this.modular = modular;
	}
	
	
	
}
