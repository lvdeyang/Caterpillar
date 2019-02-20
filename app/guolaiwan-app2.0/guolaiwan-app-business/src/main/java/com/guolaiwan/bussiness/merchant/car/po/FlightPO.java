package com.guolaiwan.bussiness.merchant.car.po;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

import com.guolaiwan.bussiness.merchant.car.enumeration.FlightType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_Sys_car_Flight")
public class FlightPO extends AbstractBasePO{
	//路线名称
	private String name;
	//司机名称
	private String driver;
	//司机id
	private long driverId;
	//班次类型
	private FlightType flightType;
	//出发时间
	private Time  goTime;
	//价钱
	private long price;
	//行程时间
	private String allTime;
	//绑定路线
	private RoutePO route;

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}

	public FlightType getFlightType() {
		return flightType;
	}

	public void setFlightType(FlightType flightType) {
		this.flightType = flightType;
	}

	

	public Time getGoTime() {
		return goTime;
	}

	public void setGoTime(Time goTime) {
		this.goTime = goTime;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getAllTime() {
		return allTime;
	}

	public void setAllTime(String allTime) {
		this.allTime = allTime;
	}

	@ManyToOne
	@JoinColumn(name = "route_id")
	public RoutePO getRoute() {
		return route;
	}

	public void setRoute(RoutePO route) {
		this.route = route;
	}
	

}
