package com.chenxi.web.classes;

public enum Moudular {
	CEPING("测评"),
	WEIXIU("维修");
	
	
    private String name;
	
	private Moudular(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(CEPING)){
			return "测评";
		}else if(this.equals(WEIXIU)){
			return "维修";
		}else {
			throw new Exception("错误的");
		}
	}
	
	public static Moudular fromName(String name) throws Exception{
		if("测评".equals(name)){
			return CEPING;
		}else if("维修".equals(name)){
			return WEIXIU;
		} else {
			throw new Exception("错误的："+name);
		}
	}
	
	public static Moudular fromString(String s) throws Exception{
		if("CEPING".equals(s)){
			return CEPING;
		}else if("WEIXIU".equals(s)){
			return WEIXIU;
		} else {
			throw new Exception("错误的："+s);
		}
	}
}
