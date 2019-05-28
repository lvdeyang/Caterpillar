package com.chenxi.web.classes;


public enum ContentSource {
	YICHE("易车"),
	XINLANG("新浪");
	
	
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
		}else {
			throw new Exception("错误的");
		}
	}
	
	public static ContentSource fromName(String name) throws Exception{
		if("易车".equals(name)){
			return YICHE;
		}else if("新浪".equals(name)){
			return XINLANG;
		} else {
			throw new Exception("错误的："+name);
		}
	}
	
	public static ContentSource fromString(String s) throws Exception{
		if("YICHE".equals(s)){
			return YICHE;
		}else if("XINLANG".equals(s)){
			return XINLANG;
		} else {
			throw new Exception("错误的："+s);
		}
	}
}
