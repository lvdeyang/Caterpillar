package com.guolaiwan.bussiness.admin.enumeration;

public enum BookType {

	LUNCH("午餐"),
	DINNER("晚餐");
	
	
private String name;
	
	private BookType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(LUNCH)){
			return "午餐";
		}else if(this.equals(DINNER)){
			return "晚餐";
		}else {
			throw new Exception("错误的餐状态");
		}
	}
	
	public static BookType fromName(String name) throws Exception{
		if("午餐".equals(name)){
			return LUNCH;
		}else if("晚餐".equals(name)){
			return DINNER;
		} else {
			throw new Exception("错误的餐状态："+name);
		}
	}
	
	public static BookType fromString(String s) throws Exception{
		if("LUNCH".equals(s)){
			return LUNCH;
		}else if("DINNER".equals(s)){
			return DINNER;
		} else {
			throw new Exception("错误的餐状态："+s);
		}
	}
}
