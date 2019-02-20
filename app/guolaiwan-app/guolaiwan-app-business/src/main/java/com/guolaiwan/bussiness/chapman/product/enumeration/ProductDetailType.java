package com.guolaiwan.bussiness.chapman.product.enumeration;

/**
 * 产品明细类型
 * lvdeyang 2017年7月3日
 */
public enum ProductDetailType {

	//首页扩展描述
	HOMEPAGE("首页扩展描述"),
	
	//详情页描述
	INFO("详情页描述"),
	
	//产品描述
	PRODUCT("产品描述");
	
	private String name;
	
	private ProductDetailType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static ProductDetailType fromName(String name) throws Exception{
		if("首页扩展描述".equals(name)){
			return HOMEPAGE;
		}else if("详情页描述".equals(name)){
			return INFO;
		}else if("产品描述".equals(name)){
			return PRODUCT;
		}else {
			throw new Exception("错误的产品明细类型："+name);
		}
	}
	
	public static ProductDetailType fromString(String name) throws Exception{
		if("HOMEPAGE".equals(name)){
			return HOMEPAGE;
		}else if("INFO".equals(name)){
			return INFO;
		}else if("PRODUCT".equals(name)){
			return PRODUCT;
		}else {
			throw new Exception("错误的产品明细类型："+name);
		}
	}
}
