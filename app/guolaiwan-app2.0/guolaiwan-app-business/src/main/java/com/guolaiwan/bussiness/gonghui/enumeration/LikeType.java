package com.guolaiwan.bussiness.gonghui.enumeration;

public enum LikeType {

	RECORD("录制"),

	ARTICLE("文章");

	private String name;

	private LikeType(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public String getFiled() throws Exception{
		if(this.equals(RECORD)){
			return "RECORD";
		}else if(this.equals(ARTICLE)){
			return "ARTICLE";
		}else{
			throw new Exception("错误的类型！");
		}
	}

	public static LikeType fromName(String name) throws Exception{
		if("录制".equals(name)){
			return RECORD;
		}else if("文章".equals(name)){
			return ARTICLE;
		}else {
			throw new Exception("错误的类型："+name);
		}
	}

	public static LikeType fromString(String s) throws Exception{
		if("RECORD".equals(s)){
			return RECORD;
		}else if("ARTICLE".equals(s)){
			return ARTICLE;
		}else {
			throw new Exception("错误的类型："+s);
		}
	}

}
