package com.guolaiwan.bussiness.sec.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_seccompany")
public class SecCompanyPo extends AbstractBasePO{
	private String name;
	private String x;
	private String y;
	private long distance;//公司范围 单位：米

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}
	
	
}
