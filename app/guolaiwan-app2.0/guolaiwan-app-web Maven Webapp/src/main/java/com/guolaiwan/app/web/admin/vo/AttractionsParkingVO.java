package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class AttractionsParkingVO extends AbstractBaseVO<AttractionsParkingVO, AttractionsParkingPO> {
	// 景区id
	private long attractionsId;
	// 停车场名称
	private String parkingName;
	// 停车场图片
	private String parkingImg;
	// 停车场地址
	private String address;
	// 总共停放车位
	private int commonParking;
	// 已用多少车位
	private int usedParking;
	// 位置 室内 室外
	private String position;
	// 是否 有充电柱
	private String chargingColumn;
	// 景区停车层
	private String parkingLayer;
	// 景区停车区
	private String parkingDistrict;
	// 停车场条例
	private String regulations;
	// 罚款倍数
	private int fineMultiple;
	// 停车费用
	private int cost;
	// 停车场时间
	private String stoppingTime;
	// 停车场电话
	private String phone;

	public long getAttractionsId() {
		return attractionsId;
	}

	public AttractionsParkingVO setAttractionsId(long attractionsId) {
		this.attractionsId = attractionsId;
		return this;
	}

	public String getParkingName() {
		return parkingName;
	}

	public AttractionsParkingVO setParkingName(String parkingName) {
		this.parkingName = parkingName;
		return this;
	}

	public String getParkingImg() {
		return parkingImg;
	}

	public AttractionsParkingVO setParkingImg(String parkingImg) {
		this.parkingImg = parkingImg;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public AttractionsParkingVO setAddress(String address) {
		this.address = address;
		return this;
	}

	public int getCommonParking() {
		return commonParking;
	}

	public AttractionsParkingVO setCommonParking(int commonParking) {
		this.commonParking = commonParking;
		return this;
	}

	public int getUsedParking() {
		return usedParking;
	}

	public AttractionsParkingVO setUsedParking(int usedParking) {
		this.usedParking = usedParking;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public AttractionsParkingVO setPosition(String position) {
		this.position = position;
		return this;
	}

	public String getChargingColumn() {
		return chargingColumn;
	}

	public AttractionsParkingVO setChargingColumn(String chargingColumn) {
		this.chargingColumn = chargingColumn;
		return this;
	}

	public String getParkingLayer() {
		return parkingLayer;
	}

	public AttractionsParkingVO setParkingLayer(String parkingLayer) {
		this.parkingLayer = parkingLayer;
		return this;
	}

	public String getParkingDistrict() {
		return parkingDistrict;
	}

	public AttractionsParkingVO setParkingDistrict(String parkingDistrict) {
		this.parkingDistrict = parkingDistrict;
		return this;
	}

	public String getRegulations() {
		return regulations;
	}

	public AttractionsParkingVO setRegulations(String regulations) {
		this.regulations = regulations;
		return this;
	}

	public int getFineMultiple() {
		return fineMultiple;
	}

	public AttractionsParkingVO setFineMultiple(int fineMultiple) {
		this.fineMultiple = fineMultiple;
		return this;
	}

	public int getCost() {
		return cost;
	}

	public AttractionsParkingVO setCost(int cost) {
		this.cost = cost;
		return this;
	}

	public String getStoppingTime() {
		return stoppingTime;
	}

	public AttractionsParkingVO setStoppingTime(String stoppingTime) {
		this.stoppingTime = stoppingTime;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public AttractionsParkingVO setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	@Override
	public AttractionsParkingVO set(AttractionsParkingPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(entity.getUpdateTime())
			.setAttractionsId(entity.getAttractionsId())
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
			.setCost(entity.getCost())
			.setStoppingTime(entity.getStoppingTime())
			.setPhone(entity.getPhone());

		return this;
	}

}
