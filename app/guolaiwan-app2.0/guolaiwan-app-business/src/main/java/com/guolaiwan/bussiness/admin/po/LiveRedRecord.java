package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "t_sys_liveredrecord")
public class LiveRedRecord extends AbstractBasePO {

	private long liveId;
	private long userId;
	public long getLiveId() {
		return liveId;
	}
	public void setLiveId(long liveId) {
		this.liveId = liveId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
