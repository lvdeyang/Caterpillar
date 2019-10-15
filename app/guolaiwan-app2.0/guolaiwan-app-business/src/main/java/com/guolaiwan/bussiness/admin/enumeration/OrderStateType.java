package com.guolaiwan.bussiness.admin.enumeration;

public enum OrderStateType {

	//商品类型
	NOTPAY("未付款"),

	//支付成功
	PAYSUCCESS("支付成功"),

	//已验单（虚拟）
	TESTED("已验单"),

	//发货（商品）
	DELIVER("已发货"),

	//已验单（商品）
	RECEIPT("已收货"),

	//已确认（餐桌订单使用）
	CONFIRMED("已确认"),

	//已评价
	COMMENTED("已评价"),

	//退款
	REFUNDING("申请退款"),

	//失败
	REFUNDFAIL("退款失败"),

	//数量过滤
	REFUNDED("退款成功");


	private String name;

	private OrderStateType(String name){
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
		}else if(this.equals(TESTED)){
			return "TESTED";
		}else if(this.equals(REFUNDING)){
			return "REFUNDING";
		}else if(this.equals(REFUNDFAIL)){
			return "REFUNDFAIL";
		}else if(this.equals(REFUNDED)){
			return "REFUNDED";
		}else if(this.equals(DELIVER)){
			return "DELIVER";
		}else if(this.equals(RECEIPT)){
			return "RECEIPT";
		}else if(this.equals(CONFIRMED)){
			return "CONFIRMED";
		}else if(this.equals(COMMENTED)){
			return "COMMENTED";
		}else{
			throw new Exception("错误的订单类型！");
		}
	}

	public static OrderStateType fromName(String name) throws Exception{
		if("未付款".equals(name)){
			return NOTPAY;
		}else if("支付成功".equals(name)){
			return PAYSUCCESS;
		}else if("已验单".equals(name)){
			return TESTED;
		}else if("申请退款".equals(name)){
			return REFUNDING;
		}else if("退款失败".equals(name)){
			return REFUNDFAIL;
		}else if("退款成功".equals(name)){
			return REFUNDED;
		}else if("已发货".equals(name)){
			return DELIVER;
		}else if("已收货".equals(name)){
			return RECEIPT;
		}else if("已确认".equals(name)){
			return CONFIRMED;
		}else if("已评价".equals(name)){
			return COMMENTED;
		}else {
			throw new Exception("错误的订单状态："+name);
		}
	}

	public static OrderStateType fromString(String s) throws Exception{
		if("NOTPAY".equals(s)){
			return NOTPAY;
		}else if("PAYSUCCESS".equals(s)){
			return PAYSUCCESS;
		}else if("TESTED".equals(s)){
			return TESTED;
		}else if("REFUNDING".equals(s)){
			return REFUNDING;
		}else if("REFUNDFAIL".equals(s)){
			return REFUNDFAIL;
		}else if("REFUNDED".equals(s)){
			return REFUNDED;
		}else if("DELIVER".equals(s)){
			return DELIVER;
		}else if("RECEIPT".equals(s)){
			return RECEIPT;
		}else if("CONFIRMED".equals(s)){
			return CONFIRMED;
		}else if("COMMENTED".equals(s)){
			return COMMENTED;
		}else {
			throw new Exception("错误的订单状态："+s);
		}
	}

}
