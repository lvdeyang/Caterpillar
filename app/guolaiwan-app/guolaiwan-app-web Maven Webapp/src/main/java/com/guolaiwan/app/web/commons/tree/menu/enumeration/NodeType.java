package com.guolaiwan.app.web.commons.tree.menu.enumeration;

public enum NodeType {

	//根节点
	ROOT("root"),
	
	//菜单
	MENU("menu");
	
	private String name;
	
	private NodeType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static NodeType fromName(String name) throws Exception{
		
		if("root".equals(name)){
			return ROOT;
		}else if("menu".equals(name)){
			return MENU;
		}else{
			throw new Exception("错误的节点类型："+name);
		}
		
	}
	
	public static NodeType fromString(String s) throws Exception{
		
		if("ROOT".equals(s)){
			return ROOT;
		}else if("MENU".equals(s)){
			return MENU;
		}else{
			throw new Exception("错误的节点类型："+s);
		}
		
	}
	
}
