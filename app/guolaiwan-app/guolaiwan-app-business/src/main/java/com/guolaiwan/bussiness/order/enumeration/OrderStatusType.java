package com.guolaiwan.bussiness.order.enumeration;

public enum OrderStatusType {

	//已支付
	PAID("已支付"),
	
	//待支付
	PENDING("待支付"),
	
	//过期
	OUTTIME("过期"),
	
	//失效
	INVALID("失效");
	
	private String name;
	
	private OrderStatusType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static OrderStatusType fromName(String name) throws Exception{
		if("已支付".equals(name)){
			return PAID;
		}else if("待支付".equals(name)){
			return PENDING;
		}else if("过期".equals(name)){
			return OUTTIME;
		}else if("失效".equals(name)){
			return INVALID;
		}else {
			throw new Exception("错误的订单状态："+name);
		}
	}
	
	public static OrderStatusType fromString(String s) throws Exception{
		if("PAID".equals(s)){
			return PAID;
		}else if("PENDING".equals(s)){
			return PENDING;
		}else if("OUTTIME".equals(s)){
			return OUTTIME;
		}else if("INVALID".equals(s)){
			return INVALID;
		}else{
			throw new Exception("错误的订单状态："+s);
		}
		
	}
	
}
