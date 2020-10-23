package com.guolaiwan.app.sec.vo;

import java.util.ArrayList;
import java.util.List;

import com.guolaiwan.bussiness.sec.po.SecPointPo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class SecPointVo extends AbstractBaseVO<SecPointVo, SecPointPo> {
	private String name;
	private String x;
	private String y;
	private String type;//com.guolaiwan.bussiness.sec.enums.SecPointType
	private long companyId;
	private long distance;//打卡范围
	private List<SecUserPointVo> secUserPointVos=new ArrayList<SecUserPointVo>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	public List<SecUserPointVo> getSecUserPointVos() {
		return secUserPointVos;
	}
	public void setSecUserPointVos(List<SecUserPointVo> secUserPointVos) {
		this.secUserPointVos = secUserPointVos;
	}
	@Override
	public SecPointVo set(SecPointPo entity) throws Exception {
		// TODO Auto-generated method stub
		this.setName(entity.getName());
		this.setCompanyId(entity.getCompanyId());
		this.setDistance(entity.getDistance());
		this.setName(entity.getName());
		this.setType(entity.getType());
		this.setX(entity.getX());
		this.setY(entity.getY());
		return this;
	}
	
}
