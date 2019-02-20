package com.guolaiwan.bussiness.user.enumeration;

public enum UserSexType {

	//男
	MALE("男"),
	
	//女
	FEMALE("女");
	
	private String name;
	
	private UserSexType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static UserSexType fromName(String name) throws Exception{
		if("男".equals(name)){
			return MALE;
		}else if("女".equals(name)){
			return FEMALE;
		}else{
			throw new Exception("错误的性别类型："+name);
		}
	}
	
	public static UserSexType fromString(String s) throws Exception{
		if("MALE".equals(s)){
			return MALE;
		}else if("FEMALE".equals(s)){
			return FEMALE;
		}else{
			throw new Exception("错误的性别类型："+s);
		}
	}
	
}
