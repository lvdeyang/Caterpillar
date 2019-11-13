package com.guolaiwan.bussiness.admin.enumeration;

public enum ActivityBundleType {

	//类型
	VOTE("投票"),

	//打折
	PRODUCT("商品");

	private String name;

	private ActivityBundleType(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public String getFiled() throws Exception{
		if(this.equals(VOTE)){
			return "VOTE";
		}else if(this.equals(PRODUCT)){
			return "PRODUCT";
		}else{
			throw new Exception("错误的活动类型！");
		}
	}

	public static ActivityBundleType fromName(String name) throws Exception{
		if("投票".equals(name)){
			return VOTE;
		}else if("商品".equals(name)){
			return PRODUCT;
		}else {
			throw new Exception("错误的活动类型："+name);
		}
	}

	public static ActivityBundleType fromString(String s) throws Exception{
		if("VOTE".equals(s)){
			return VOTE;
		}else if("PRODUCT".equals(s)){
			return PRODUCT;
		}else {
			throw new Exception("错误的活动类型："+s);
		}
	}

}
