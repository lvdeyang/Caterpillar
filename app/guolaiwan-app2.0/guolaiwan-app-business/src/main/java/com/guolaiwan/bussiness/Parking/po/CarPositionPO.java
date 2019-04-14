package com.guolaiwan.bussiness.Parking.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_ParkingLocation_table")
public class CarPositionPO  extends AbstractBasePO{
	//景区停车场id
	private  Long attractionsId;
	// 车位 经度
	private String  longitude;
	//车位 图
	private String latitude;
	//车位层
	private String number;
	//车位那区
	private String  district;
	//车位状态  未使用 正在使用
	private String state;
	
	
	
	/**
	 * 车位状态  未使用 正在使用
	 * @return
	 */
	public String getState() {
		return state;
	}
	/**
	 * 车位状态  未使用 正在使用
	 * @return
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 景区停车场id
	 * @return
	 */
	public Long getAttractionsId() {
		return attractionsId;
	}
	/**
	 * 景区停车场id
	 * @return
	 */
	public void setAttractionsId(Long attractionsId) {
		this.attractionsId = attractionsId;
	}
	/**
	 * 景区车位经度
	 * @return
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * 景区车位经度
	 * @return
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 景区车位图
	 * @return
	 */
	@Lob
	@Column(columnDefinition="LONGTEXT")
	public String getLatitude() {
		return latitude;
	}
	/**
	 * 景区车位纬图
	 * @return
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 景区车位层数
	 * @return
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * 景区车位层数
	 * @return
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * 车位那区
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * 车位那区
	 */
	public void setDistrict(String district) {
		this.district = district;
	}


}
