package com.guolaiwan.bussiness.common.enumeration;

/**
 * 代码分类
 * lvdeyang 2017年7月5日
 */
public enum CodeType {

	/****************************
	 *          level_1
	 ****************************/
	
	//类目
	CATEGORY("栏目"),
	
	//标签
	TAG("标签");
	
	private String name;
	
	private CodeType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static CodeType fromName(String name) throws Exception{
		if("标签".equals(name)){
			return TAG;
		}else if("栏目".equals(name)){
			return CATEGORY;
		}else{
			throw new Exception("错误的代码类型："+name);
		}
	}
	
	public static CodeType fromString(String s) throws Exception{
		if("TAG".equals(s)){
			return TAG;
		}else if("CATEGORY".equals(s)){
			return CATEGORY;
		}else{
			throw new Exception("错误的代码类型："+s);
		}
	}
	
}
