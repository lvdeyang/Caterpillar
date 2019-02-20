package com.guolaiwan.bussiness.admin.po;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_logisticspoint")
public class LogisticsPoint extends AbstractBasePO {

	private String name;
	// 坐标经度
	private String longitude;
	// 坐标纬度
	private String latitude;
	
	private LogisticsPo logisticsPo;
	
	private Set<LogisticsPeople> logisticsPeoples=new HashSet<LogisticsPeople>();
	
	
	
	 @OneToMany(mappedBy = "logisticsPoint", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	public Set<LogisticsPeople> getLogisticsPeoples() {
		return logisticsPeoples;
	}
	public void setLogisticsPeoples(Set<LogisticsPeople> logisticsPeoples) {
		this.logisticsPeoples = logisticsPeoples;
	}
	@ManyToOne
	@JoinColumn(name = "logisticsid")
	public LogisticsPo getLogisticsPo() {
		return logisticsPo;
	}
	public void setLogisticsPo(LogisticsPo logisticsPo) {
		this.logisticsPo = logisticsPo;
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
	
	
}
