package com.guolaiwan.app.web.smartParking.vo;


import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ParkingPositionVo   extends AbstractBaseVO<ParkingPositionVo,ParkingPositionPO>{
	// 景区 车位id
	private long positionId;
	//车位编号
	private long positionNumber;
	// 车位信息   大小车位
	private String positionInformation;
	//使用情况 0无车 1有车
	private long useCondition;
	
	
	@Override
	public ParkingPositionVo set(ParkingPositionPO entity) throws Exception {
		this.setPositionId(entity.getPositionId())
		.setPositionNumber(entity.getPositionNumber())
		.setPositionInformation(entity.getPositionInformation())
		.setUseCondition(entity.getUseCondition());
		return this;
	}
	
		
	/**
	 * 景区 id  车位
	 */
	public long getPositionId() {
		return positionId;
	}
	/**
	 * 景区  id 车位
	 * @return 
	 */
	public ParkingPositionVo setPositionId(long positionId) {
		this.positionId = positionId;
		return this;
	}
	/**
	 * 车位编号
	 */
	public long getPositionNumber() {
		return positionNumber;
	}
	/**
	 * 车位编号
	 * @return 
	 */
	public ParkingPositionVo setPositionNumber(long positionNumber) {
		this.positionNumber = positionNumber;
		return this;
	}
	/**
	 * 车位信息   大小车位
	 */
	public String getPositionInformation() {
		return positionInformation;
	}
	/**
	 * 车位信息   大小车位 
	 * @return 
	 */
	public ParkingPositionVo setPositionInformation(String positionInformation) {
		this.positionInformation = positionInformation;
		return this;
	}
	/**
	 * 使用情况 0无车 1有车
	 */
	public long getUseCondition() {
		return useCondition;
	}
	/**
	 * 使用情况 0无车 1有车
	 * @return 
	 */
	public ParkingPositionVo setUseCondition(long useCondition) {
		this.useCondition = useCondition;
		return this;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
