package com.guolaiwan.bussiness.admin.enumeration;

public enum OrderType {

	//类型
	MERCHANT("商家订单"),
	
	MERCHANTGROUP("商家团购"),
	
	//标签过滤
	DISTRIBUTOR("经销商订单"),
	
	//跑腿订单
	LOGISTICS("跑腿订单");
	
	private String name;
	
	private OrderType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(MERCHANT)){
			return "MERCHANT";
		}else if(this.equals(MERCHANTGROUP)){
			return "MERCHANTGROUP";
		}else if(this.equals(DISTRIBUTOR)){
			return "DISTRIBUTOR";
		}else if(this.equals(LOGISTICS)){
			return "LOGISTICS";
		}else{
			throw new Exception("错误的订单类型！");
		}
	}
	
	public static OrderType fromName(String name) throws Exception{
		if("商家订单".equals(name)){
			return MERCHANT;
		}else if("商家团购".equals(name)){
			return MERCHANTGROUP;
		}else if("经销商订单".equals(name)){
			return DISTRIBUTOR;
		}else if("跑腿订单".equals(name)){
			return LOGISTICS;
		}else {
			throw new Exception("错误的订单类型："+name);
		}
	}
	
	public static OrderType fromString(String s) throws Exception{
		if("MERCHANT".equals(s)){
			return MERCHANT;
		}else if("MERCHANTGROUP".equals(s)){
			return MERCHANTGROUP;
		}else if("DISTRIBUTOR".equals(s)){
			return DISTRIBUTOR;
		}else if("LOGISTICS".equals(s)){
			return LOGISTICS;
		}else {
			throw new Exception("错误的订单类型："+s);
		}
	}
	
}
