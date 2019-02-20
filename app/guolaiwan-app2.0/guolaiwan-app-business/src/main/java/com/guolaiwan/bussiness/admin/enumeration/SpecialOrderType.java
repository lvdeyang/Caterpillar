package com.guolaiwan.bussiness.admin.enumeration;

public enum SpecialOrderType {

	//租车订单
	CARRENTAL(00010l, "租车订单");
	
	private long code;
	
	private String name;
	
	private SpecialOrderType(long code, String name){
		this.code = code;
		this.name = name;
	}
	
	public long getCode(){
		return this.code;
	}
	
	public String getName(){
		return this.name;
	}
	
}
