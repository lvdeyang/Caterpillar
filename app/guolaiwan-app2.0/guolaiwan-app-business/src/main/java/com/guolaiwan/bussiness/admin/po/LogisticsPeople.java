package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "t_sys_logisticspeople")
public class LogisticsPeople extends AbstractBasePO {

	private String name;
	// 坐标经度
	private String longitude;
	// 坐标纬度
	private String latitude;
	
	private LogisticsPoint logisticsPoint;
	
	private String phone;
	
	
	@ManyToOne
	@JoinColumn(name = "logisticspointid")
	public LogisticsPoint getLogisticsPoint() {
		return logisticsPoint;
	}
	public void setLogisticsPoint(LogisticsPoint logisticsPoint) {
		this.logisticsPoint = logisticsPoint;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
