package com.guolaiwan.app.web.enumeration;

public enum LoginCacheName {

	KEY("GLWCATCHE001"),
	
	USER("user"),
	
	CHAPMAN("chapman"),
	
	ROOT("root");
	
	private String name;
	
	private LoginCacheName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
