package com.guolaiwan.bussiness.admin.enumeration;

public enum TableStatus {

	FREE("空闲"),
	BOOKED("已预订"),
	INUSE("使用中"),
	PAID("已支付");
	
	private String name;
	
	private TableStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(FREE)){
			return "空闲";
		}else if(this.equals(BOOKED)){
			return "已预订";
		}else if(this.equals(INUSE)){
			return "使用中";
		}else if(this.equals(PAID)){
			return "已支付";
		}else {
			throw new Exception("错误的餐桌状态");
		}
	}
	
	public static TableStatus fromName(String name) throws Exception{
		if("空闲".equals(name)){
			return FREE;
		}else if("已预订".equals(name)){
			return BOOKED;
		} else if ("使用中".equals(name)){
			return INUSE;
		}else if ("已支付".equals(name)){
			return PAID;
		} else {
			throw new Exception("错误的餐桌状态："+name);
		}
	}
	
	public static TableStatus fromString(String s) throws Exception{
		if("FREE".equals(s)){
			return FREE;
		}else if("BOOKED".equals(s)){
			return BOOKED;
		}else if("INUSE".equals(s)){
			return INUSE;
		}else if("PAID".equals(s)){
			return PAID;
		}else {
			throw new Exception("错误的餐桌状态："+s);
		}
	}
}
