package com.guolaiwan.bussiness.admin.enumeration;

public enum PageType {

	//首页
	HOMEPAGE("首页"),
	
	//详情
	INFO("详情");
	
	private String name;
	
	private PageType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static PageType fromName(String name) throws Exception{
		if("首页".equals(name)){
			return HOMEPAGE;
		}else if("详情".equals(name)){
			return INFO;
		}else{
			throw new Exception("错误的页面类型："+name);
		}
	}
	
	public static PageType fromString(String s) throws Exception{
		if("HOMEPAGE".equals(s)){
			return HOMEPAGE;
		}else if("INFO".equals(s)){
			return INFO;
		}else {
			throw new Exception("错误的页面类型："+s);
		}
	}
}
