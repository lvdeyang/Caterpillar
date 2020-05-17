package com.guolaiwan.bussiness.gonghui.enumeration;

public enum RecomType {

	COLUMN("栏目"),

	HOME("首页"),
	
	ARTICLE("文章");

	private String name;

	private RecomType(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public String getFiled() throws Exception{
		if(this.equals(COLUMN)){
			return "COLUMN";
		}else if(this.equals(HOME)){
			return "HOME";
		}else if(this.equals(ARTICLE)){
			return "ARTICLE";
		}else{
			throw new Exception("错误的活动类型！");
		}
	}

	public static RecomType fromName(String name) throws Exception{
		if("栏目".equals(name)){
			return COLUMN;
		}else if("首页".equals(name)){
			return HOME;
		}else if("文章".equals(name)){
			return ARTICLE;
		}else {
			throw new Exception("错误的活动类型："+name);
		}
	}

	public static RecomType fromString(String s) throws Exception{
		if("COLUMN".equals(s)){
			return COLUMN;
		}else if("HOME".equals(s)){
			return HOME;
		}else if("ARTICLE".equals(s)){
			return ARTICLE;
		}else {
			throw new Exception("错误的活动类型："+s);
		}
	}

}
