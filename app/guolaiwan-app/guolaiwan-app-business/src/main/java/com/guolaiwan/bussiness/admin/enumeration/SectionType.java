package com.guolaiwan.bussiness.admin.enumeration;

public enum SectionType {

	//标题
	TEXT("文本"),
	
	//商品
	PRODUCT("商品"),
	
	//侧边栏
	CHAT("聊天室"),
	
	//广告
	ADVERTISEMENT("广告");
	
	private String name;
	
	private SectionType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static SectionType fromName(String name) throws Exception{
		if("文本".equals(name)){
			return TEXT;
		}else if("商品".equals(name)){
			return PRODUCT;
		}else if("聊天室".equals(name)){
			return CHAT;
		}else if("广告".equals(name)){
			return ADVERTISEMENT;
		}else {
			throw new Exception("错误的版块类型："+name);
		}
		
	}
	
	public static SectionType fromString(String s) throws Exception{
		if("TEXT".equals(s)){
			return TEXT;
		}else if("PRODUCT".equals(s)){
			return PRODUCT;
		}else if("CHAT".equals(s)){
			return CHAT;
		}else if("ADVERTISEMENT".equals(s)){
			return ADVERTISEMENT;
		}else {
			throw new Exception("错误的版块类型："+s);
		}
	}
	
}
