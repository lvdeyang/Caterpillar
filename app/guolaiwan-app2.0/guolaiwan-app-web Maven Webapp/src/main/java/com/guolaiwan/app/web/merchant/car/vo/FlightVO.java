package com.guolaiwan.app.web.merchant.car.vo;


import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.merchant.car.enumeration.FlightType;
import com.guolaiwan.bussiness.merchant.car.po.FlightPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class FlightVO extends AbstractBaseVO<FlightVO, FlightPO>{
	//班次名称
	private String name;
	//路线名称
	private String routeName;
	//路线
	private String lab;
	//司机名称
	private String driver;
	//司机id
	private long driverId;
	//班次类型
	private String flightType;
	//出发时间
	private String  goTime;
	//价钱
	private String price;
	//行程时间
	private String allTime;
	
	public String getName() {
		return name;
	}

	public FlightVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getRouteName() {
		return routeName;
	}

	public FlightVO setRouteName(String routeName) {
		this.routeName = routeName;
		return this;
	}
	
	

	public String getLab() {
		return lab;
	}

	public FlightVO setLab(String lab) {
		this.lab = lab;
		return this;
	}

	public String getDriver() {
		return driver;
	}

	public FlightVO setDriver(String driver) {
		this.driver = driver;
		return this;
	}

	public long getDriverId() {
		return driverId;
	}

	public FlightVO setDriverId(long driverId) {
		this.driverId = driverId;
		return this;
	}

	public String getFlightType() {
		return flightType;
	}

	public FlightVO setFlightType(String flightType) {
		this.flightType = flightType;
		return this;
	}

	public String getGoTime() {
		return goTime;
	}

	public FlightVO setGoTime(String goTime) {
		this.goTime = goTime;
		return this;
	}

	public String getPrice() {
		return price;
	}

	public FlightVO setPrice(String price) {
		this.price = price;
		return this;
	}

	public String getAllTime() {
		return allTime;
	}

	public FlightVO setAllTime(String allTime) {
		this.allTime = allTime;
		return this;
	}

	public FlightVO set(FlightPO entity)throws Exception {
		
		DecimalFormat df =new DecimalFormat("0.00");
		String goTime;
		if(entity.getFlightType().equals(FlightType.CONTRACT)){
			goTime = "全天发车";
		}else{
			goTime= goTimeFT24(entity.getGoTime());
		}
		
		
		String alltimeString ;
		if(entity.getAllTime()==null){
			alltimeString="暂无数据";
		}else{
			alltimeString =entity.getAllTime();
			
		}
		
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setName(entity.getName())
		.setRouteName(entity.getRoute().getName())
		.setLab(entity.getRoute().getLab())
		.setDriver(entity.getDriver())
		.setDriverId(entity.getDriverId())
		.setFlightType(entity.getFlightType().getName())
		.setGoTime(goTime)
		.setPrice(df.format((double)entity.getPrice()/100))
		.setAllTime(alltimeString);
		return this;
	}
	
	//12小时制（没写完。。）
	private String goTimeFT12(Time goTime){
		
		String[] goTimes = goTime.toString().split(":");
		int hour = Integer.parseInt(goTimes[0]);
		return "";
	}
	//24小时制
	private String goTimeFT24(Time goTime){
		return goTime.toString();
	}
}
