package com.guolaiwan.bussiness.Parking.po;


import javax.persistence.Entity;

import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
/**
 * 
 * @author 车辆信息表
 *
 */
@Entity
@Table(name = "t_vehicle_table")
public class VehiclePO extends AbstractBasePO{
	//用户id
	private Long vehicleId;
	//车牌号
	private String number;
	//车型
	private String type;
	
	/**
	 * 
	 * @return 用户id
	 */
	public Long getVehicleid() {
		return vehicleId;
	}
	/**
	 * 
	 * @param 用户id
	 */
	public void setVehicleid(Long userId) {
		this.vehicleId = userId;
	}
	/**
	 *
	 * @return 车牌号
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * 
	 * @param 车牌号
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * 
	 * @return 车型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 
	 * @param 车型
	 */
	public void setType(String type) {
		this.type = type;
	}
	



}
