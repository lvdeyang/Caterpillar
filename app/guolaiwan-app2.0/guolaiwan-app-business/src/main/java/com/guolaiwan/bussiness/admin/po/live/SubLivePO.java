package com.guolaiwan.bussiness.admin.po.live;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.enumeration.LiveType;
import com.guolaiwan.bussiness.admin.po.LivePO;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_sublive")
public class SubLivePO extends AbstractBasePO {

	private static final long serialVersionUID = 6285795210300284752L;

	//直播名称
	private String liveName;
	//liveId
	private long liveId;
	//是否使用
	private int inuse = 0;
	//机位编号
	private int cameraNumber = 0;
	//发布名称
	private String pubName;
	
	private LiveStatusType status;
	
    private LiveOrderPO liveOrderPO;
    
	public String getLiveName() {
		return liveName;
	}
	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}
	
	
	
	
	public long getLiveId() {
		return liveId;
	}
	public void setLiveId(long liveId) {
		this.liveId = liveId;
	}
	public int getInuse() {
		return inuse;
	}
	public void setInuse(int inuse) {
		this.inuse = inuse;
	}
	
	public int getCameraNumber() {
		return cameraNumber;
	}
	public void setCameraNumber(int cameraNumber) {
		this.cameraNumber = cameraNumber;
	}
	
	public String getPubName() {
		return pubName;
	}
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
	@Enumerated(EnumType.STRING)
	public LiveStatusType getStatus() {
		return status;
	}
	public void setStatus(LiveStatusType status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name = "liveOrderId")
	public LiveOrderPO getLiveOrderPO() {
		return liveOrderPO;
	}
	public void setLiveOrderPO(LiveOrderPO liveOrderPO) {
		this.liveOrderPO = liveOrderPO;
	}
	
	
	
	

}
