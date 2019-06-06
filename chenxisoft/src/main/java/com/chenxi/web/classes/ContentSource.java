package com.chenxi.web.classes;


public enum ContentSource {
	YICHE("易车"),
	XINLANG("新浪"),
	JINGDONG("京东");
	
	
    private String name;
	
	private ContentSource(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(YICHE)){
			return "易车";
		}else if(this.equals(XINLANG)){
			return "新浪";
		}else if(this.equals(JINGDONG)){
			return "京东";
		}else {
			throw new Exception("错误的");
		}
	}
	
	public static ContentSource fromName(String name) throws Exception{
		if("易车".equals(name)){
			return YICHE;
		}else if("新浪".equals(name)){
			return XINLANG;
		}else if("京东".equals(name)){
			return JINGDONG;
		} else {
			throw new Exception("错误的："+name);
		}
	}
	
	public static ContentSource fromString(String s) throws Exception{
		if("YICHE".equals(s)){
			return YICHE;
		}else if("XINLANG".equals(s)){
			return XINLANG;
		} else if("JINGDONG".equals(s)){
			return JINGDONG;
		} else {
			throw new Exception("错误的："+s);
		}
	}
}
