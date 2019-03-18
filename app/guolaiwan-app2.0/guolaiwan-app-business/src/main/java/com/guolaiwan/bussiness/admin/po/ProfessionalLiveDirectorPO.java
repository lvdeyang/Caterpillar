package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_sys_professionallive_director")
public class ProfessionalLiveDirectorPO extends AbstractBasePO{

	private static final long serialVersionUID = 4741567069656165984L;
	
	private long liveId;
	private int inUse;
	//机位直播时是否可以关闭
	private String cameraCanClose = "YES";
	public long getLiveId() {
		return liveId;
	}
	public void setLiveId(long liveId) {
		this.liveId = liveId;
	}
	public int getInUse() {
		return inUse;
	}
	public void setInUse(int inUse) {
		this.inUse = inUse;
	}
	public String getCameraCanClose() {
		return cameraCanClose;
	}
	public void setCameraCanClose(String cameraCanClose) {
		this.cameraCanClose = cameraCanClose;
	}
	
	
}
