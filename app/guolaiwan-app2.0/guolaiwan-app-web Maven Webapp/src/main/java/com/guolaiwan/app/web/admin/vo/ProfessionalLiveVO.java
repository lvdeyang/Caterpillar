package com.guolaiwan.app.web.admin.vo;

import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.po.ProfessionalLivePO;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ProfessionalLiveVO extends AbstractBaseVO<ProfessionalLiveVO, ProfessionalLivePO>{


	private long liveId;
	private String liveName;
	private String liveStatusType;
	private String liveType;
	//主播机位 1-6 为机位 7为垫播
	private int broadcastCamera;
	
	
	
	public long getLiveId() {
		return liveId;
	}



	public ProfessionalLiveVO setLiveId(long liveId) {
		this.liveId = liveId;
		return this;
	}



	public String getLiveName() {
		return liveName;
	}



	public ProfessionalLiveVO setLiveName(String liveName) {
		this.liveName = liveName;
		return this;
	}



	public String getLiveStatusType() {
		return liveStatusType;
	}



	public ProfessionalLiveVO setLiveStatusType(String liveStatusType) {
		this.liveStatusType = liveStatusType;
		return this;
	}

	
	
	public String getLiveType() {
		return liveType;
	}



	public ProfessionalLiveVO setLiveType(String liveType) {
		this.liveType = liveType;
		return this;
	}



	public int getBroadcastCamera() {
		return broadcastCamera;
	}



	public ProfessionalLiveVO setBroadcastCamera(int broadcastCamera) {
		this.broadcastCamera = broadcastCamera;
		return this;
	}

	@Override
	public ProfessionalLiveVO set(ProfessionalLivePO entity) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(formatter.format(entity.getUpdateTime()))
		.setLiveId(entity.getLiveId())
		.setLiveName(entity.getLiveName())
		.setLiveStatusType(entity.getLiveStatusType())
		.setLiveType(entity.getLiveType())
		.setBroadcastCamera(entity.getBroadcastCamera());
		return this;
	}

}
