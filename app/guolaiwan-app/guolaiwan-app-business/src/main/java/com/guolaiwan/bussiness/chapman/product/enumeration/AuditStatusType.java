package com.guolaiwan.bussiness.chapman.product.enumeration;

public enum AuditStatusType {

	//审核通过
	PASSED("审核通过");
	
	private String name;
	
	private AuditStatusType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static AuditStatusType fromName(String name) throws Exception{
		if("审核通过".equals(name)){
			return PASSED;
		}else {
			throw new Exception("错误的审核状态："+name);
		}
	}

	public static AuditStatusType fromString(String s) throws Exception{
		if("PASSED".equals(s)){
			return PASSED;
		}else {
			throw new Exception("错误的审核状态："+s);
		}
	}
	
}
