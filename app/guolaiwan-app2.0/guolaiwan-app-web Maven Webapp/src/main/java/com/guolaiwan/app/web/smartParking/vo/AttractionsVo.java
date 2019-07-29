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

public class AttractionsVo  extends AbstractBaseVO<AttractionsVo, AttractionsParkingPO>{
	// 景区id
	private  Long attractionsId;
	//停车场名称
	private  String  parkingName;
	//停车场图片
	private  String  parkingImg;
	//停车场地址
	private  String  address;
	//总共停放车位
	private  int  commonParking;
	//已用多少车位
	private  int usedParking;
	//位置  室内 室外 
	private  String  position;	
	// 是否     有充电柱 
	private  String chargingColumn;
	//景区停车层
	private String  parkingLayer;
	//景区停车区
	private  String parkingDistrict;
	//停车场条例
	private String regulations;
	//罚款倍数
	private  int fineMultiple;
	//停车场时间
	private String stoppingTime;
	//距离
    private String distance;
	//停车场电话
	private String phone;

	@Override
	public AttractionsVo set(AttractionsParkingPO entity) throws Exception {
		this.setAttractionsId(entity.getAttractionsId())
		.setParkingName(entity.getParkingName())
		.setParkingImg(entity.getParkingImg())
		.setAddress(entity.getAddress())
		.setCommonParking(entity.getCommonParking())
		.setUsedParking(entity.getUsedParking())
		.setPosition(entity.getPosition())
        .setChargingColumn(entity.getChargingColumn())
        .setParkingLayer(entity.getParkingLayer())
        .setParkingDistrict(entity.getParkingDistrict())
        .setRegulations(entity.getRegulations())
        .setFineMultiple(entity.getFineMultiple())
		.setId(entity.getId())
		.setStoppingTime(entity.getStoppingTime())
		.setPhone(entity.getPhone());
		return this;
	}










	public String getDistance() {
		return distance;
	}



	public void setDistance(String distance) {
		this.distance = distance;
	}




	/* 停车场时间
	 * @return
	 */
	public String getStoppingTime() {
		return stoppingTime;
	}
	/**
	 * 停车场时间
	 * @return 
	 * @return
	 */
	public AttractionsVo setStoppingTime(String stoppingTime) {
		this.stoppingTime = stoppingTime;
		return this;
	}
	/**
	 * 停车场电话
	 * @return
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 停车场电话
	 * @return 
	 * @return
	 */
	public AttractionsVo setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	/**
	 * @return 景区id
	 */
	public Long getAttractionsId() {
		return attractionsId;
	}
	/**
	 * @param 景点id
	 * @return 
	 */
	public AttractionsVo setAttractionsId(Long attractionsId) {
		this.attractionsId = attractionsId;
		return this;
	}

	/**
	 * 停车场图片 
	 */
	public String getParkingImg() {
		return parkingImg;
	}
	/**
	 * 停车场图片
	 * @return 
	 */
	public AttractionsVo setParkingImg(String parkingImg) {
		this.parkingImg = parkingImg;
		return this;
	}	
	/**
	 * 总共停放车位
	 */
	public int getCommonParking() {
		return commonParking;
	}
	/**
	 * 总共停放车位
	 * @return 
	 */
	public AttractionsVo setCommonParking(int commonParking) {
		this.commonParking = commonParking;
		return this;
	}
	/**
	 * 已用多少车位
	 */
	public int getUsedParking() {
		return usedParking;
	}
	/** 
	 * 已用多少车位
	 * @return 
	 */
	public AttractionsVo setUsedParking(int usedParking) {
		this.usedParking = usedParking;
		return this;
	}
	/**
	 * 室内室外  0室内1 室外 
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 室内室外  0室内1 室外 
	 * @return 
	 */
	public AttractionsVo setPosition(String position) {
		this.position = position;
		return this;
	}
	/** 
	 * 是否有充电柱  0无 1有
	 */
	public String getChargingColumn() {
		return chargingColumn;
	}
	/**
	 * 是否有充电柱  0无 1有
	 * @return 
	 */
	public AttractionsVo setChargingColumn(String chargingColumn) {
		this.chargingColumn = chargingColumn;
		return this;
	}
	/**
	 * 景区停车层
	 */
	public String getParkingLayer() {
		return parkingLayer;
	}
	/**
	 * 景区停车层
	 * @return 
	 */
	public AttractionsVo setParkingLayer(String parkingLayer) {
		this.parkingLayer = parkingLayer;
		return this;
	}
	/**
	 * 景区规定条例
	 */
	public String getParkingDistrict() {
		return parkingDistrict;
	}
	/**
	 * 景区规定条例 
	 * @return 
	 */
	@Column(length=255)
	public AttractionsVo setParkingDistrict(String parkingDistrict) {
		this.parkingDistrict = parkingDistrict;
		return this;
	}
	/**
	 * 罚款倍数 
	 */
	public int getFineMultiple() {
		return fineMultiple;
	}
	/**
	 * 罚款倍数
	 * @return 
	 */
	public AttractionsVo setFineMultiple(int fineMultiple) {
		this.fineMultiple = fineMultiple;
		return this;
	}
	/**
	 * @return 停车场条例
	 */
	public String getRegulations() {
		return regulations;
	}
	/**
	 * 停车场条例
	 * @return 
	 */
	public AttractionsVo setRegulations(String regulations) {
		this.regulations = regulations;
		return this;
	}
	/**
	 * 停车场地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 停车场地址
	 */
	public AttractionsVo setAddress(String address) {
		this.address = address;
		return this;
	}
	/**
	 * 停车场名称
	 */
	public String getParkingName() {
		return parkingName;
	}
	/**
	 * 停车场名称
	 */
	public AttractionsVo setParkingName(String parkingName) {
		this.parkingName = parkingName;
		return this;
	}



}
