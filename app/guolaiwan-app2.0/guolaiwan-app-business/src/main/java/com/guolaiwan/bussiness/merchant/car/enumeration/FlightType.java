package com.guolaiwan.bussiness.merchant.car.enumeration;

public enum FlightType {

	//商品类型
	CARTIME("固定时间"),
	
	//标签过滤
	CONTRACT("包车");
	
	private String name;
	
	private FlightType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	

	public static FlightType fromName(String name) throws Exception{
		if("固定时间".equals(name)){
			return CARTIME;
		}else if("包车".equals(name)){
			return CONTRACT;
		}else {
			throw new Exception("错误的班次类型："+name);
		}
	}

	public static FlightType fromString(String s) throws Exception{
		if("CARTIME".equals(s)){
			return CARTIME;
		}else if("CONTRACT".equals(s)){
			return CONTRACT;
		}else {
			throw new Exception("错误的班次类型："+s);
		}
	}
	
}