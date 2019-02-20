package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;

import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;


@Entity
@Table(name = "t_sys_bundleorder")
public class BundleOrder extends AbstractBasePO {
	private static final long serialVersionUID = 1L;
	//英文名
	private String orderStr;
	public String getOrderStr() {
		return orderStr;
	}
	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}
	
	
}
