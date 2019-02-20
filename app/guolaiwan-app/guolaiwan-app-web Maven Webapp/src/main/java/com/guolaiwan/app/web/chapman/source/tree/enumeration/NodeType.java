package com.guolaiwan.app.web.chapman.source.tree.enumeration;

import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.Static;

public enum NodeType {

	//根节点
	ROOT("root"),
	
	//文件夹
	FOLDER("folder"),
	
	//图片
	PICTURE("picture"),
	
	//音频
	AUDIO("audio"),
	
	//视频
	VIDEO("video");
	
	private String name;
	
	private NodeType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static NodeType fromString(String s) throws Exception{
		
		if("ROOT".equals(s)){
			return ROOT;
		}else if("FOLDER".equals(s)){
			return FOLDER;
		}else if("PICTURE".equals(s)){
			return PICTURE;
		}else if("AUDIO".equals(s)){
			return AUDIO;
		}else if("VIDEO".equals(s)){
			return VIDEO;
		}else{
			throw new Exception("错误的节点类型："+s);
		}
		
	}
	
}
