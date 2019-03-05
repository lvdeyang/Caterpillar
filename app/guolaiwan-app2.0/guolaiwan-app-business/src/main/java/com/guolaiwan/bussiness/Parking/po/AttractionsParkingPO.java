package com.guolaiwan.bussiness.Parking.po;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 
 * @author 景区  停车场表
 *
 */
@Entity
@Table(name = "t_attractionsparking_table")
public class AttractionsParkingPO extends AbstractBasePO {
	// 景区id
	private  long attractionsId;
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

	//停车费用
	private  int cost;
	//停车场时间
	private String stoppingTime;

	//停车场电话
	private String phone;







	/**
	 * 停车费用
	 * @return
	 */

	public int getCost() {
		return cost;
	}

	/**
	 * 停车费用
	 * @return
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	public void setAttractionsId(long attractionsId) {
		this.attractionsId = attractionsId;
	}
	/**
	 * 停车场时间
	 * @return
	 */
	public String getStoppingTime() {
		return stoppingTime;
	}
	/**
	 * 停车场时间
	 * @return
	 */
	public void setStoppingTime(String stoppingTime) {
		this.stoppingTime = stoppingTime;
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
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return 景区id
	 */
	public Long getAttractionsId() {
		return attractionsId;
	}
	/**
	 * @param 景点id
	 */
	public void setAttractionsId(Long attractionsId) {
		this.attractionsId = attractionsId;
	}

	/**
	 * 停车场图片 
	 */
	public String getParkingImg() {
		return parkingImg;
	}
	/**
	 * 停车场图片
	 */
	public void setParkingImg(String parkingImg) {
		this.parkingImg = parkingImg;
	}	
	/**
	 * 总共停放车位
	 */
	public int getCommonParking() {
		return commonParking;
	}
	/**
	 * 总共停放车位
	 */
	public void setCommonParking(int commonParking) {
		this.commonParking = commonParking;
	}
	/**
	 * 已用多少车位
	 */
	public int getUsedParking() {
		return usedParking;
	}
	/** 
	 * 已用多少车位
	 */
	public void setUsedParking(int usedParking) {
		this.usedParking = usedParking;
	}
	/**
	 * 室内室外  0室内1 室外 
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 室内室外  0室内1 室外 
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/** 
	 * 是否有充电柱  0无 1有
	 */
	public String getChargingColumn() {
		return chargingColumn;
	}
	/**
	 * 是否有充电柱  0无 1有
	 */
	public void setChargingColumn(String chargingColumn) {
		this.chargingColumn = chargingColumn;
	}
	/**
	 * 景区停车层
	 */
	public String getParkingLayer() {
		return parkingLayer;
	}
	/**
	 * 景区停车层
	 */
	public void setParkingLayer(String parkingLayer) {
		this.parkingLayer = parkingLayer;
	}
	/**
	 * 景区规定条例
	 */
	public String getParkingDistrict() {
		return parkingDistrict;
	}
	/**
	 * 景区规定条例 
	 */
	@Column(length=3000)
	public void setParkingDistrict(String parkingDistrict) {
		this.parkingDistrict = parkingDistrict;
	}
	/**
	 * 罚款倍数 
	 */
	public int getFineMultiple() {
		return fineMultiple;
	}
	/**
	 * 罚款倍数
	 */
	public void setFineMultiple(int fineMultiple) {
		this.fineMultiple = fineMultiple;
	}
	/**
	 * @return 停车场条例
	 */
	public String getRegulations() {
		return regulations;
	}
	/**
	 * 停车场条例
	 */
	public void setRegulations(String regulations) {
		this.regulations = regulations;
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
	public void setAddress(String address) {
		this.address = address;
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
	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}






}
