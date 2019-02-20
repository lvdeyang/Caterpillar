package com.guolaiwan.bussiness.admin.enumeration;

public enum ProductSaleType {

	//类型
	CASH("现金"),
	
	//打折
	GOLD("金币"),
	
	//积分
	JIFEN("积分");
	
	private String name;
	
	private ProductSaleType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(CASH)){
			return "现金";
		}else if(this.equals(GOLD)){
			return "金币";
		}else if(this.equals(JIFEN)){
			return "积分";
		}else{
			throw new Exception("错误的商品出售类型！");
		}
	}
	
	public static ProductSaleType fromName(String name) throws Exception{
		if("现金".equals(name)){
			return CASH;
		}else if("金币".equals(name)){
			return GOLD;
		}else if("积分".equals(name)){
			return JIFEN;
		}else {
			throw new Exception("错误的商品出售类型："+name);
		}
	}
	
	public static ProductSaleType fromString(String s) throws Exception{
		if("CASH".equals(s)){
			return CASH;
		}else if("GOLD".equals(s)){
			return GOLD;
		}else if("JIFEN".equals(s)){
			return JIFEN;
		}else {
			throw new Exception("错误的商品出售类型："+s);
		}
	}
	
}
