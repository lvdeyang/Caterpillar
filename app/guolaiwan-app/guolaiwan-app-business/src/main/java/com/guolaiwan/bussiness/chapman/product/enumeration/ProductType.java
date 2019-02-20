package com.guolaiwan.bussiness.chapman.product.enumeration;

/**
 * 产品类型
 * lvdeyang 2017年6月22日
 */
public enum ProductType {

	//餐饮
	RESTAURANT("餐饮"),
	
	//景点
	SCENERY("景点");
	
	private String name;
	
	private ProductType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static ProductType fromName(String name) throws Exception{
		if(name == null){
			return null;
		}else if("餐饮".equals(name)){
			return RESTAURANT;
		}else if("景点".equals(name)){
			return SCENERY;
		}else{
			throw new Exception("错误的产品类型："+name);
		}
	}
	
	public static ProductType fromString(String s) throws Exception{
		if("RESTAURANT".equals(s)){
			return RESTAURANT;
		}else if("SCENERY".equals(s)){
			return SCENERY;
		}else{
			throw new Exception("错误的产品类型："+s);
		}
	}
	
}
