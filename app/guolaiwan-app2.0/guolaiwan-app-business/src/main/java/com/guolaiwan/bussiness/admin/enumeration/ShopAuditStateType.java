package com.guolaiwan.bussiness.admin.enumeration;

public enum ShopAuditStateType {

	// 待审核
	D("待审核"),
	// 审核通过
	T("审核通过"),
	// 未通过
	N("未通过"),
	// 草稿
	C("草稿");
	
	private String name;
	
	private ShopAuditStateType(String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static ShopAuditStateType fromName(String name) throws Exception{
		if("待审核".equals(name)){
			return D;
		}else if("审核通过".equals(name)){
			return T;
		}else if("未通过".equals(name)){
			return N;
		}else if("草稿".equals(name)){
			return C;
		}else {
			throw new Exception("错误的版块类型："+name);
		}
		
	}
	
	public static ShopAuditStateType fromString(String s) throws Exception{
		if("D".equals(s)){
			return D;
		}else if("T".equals(s)){
			return T;
		}else if("N".equals(s)){
			return N;
		}else if("C".equals(s)){
			return C;
		}else {
			throw new Exception("错误的版块类型："+s);
		}
	}
}
