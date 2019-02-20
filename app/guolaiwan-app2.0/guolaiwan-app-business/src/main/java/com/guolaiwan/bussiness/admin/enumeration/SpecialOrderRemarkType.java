package com.guolaiwan.bussiness.admin.enumeration;

public enum SpecialOrderRemarkType {

	//分隔符
	SEPARATOR("分隔符", "@@@"),
	
	//备用电话
	BACKPHONE("备用电话", "*001*"),
	
	//其他信息
	OTHER("其他信息",  "*002*");
	
	private String code;
	
	private String name;
	
	private SpecialOrderRemarkType(String name, String code){
		this.name = name;
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public String getName(){
		return this.name;
	}
	
}
