package com.guolaiwan.bussiness.admin.enumeration;

public enum FilterType {

	//商品类型
	PRODUCTTYPE("商品类型"),
	
	//标签过滤
	TAG("标签"),
	
	//时间过滤
	TIME("上架时间"),
	
	//数量过滤
	NUM("数量");
	
	private String name;
	
	private FilterType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(PRODUCTTYPE)){
			return "type";
		}else if(this.equals(TAG)){
			return "tag";
		}else if(this.equals(TIME)){
			return "updateTime";
		}else if(this.equals(NUM)){
			return "limit";
		}else{
			throw new Exception("错误的过滤器类型");
		}
	}
	
	public static FilterType fromName(String name) throws Exception{
		if("商品类型".equals(name)){
			return PRODUCTTYPE;
		}else if("标签".equals(name)){
			return TAG;
		}else if("上架时间".equals(name)){
			return TIME;
		}else if("数量".equals(name)){
			return NUM;
		}else {
			throw new Exception("错误的过滤器类型："+name);
		}
	}
	
	public static FilterType fromString(String s) throws Exception{
		if("PRODUCTTYPE".equals(s)){
			return PRODUCTTYPE;
		}else if("TAG".equals(s)){
			return TAG;
		}else if("TIME".equals(s)){
			return TIME;
		}else if("NUM".equals(s)){
			return NUM;
		}else {
			throw new Exception("错误的过滤器类型："+s);
		}
	}
	
}
