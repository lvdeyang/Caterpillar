package com.guolaiwan.bussiness.gonghui.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "gonghui_video")
public class VideoPo extends AbstractBasePO {
	private String company;
	private String name;
	private String phone;
	private String videoName;
	private String coverUrl;
	private String playUrl;
	private int aCount;//点赞个数
	private int passed;//0未通过 1通过 2审核中
	private String passedStr;
	private int send;//0未通知，1已通知
	private long userId;
	private String companyType;
	private int tooss;
	private String realPlayUrl;
	private String realCoverUrl;
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getPlayUrl() {
		return playUrl;
	}
	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}
	public int getaCount() {
		return aCount;
	}
	public void setaCount(int aCount) {
		this.aCount = aCount;
	}
	public int getPassed() {
		return passed;
	}
	public void setPassed(int passed) {
		this.passed = passed;
	} 
	public String getPassedStr() {
		return passedStr;
	}
	public void setPassedStr(String passedStr) {
		this.passedStr = passedStr;
	}
	public int getSend() {
		return send;
	}
	public void setSend(int send) {
		this.send = send;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public int getTooss() {
		return tooss;
	}
	public void setTooss(int tooss) {
		this.tooss = tooss;
	}
	@Transient 
	public String getRealPlayUrl() {
		return realPlayUrl;
	}
	public void setRealPlayUrl(String realPlayUrl) {
		this.realPlayUrl = realPlayUrl;
	}
	@Transient 
	public String getRealCoverUrl() {
		return realCoverUrl;
	}
	public void setRealCoverUrl(String realCoverUrl) {
		this.realCoverUrl = realCoverUrl;
	}
	
	
}
