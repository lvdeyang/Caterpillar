package com.guolaiwan.bussiness.Parking.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_appmessage")
public class AppMessagePO  extends AbstractBasePO{  //万佛园 app信息 
	private long userId;
	
	private long merchantId;
	
	//景区名称
	private String merchantName;

	//导览  初始显示位置经度
	private String  locationLongitude;
	
	
	//导览  初始显示位置经度
	private String  locationLatitude;

    
	public String getMerchantName() {
		return merchantName;
	}


	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public long getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}


	public String getLocationLongitude() {
		return locationLongitude;
	}


	public void setLocationLongitude(String locationLongitude) {
		this.locationLongitude = locationLongitude;
	}


	public String getLocationLatitude() {
		return locationLatitude;
	}


	public void setLocationLatitude(String locationLatitude) {
		this.locationLatitude = locationLatitude;
	}
	
	
}
