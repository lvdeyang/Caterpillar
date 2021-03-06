package com.guolaiwan.bussiness.sec.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_secpoint")
public class SecPointPo extends AbstractBasePO{

	private String name;
	private String x;
	private String y;
	private String type;//com.guolaiwan.bussiness.sec.enums.SecPointType
	private long companyId;
	private long distance;//打卡范围
	//private Date setTime;
	//private Date setEndTime;
	//private String setTimeStr;
	//private String setEndTimeStr;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	/*public Date getSetTime() {
		return setTime;
	}
	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}
	public Date getSetEndTime() {
		return setEndTime;
	}
	public void setSetEndTime(Date setEndTime) {
		this.setEndTime = setEndTime;
	}
	@Transient
	public String getSetTimeStr() {
		return setTimeStr;
	}
	public void setSetTimeStr(String setTimeStr) {
		this.setTimeStr = setTimeStr;
	}
	@Transient
	public String getSetEndTimeStr() {
		return setEndTimeStr;
	}
	public void setSetEndTimeStr(String setEndTimeStr) {
		this.setEndTimeStr = setEndTimeStr;
	}*/
	
}
