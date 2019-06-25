package com.chenxi.web.classes;


public enum OrderStatus {
	NOTPAY("NOTPAY"),
	PAYSUCCESS("PAYSUCCESS"),
	COMPLETE("COMPLETE"),
	REFUNDING("REFUNDING"),
	REFUNDED("REFUNDED");
	
	
    private String name;
	
	private OrderStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(NOTPAY)){
			return "NOTPAY";
		}else if(this.equals(PAYSUCCESS)){
			return "PAYSUCCESS";
		}else if(this.equals(COMPLETE)){
			return "COMPLETE";
		}else if(this.equals(REFUNDING)){
			return "REFUNDING";
		}else {
			throw new Exception("错误的");
		}
	}
	
	public static OrderStatus fromName(String name) throws Exception{
		if("NOTPAY".equals(name)){
			return NOTPAY;
		}else if("PAYSUCCESS".equals(name)){
			return PAYSUCCESS;
		}else if("COMPLETE".equals(name)){
			return COMPLETE;
		} else if("REFUNDING".equals(name)){
			return REFUNDING;
		} else {
			throw new Exception("错误的："+name);
		}
	}
	
	public static OrderStatus fromString(String s) throws Exception{
		if("NOTPAY".equals(s)){
			return NOTPAY;
		}else if("PAYSUCCESS".equals(s)){
			return PAYSUCCESS;
		} else if("COMPLETE".equals(s)){
			return COMPLETE;
		} else if("REFUNDING".equals(s)){
			return REFUNDING;
		} else {
			throw new Exception("错误的："+s);
		}
	}
}
