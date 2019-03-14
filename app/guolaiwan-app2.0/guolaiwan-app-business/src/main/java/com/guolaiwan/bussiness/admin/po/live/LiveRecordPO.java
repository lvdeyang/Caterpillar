package com.guolaiwan.bussiness.admin.po.live;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_liverecord")
public class LiveRecordPO extends AbstractBasePO{

	private String localPath;
	private String pubUrl;
	private String recordName;
	private long liveId;
	private long subLiveId;
	private String subLiveName;
	private Date startTime;
	private Date endTime;
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public String getPubUrl() {
		return pubUrl;
	}
	public void setPubUrl(String pubUrl) {
		this.pubUrl = pubUrl;
	}
	public long getLiveId() {
		return liveId;
	}
	public void setLiveId(long liveId) {
		this.liveId = liveId;
	}
	public long getSubLiveId() {
		return subLiveId;
	}
	public void setSubLiveId(long subLiveId) {
		this.subLiveId = subLiveId;
	}
	
	public String getSubLiveName() {
		return subLiveName;
	}
	public void setSubLiveName(String subLiveName) {
		this.subLiveName = subLiveName;
	}
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
