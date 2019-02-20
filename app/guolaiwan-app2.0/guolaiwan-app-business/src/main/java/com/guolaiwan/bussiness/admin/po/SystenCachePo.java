package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "t_sys_cache")
public class SystenCachePo extends AbstractBasePO{

	private String wxKey;
	private String wxVal;
	public String getWxKey() {
		return wxKey;
	}
	public void setWxKey(String wxKey) {
		this.wxKey = wxKey;
	}
	public String getWxVal() {
		return wxVal;
	}
	public void setWxVal(String wxVal) {
		this.wxVal = wxVal;
	}
	
}
