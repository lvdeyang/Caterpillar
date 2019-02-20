package com.guolaiwan.bussiness.chapman.source.enumeration;

public enum SourceMapType {

	//首页图片
	HOMEPAGE("首页图片"),
	
	//缩略图
	THUMBNAIL("缩略图"),
	
	//详情图
	INFO("详情图");
	
	private String name;
	
	private SourceMapType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static SourceMapType fromName(String name) throws Exception{
		if("首页图片".equals(name)){
			return HOMEPAGE;
		}else if("缩略图".equals(name)){
			return THUMBNAIL;
		}else if("详情图".equals(name)){
			return INFO;
		}else{
			throw new Exception("错误的资源映射类型："+name);
		}
	}
	
	public static SourceMapType fromString(String s) throws Exception{
		if("HOMEPAGE".equals(s)){
			return HOMEPAGE;
		}else if("THUMBNAIL".equals(s)){
			return THUMBNAIL;
		}else if("INFO".equals(s)){
			return INFO;
		}else {
			throw new Exception("错误的资源映射类型："+s);
		}
	}
	
}
