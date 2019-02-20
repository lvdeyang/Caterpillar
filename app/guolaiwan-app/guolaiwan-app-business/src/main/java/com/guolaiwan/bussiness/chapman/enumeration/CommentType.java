package com.guolaiwan.bussiness.chapman.enumeration;

/**
 * 评论类型
 * lvdeyang 2017年6月22日
 */
public enum CommentType {

	//好评
	PRAISE("好评"),
	
	//中评
	IN("中评"),
	
	//差评
	BAD("差评");
	
	private String name;
	
	private CommentType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static CommentType fromString(String name) throws Exception{
		
		if(name == null){
			return null;
		}else if("好评".equals(name)){
			return PRAISE;
		}else if("中评".equals(name)){
			return IN;
		}else if("差评".equals(name)){
			return BAD;
		}else{
			throw new Exception("错误的评论类型："+name);
		}
		
	}
	
}
