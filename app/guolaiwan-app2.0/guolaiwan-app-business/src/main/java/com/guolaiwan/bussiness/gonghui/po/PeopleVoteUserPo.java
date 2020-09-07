package com.guolaiwan.bussiness.gonghui.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "peoplevoteuser")
public class PeopleVoteUserPo extends AbstractBasePO{

	private long peoplevoteId;
	private long userId;
	public long getPeoplevoteId() {
		return peoplevoteId;
	}
	public void setPeoplevoteId(long peoplevoteId) {
		this.peoplevoteId = peoplevoteId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
