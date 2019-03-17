package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.live.LiveRecordPO;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LiveRecordVO extends AbstractBaseVO<LiveRecordVO, LiveRecordPO>{
	
	private String localPath;
	private String pubUrl;
	private String recordName;
	private String liveId;
	private String subLiveId;
	private String subLiveName;
	
	
	public String getLocalPath() {
		return localPath;
	}

	public LiveRecordVO setLocalPath(String localPath) {
		this.localPath = localPath;
		return this;
	}

	public String getPubUrl() {
		return pubUrl;
	}

	public LiveRecordVO setPubUrl(String pubUrl) {
		this.pubUrl = pubUrl;
		return this;
	}

	public String getRecordName() {
		return recordName;
	}

	public LiveRecordVO setRecordName(String recordName) {
		this.recordName = recordName;
		return this;
	}

	public String getLiveId() {
		return liveId;
	}

	public LiveRecordVO setLiveId(String liveId) {
		this.liveId = liveId;
		return this;
	}

	public String getSubLiveId() {
		return subLiveId;
	}

	public LiveRecordVO setSubLiveId(String subLiveId) {
		this.subLiveId = subLiveId;
		return this;
	}

	public String getSubLiveName() {
		return subLiveName;
	}

	public LiveRecordVO setSubLiveName(String subLiveName) {
		this.subLiveName = subLiveName;
		return this;
	}

	@Override
	public LiveRecordVO set(LiveRecordPO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setLocalPath(entity.getLocalPath())
		.setPubUrl(entity.getPubUrl())
		.setRecordName(entity.getRecordName())
		.setLiveId(entity.getLiveId()+"")
		.setSubLiveId(entity.getSubLiveId()+"")
		.setSubLiveName(entity.getSubLiveName());
		return this;
	}
	
}
