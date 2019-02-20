package com.guolaiwan.bussiness.distribute.classify;

public enum RecommendType {
	SWIPER("SWIPER"),
	DMODEL("DMODEL"),
	DLIST("DLIST");
	
	private String name;
	
	private RecommendType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
