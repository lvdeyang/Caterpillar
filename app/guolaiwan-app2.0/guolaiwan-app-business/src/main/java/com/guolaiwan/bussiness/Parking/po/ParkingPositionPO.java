package com.guolaiwan.bussiness.Parking.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 
 * @author 景区 车位表
 *
 */
@Entity
@Table(name = "t_parkingposition_table")
public class ParkingPositionPO extends AbstractBasePO {
	
	// 车位信息   大小车位

	private Long positionId;
	// 车位编号
	private int positionNumber;
	// 车位信息 大小车位

	private String positionInformation;


	// 使用情况 0无车 1有车
	private int useCondition;

	/**
	 * 景区 id 车位
	 */

	public Long getPositionId() {
		return positionId;
	}

	/**
	 * 景区 id 车位
	 */

	public void setPositionId(Long positionId) {

		this.positionId = positionId;
	}

	/**
	 * 车位编号
	 */
	public int getPositionNumber() {
		return positionNumber;
	}

	/**
	 * 车位编号
	 */
	public void setPositionNumber(int positionNumber) {
		this.positionNumber = positionNumber;
	}

	/**
	 * 车位信息 大小车位
	 */
	public String getPositionInformation() {
		return positionInformation;
	}

	/**
	 * 车位信息 大小车位
	 */
	public void setPositionInformation(String positionInformation) {
		this.positionInformation = positionInformation;
	}

	/**
	 * 使用情况 0无车 1有车
	 */
	public int getUseCondition() {
		return useCondition;
	}

	/**
	 * 使用情况 0无车 1有车
	 */
	public void setUseCondition(int useCondition) {
		this.useCondition = useCondition;
	}

}
