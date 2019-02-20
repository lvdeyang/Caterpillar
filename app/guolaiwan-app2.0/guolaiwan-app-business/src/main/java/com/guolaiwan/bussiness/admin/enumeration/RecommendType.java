package com.guolaiwan.bussiness.admin.enumeration;

public enum RecommendType {

	//类型
	CAROUSEL("轮播图推荐"),
	
	RADIO("视频推荐"),
	
	SPECIAL("特别推荐");
	
	private String name;
	
	private RecommendType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(CAROUSEL)){
			return "轮播图推荐";
		}else if(this.equals(RADIO)){
			return "视频推荐";
		}else if(this.equals(SPECIAL)){
			return "特别推荐";
		}else{
			throw new Exception("错误的推荐类型！");
		}
	}
	
	public static RecommendType fromName(String name) throws Exception{
		if("轮播图推荐".equals(name)){
			return CAROUSEL;
		}else if("视频推荐".equals(name)){
			return RADIO;
		}else if("特别推荐".equals(name)){
			return SPECIAL;
		}else {
			throw new Exception("错误的推荐类型："+name);
		}
	}
	
	public static RecommendType fromString(String s) throws Exception{
		if("CAROUSEL".equals(s)){
			return CAROUSEL;
		}else if("RADIO".equals(s)){
			return RADIO;
		}else if("SPECIAL".equals(s)){
			return SPECIAL;
		}else {
			throw new Exception("错误的推荐类型："+s);
		}
	}
	
}
