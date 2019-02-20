package com.guolaiwan.bussiness.admin.enumeration;

public enum LiveType {

	// 普通用户
	USER("普通用户"),
	// 商户
	MERCHANT("商户");
	
private String name;
	
	private LiveType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(MERCHANT)){
			return "MERCHANT";
		}else if(this.equals(USER)){
			return "USER";
		}else{
			throw new Exception("错误的主播类型！");
		}
	}
	
	
	
	public static LiveType fromName(String name) throws Exception{
		if("普通用户".equals(name)){
			return USER;
		}else if("商户".equals(name)){
			return MERCHANT;
		}else {
			throw new Exception("错误的主播类型："+name);
		}
	}
	
	public static LiveType fromString(String s) throws Exception{
		if("USER".equals(s)){
			return USER;
		}else if("MERCHANT".equals(s)){
			return MERCHANT;
		}else {
			throw new Exception("错误的主播类型："+s);
		}
	}
	
}
