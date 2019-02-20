package com.guolaiwan.bussiness.admin.enumeration;

public enum MenuRenderType {

	//默认渲染
	DEFAULT("默认渲染"),
	
	//点击渲染
	CLICK("点击渲染");
	
	private String name;
	
	private MenuRenderType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static MenuRenderType fromName(String name) throws Exception{
		if("默认渲染".equals(name)){
			return DEFAULT;
		}else if("点击渲染".equals(name)){
			return CLICK;
		}else {
			throw new Exception("错误的渲染类型："+name);
		}
	}
	
	public static MenuRenderType fromString(String s) throws Exception{
		if("DEFAULT".equals(s)){
			return DEFAULT;
		}else if("CLICK".equals(s)){
			return CLICK;
		}else {
			throw new Exception("错误的渲染类型："+s);
		}
	}
	
}
