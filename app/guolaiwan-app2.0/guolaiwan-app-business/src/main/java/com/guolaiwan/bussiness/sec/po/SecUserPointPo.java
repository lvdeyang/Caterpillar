package com.guolaiwan.bussiness.sec.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_secuserpoint")
public class SecUserPointPo extends AbstractBasePO{

	private long secUserId;
	private long secPointId;
	private long secPointTimeId;
	private Date setTime;
	private String setTimeStr;
	private String status;//com.guolaiwan.bussiness.sec.enums.SecUserPointStatus
	public long getSecUserId() {
		return secUserId;
	}
	public void setSecUserId(long secUserId) {
		this.secUserId = secUserId;
	}
	public long getSecPointId() {
		return secPointId;
	}
	public void setSecPointId(long secPointId) {
		this.secPointId = secPointId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getSecPointTimeId() {
		return secPointTimeId;
	}
	public void setSecPointTimeId(long secPointTimeId) {
		this.secPointTimeId = secPointTimeId;
	}
	public Date getSetTime() {
		return setTime;
	}
	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}
	public String getSetTimeStr() {
		return setTimeStr;
	}
	public void setSetTimeStr(String setTimeStr) {
		this.setTimeStr = setTimeStr;
	}
	
	
}
