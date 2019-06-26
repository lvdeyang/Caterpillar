package com.chenxi.web.yueba.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "seerecord")
public class SeeRecordPo extends AbstractBasePO {

	private long userId;
	private String userName;
	private String userPic;
	private long workerId;
	private String workerName;
	private String workerPic;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getWorkerId() {
		return workerId;
	}
	public void setWorkerId(long workerId) {
		this.workerId = workerId;
	}
	@Transient
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	@Transient
	public String getWorkerPic() {
		return workerPic;
	}
	public void setWorkerPic(String workerPic) {
		this.workerPic = workerPic;
	}
	@Transient
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Transient
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	
	
	
}
