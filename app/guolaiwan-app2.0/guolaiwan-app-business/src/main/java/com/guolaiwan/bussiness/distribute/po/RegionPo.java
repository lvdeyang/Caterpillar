package com.guolaiwan.bussiness.distribute.po;

import javax.persistence.Entity;
import javax.persistence.Table;


import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "distribute_region")
public class RegionPo extends AbstractBasePO {
     private long parentId;
     private String code;
     private String name;
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
     
	
	 
}
