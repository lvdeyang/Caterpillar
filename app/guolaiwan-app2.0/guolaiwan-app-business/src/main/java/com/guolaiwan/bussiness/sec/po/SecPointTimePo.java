package com.guolaiwan.bussiness.sec.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_secpointtime")
public class SecPointTimePo extends AbstractBasePO{

	private long secPointId;
	private Date setTime;
	private Date setStartTime;
	private Date setEndTime;
	private String setTimeStr;
	private String setStartTimeStr;
	private String setEndTimeStr;
	public long getSecPointId() {
		return secPointId;
	}
	public void setSecPointId(long secPointId) {
		this.secPointId = secPointId;
	}
	public Date getSetTime() {
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
	public String getSetTimeStr() {
		return setTimeStr;
	}
	public void setSetTimeStr(String setTimeStr) {
		this.setTimeStr = setTimeStr;
	}
	public String getSetEndTimeStr() {
		return setEndTimeStr;
	}
	public void setSetEndTimeStr(String setEndTimeStr) {
		this.setEndTimeStr = setEndTimeStr;
	}
	public Date getSetStartTime() {
		return setStartTime;
	}
	public void setSetStartTime(Date setStartTime) {
		this.setStartTime = setStartTime;
	}
	public String getSetStartTimeStr() {
		return setStartTimeStr;
	}
	public void setSetStartTimeStr(String setStartTimeStr) {
		this.setStartTimeStr = setStartTimeStr;
	}
	
}
