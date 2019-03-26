package com.guolaiwan.app.web.smartParking.vo;

import java.util.ArrayList;

import javax.persistence.Column;

import com.guolaiwan.app.web.distribute.vo.DistributePolicyVo;
import com.guolaiwan.app.web.distribute.vo.DistributeProductVo;
import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.distribute.po.DistributePolicy;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class VehiceVo {
	
    //车牌号
	private String vehicle;
	//车型
	private String type;
	//停车场名称
	private String parking;
	//停车场区
	private String district;
	// 停车区
	private int number;
	//到期时间
	private String dueTime;
	
	
	
	
	
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getDueTime() {
		return dueTime;
	}
	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParkingn() {
		return parking;
	}
	public void setParkingn(String parking) {
		this.parking = parking;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
}
