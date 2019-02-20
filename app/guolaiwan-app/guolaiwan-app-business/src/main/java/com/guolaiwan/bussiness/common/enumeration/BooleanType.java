package com.guolaiwan.bussiness.common.enumeration;

public enum BooleanType {

	//是
	TRUE("是"),
	
	//否
	FALSE("否");
	
	private String name;
	
	private BooleanType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static BooleanType fromName(String name) throws Exception{
		if("是".equals(name)){
			return TRUE;
		}else if("否".equals(name)){
			return FALSE;
		}else {
			throw new Exception("错误的布尔值类型："+name);
		}
	}
	
	public static BooleanType fromString(String s) throws Exception{
		if("TRUE".equals(s)){
			return TRUE;
		}else if("FALSE".equals(s)){
			return FALSE;
		}else {
			throw new Exception("错误的布尔值类型："+s);
		}
	}
	
}
