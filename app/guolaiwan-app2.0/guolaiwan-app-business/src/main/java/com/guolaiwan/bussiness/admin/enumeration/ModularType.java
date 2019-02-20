package com.guolaiwan.bussiness.admin.enumeration;

public enum ModularType {

	//类型
	PRODUCT("商品型"),
	
	//打折
	MERCHANT("商家型");
	
	
	private String name;
	
	private ModularType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(PRODUCT)){
			return "PRODUCT";
		}else if(this.equals(MERCHANT)){
			return "MERCHANT";
		}else{
			throw new Exception("错误的模块类型！");
		}
	}
	
	public static ModularType fromName(String name) throws Exception{
		if("商品型".equals(name)){
			return PRODUCT;
		}else if("商家型".equals(name)){
			return MERCHANT;
		}else {
			throw new Exception("错误的模块类型："+name);
		}
	}
	
	public static ModularType fromString(String s) throws Exception{
		if("PRODUCT".equals(s)){
			return PRODUCT;
		}else if("MERCHANT".equals(s)){
			return MERCHANT;
		}else {
			throw new Exception("错误的模块类型："+s);
		}
	}
	
}
