package com.guolaiwan.bussiness.common.enumeration;

public enum OperatorType {

	//等于
	EQ("="),
	
	//不等于
	NE("!="),
	
	//小于
	LT("<"),
	
	//大于
	GT(">"),
	
	//小于等于
	LE("<="),
	
	//大于等于
	GE(">="),
	
	//枚举范围
	IN("in"),
	
	//枚举范围反选
	NIN("not in"),
	
	//模糊匹配
	LIKE("like"),
	
	//序列范围
	BETWEEN("between");
	
	private String name;
	
	private OperatorType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static OperatorType fromName(String name) throws Exception{
		if(name==null || "".equals(name)) return null;
		if("=".equals(name)){
			return EQ;
		}else if("!=".equals(name)){
			return NE;
		}else if("<".equals(name)){
			return LT;
		}else if(">".equals(name)){
			return GT;
		}else if("<=".equals(name)){
			return LE;
		}else if(">=".equals(name)){
			return GE;
		}else if("in".equals(name)){
			return IN;
		}else if("not in".equals(name)){
			return NIN;
		}else if("like".equals(name)){
			return LIKE;
		}else if("between".equals(name)){
			return BETWEEN;
		}else{
			throw new Exception("错误的运算符："+name);
		}
	}

	public static OperatorType fromString(String s) throws Exception{
		if(s==null || "".equals(s)) return null;
		if("EQ".equals(s)){
			return EQ;
		}else if("NE".equals(s)){
			return NE;
		}else if("LT".equals(s)){
			return LT;
		}else if("GT".equals(s)){
			return GT;
		}else if("LE".equals(s)){
			return LE;
		}else if("GE".equals(s)){
			return GE;
		}else if("IN".equals(s)){
			return IN;
		}else if("NIN".equals(s)){
			return NIN;
		}else if("LIKE".equals(s)){
			return LIKE;
		}else if("BETWEEN".equals(s)){
			return BETWEEN;
		}else{
			throw new Exception("错误的运算符："+s);
		}
	}
	
}
