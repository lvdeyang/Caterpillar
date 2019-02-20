package com.guolaiwan.bussiness.admin.enumeration;

public enum PayType {

	//默认渲染
	ALIPAY("支付宝"),

	//点击渲染
	WEICHAT("微信"),

	//
	GOLDPAY("金币支付");

	private String name;

	private PayType(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public static PayType fromName(String name) throws Exception{
		if("支付宝".equals(name)){
			return ALIPAY;
		}else if("微信".equals(name)){
			return WEICHAT;
		}else if("金币支付".equals(name)){
			return GOLDPAY;
		}else {
			throw new Exception("错误的支付方式："+name);
		}
	}

	public static PayType fromString(String s) throws Exception{
		if("ALIPAY".equals(s)){
			return ALIPAY;
		}else if("WEICHAT".equals(s)){
			return WEICHAT;
		}else if("GOLDPAY".equals(s)){
			return GOLDPAY;
		}else {
			throw new Exception("错误的支付方式："+s);
		}
	}

}
