package com.guolaiwan.bussiness.admin.enumeration;

public enum CompanyType {

	//类型
	HEAD("总公司"),
	
	//标签过滤
	SON("分公司");
	
	private String name;
	
	private CompanyType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(HEAD)){
			return "总公司";
		}else if(this.equals(SON)){
			return "分公司";
		}else{
			throw new Exception("错误的公司类型");
		}
	}
	
	public static CompanyType fromName(String name) throws Exception{
		if("总公司".equals(name)){
			return HEAD;
		}else if("分公司".equals(name)){
			return SON;
		}else {
			throw new Exception("错误的公司类型："+name);
		}
	}
	
	public static CompanyType fromString(String s) throws Exception{
		if("HEAD".equals(s)){
			return HEAD;
		}else if("SON".equals(s)){
			return SON;
		}else {
			throw new Exception("错误的公司类型："+s);
		}
	}
	
}
