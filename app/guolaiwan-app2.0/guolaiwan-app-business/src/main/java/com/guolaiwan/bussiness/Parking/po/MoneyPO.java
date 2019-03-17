package com.guolaiwan.bussiness.Parking.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;
/**
 * 停车场 车位管理表
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_ParkingMoney_table")
public class MoneyPO  extends AbstractBasePO{
	//停车场区id
	private  Long attractionsId;
	//车位型
	private String parkingModel;
	//停车费用 
	private int  Money;
	//区域 
	private String  area;
	//层域 
	private String  tier;






	/**
	 * 层域 
	 * @return
	 */

	public String getTier() {
		return tier;
	}

	/**
	 * 层域 
	 * @return
	 */
	public void setTier(String tier) {
		this.tier = tier;
	}


	/**
	 * 区域 
	 * @return
	 */
	public String getArea() {
		return area;
	}


	/**
	 * 区域 
	 * @return
	 */
	public void setArea(String area) {
		this.area = area;
	}
	public Long getAttractionsId() {
		return attractionsId;
	}
	/**
	 * 停车场区id
	 * @return
	 */
	public void setAttractionsId(Long attractionsId) {
		this.attractionsId = attractionsId;
	}

	/**
	 * 车位型
	 * @return
	 */
	public String getParkingModel() {
		return parkingModel;
	}
	/**
	 * 车位型
	 * @return
	 */
	public void setParkingModel(String parkingModel) {
		this.parkingModel = parkingModel;
	}
	/**
	 * 钱数
	 * @return
	 */
	public int getMoney() {
		return Money;
	}
	/**
	 * 钱
	 * @return
	 */
	public void setMoney(int money) {
		Money = money;
	}



}
