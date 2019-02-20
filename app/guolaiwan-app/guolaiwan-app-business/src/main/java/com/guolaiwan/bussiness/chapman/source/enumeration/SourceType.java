package com.guolaiwan.bussiness.chapman.source.enumeration;

/**
 * 资源类型
 * lvdeyang 2017年6月23日
 */
public enum SourceType {

	//图片资源
	PICTURE("图片"),
	
	//音频资源
	AUDIO("音频"),
	
	//视频资源
	VIDEO("视频");
	
	private String name;
	
	private SourceType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static SourceType fromString(String s) throws Exception{
		if("PICTURE".equals(s)){
			return PICTURE;
		}else if("AUDIO".equals(s)){
			return AUDIO;
		}else if("VIDEO".equals(s)){
			return VIDEO;
		}else{
			throw new Exception("错误的资源类型："+s);
		}
	}
	
}
