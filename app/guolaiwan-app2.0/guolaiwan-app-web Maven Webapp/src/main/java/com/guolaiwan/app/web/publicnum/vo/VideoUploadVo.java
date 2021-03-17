package com.guolaiwan.app.web.publicnum.vo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "gonghui_video_upload")
public class VideoUploadVo extends AbstractBasePO {
	private String fileName;
	private long fileSize;
	private long uploadSize;
	private int uploadPercent;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public long getUploadSize() {
		return uploadSize;
	}
	public void setUploadSize(long uploadSize) {
		this.uploadSize = uploadSize;
	}
	public int getUploadPercent() {
		return uploadPercent;
	}
	public void setUploadPercent(int uploadPercent) {
		this.uploadPercent = uploadPercent;
	}
	
	
}
