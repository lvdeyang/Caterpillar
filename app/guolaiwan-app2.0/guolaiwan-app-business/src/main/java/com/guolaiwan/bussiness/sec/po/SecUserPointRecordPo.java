package com.guolaiwan.bussiness.sec.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_secuserpointrecord")
public class SecUserPointRecordPo extends AbstractBasePO{

	private long secUserId;
	private long secPointId;
	private Date setTime;
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
	public Date getSetTime() {
		return setTime;
	}
	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}
	
}
