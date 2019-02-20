package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;

import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_zziduan")
public class ZziduanPO extends AbstractBasePO {
	private static final long serialVersionUID = 1L;
	//英文名
	private String Name;
	//中文名
	private String enName;
	//数据库类型
	private String mType;
	//java类型
	private String jType;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getmType() {
		return mType;
	}
	public void setmType(String mType) {
		this.mType = mType;
	}
	public String getjType() {
		return jType;
	}
	public void setjType(String jType) {
		this.jType = jType;
	}
	
	
	
	
}
