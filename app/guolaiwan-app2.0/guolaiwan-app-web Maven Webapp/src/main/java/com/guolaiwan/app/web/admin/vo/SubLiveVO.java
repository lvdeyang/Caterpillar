package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.po.live.SubLivePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class SubLiveVO extends AbstractBaseVO<SubLiveVO, SubLivePO>{

	private String liveName;
	private long liveId;
	private int inuse = 0;
	private String recordState = "STOP";
	private int cameraNumber = 0;
	private String pubName;
	private LiveStatusType status;
 
	public String getLiveName() {
		return liveName;
	}



	public SubLiveVO setLiveName(String liveName) {
		this.liveName = liveName;
		return this;
	}



	public long getLiveId() {
		return liveId;
	}



	public SubLiveVO setLiveId(long liveId) {
		this.liveId = liveId;
		return this;
	}



	public int getInuse() {
		return inuse;
	}



	public SubLiveVO setInuse(int inuse) {
		this.inuse = inuse;
		return this;
	}

	
	



	public String getRecordState() {
		return recordState;
	}



	public SubLiveVO setRecordState(String recordState) {
		this.recordState = recordState;
		return this;
	}



	public int getCameraNumber() {
		return cameraNumber;
	}



	public SubLiveVO setCameraNumber(int cameraNumber) {
		this.cameraNumber = cameraNumber;
		return this;
	}



	public String getPubName() {
		return pubName;
	}



	public SubLiveVO setPubName(String pubName) {
		this.pubName = pubName;
		return this;
	}



	public LiveStatusType getStatus() {
		return status;
	}



	public SubLiveVO setStatus(LiveStatusType status) {
		this.status = status;
		return this;
	}


	@Override
	public SubLiveVO set(SubLivePO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setLiveName(entity.getLiveName())
		.setLiveId(entity.getLiveId())
		.setInuse(entity.getInuse())
		.setRecordState(entity.getRecordState())
		.setCameraNumber(entity.getCameraNumber())
		.setPubName(entity.getPubName())
		.setStatus(entity.getStatus());
		
		return this;
	}
    
    
}
