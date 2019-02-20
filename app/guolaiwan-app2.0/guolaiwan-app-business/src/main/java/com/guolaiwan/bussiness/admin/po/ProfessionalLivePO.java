package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;

import pub.caterpillar.orm.po.AbstractBasePO;


/**
 * 专业直播列表数据仅存储正在直播的专业直播信息
 * @author Administrator
 */
@Entity
@Table(name="t_sys_professionallive")
public class ProfessionalLivePO extends AbstractBasePO{

	private static final long serialVersionUID = 3202755754687349687L;
	private long liveId;
	private String liveName;
	private String liveStatusType;
	private String liveType = "PROFESSIONAL_LIVE";
	//主播机位 1-6 为机位 7为垫播 0为未设置机位
	private int broadcastCamera = 0;
	
	public long getLiveId() {
		return liveId;
	}
	
	public void setLiveId(long liveId) {
		this.liveId = liveId;
	}
	
	public String getLiveName() {
		return liveName;
	}
	
	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}

	public String getLiveStatusType() {
		return liveStatusType;
	}

	public void setLiveStatusType(String liveStatusType) {
		this.liveStatusType = liveStatusType;
	}

	public String getLiveType() {
		return liveType;
	}

	public void setLiveType(String liveType) {
		this.liveType = liveType;
	}

	public int getBroadcastCamera() {
		return broadcastCamera;
	}

	public void setBroadcastCamera(int broadcastCamera) {
		this.broadcastCamera = broadcastCamera;
	}

	
	
	
}
