package com.guolaiwan.app.sec.vo;

import java.util.Date;

import com.guolaiwan.bussiness.sec.po.SecUserPointPo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class SecUserPointVo extends AbstractBaseVO<SecUserPointVo, SecUserPointPo>{
	private long secUserId;
	private long secPointId;
	private long secPointTimeId;
	private String setTimeStr;
	private String status;//com.guolaiwan.bussiness.sec.enums.SecUserPointStatus
	public long getSecUserId() {
		return secUserId;
	}
	public void setSecUserId(long secUserId) {
		this.secUserId = secUserId;
	}
	public long getSecPointId() {
		return secPointId;
	}
	public void setSecPointId(long secPointId) {
		this.secPointId = secPointId;
	}
	public long getSecPointTimeId() {
		return secPointTimeId;
	}
	public void setSecPointTimeId(long secPointTimeId) {
		this.secPointTimeId = secPointTimeId;
	}
	
	public String getSetTimeStr() {
		return setTimeStr;
	}
	public void setSetTimeStr(String setTimeStr) {
		this.setTimeStr = setTimeStr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public SecUserPointVo set(SecUserPointPo entity) throws Exception {
		// TODO Auto-generated method stub
		this.setSecPointId(entity.getSecPointId());
		this.setSecPointTimeId(entity.getSecPointId());
		this.setSecUserId(entity.getSecUserId());
		this.setSetTimeStr(entity.getSetTimeStr());
		this.setStatus(entity.getStatus());
		return this;
	}
	
}
