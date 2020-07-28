package com.guolaiwan.bussiness.merchant.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import com.guolaiwan.bussiness.admin.enumeration.ActivityType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_carmessage")
public class CarMessagePO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	
	private String carNum;
	private String region;
	private int type;//0小型车1大型车
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
}
