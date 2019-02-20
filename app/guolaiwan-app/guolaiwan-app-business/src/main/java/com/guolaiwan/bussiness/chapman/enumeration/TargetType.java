package com.guolaiwan.bussiness.chapman.enumeration;

public enum TargetType {

	//商户
	CHAPMAN("商户"),
	
	//商品
	PRODUCT("商品"),
	
	//商品明细
	PRODUCTDETAIL("商品明细");
	
	private String name;
	
	private TargetType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
