package com.guolaiwan.bussiness.admin.enumeration;

public enum VideoPicType {

	//小视频
	LITTLEVEDIO("小视频"),

	//图文
	PICTURE("图文");

	

	private String name;

	private VideoPicType(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public String getFiled() throws Exception{
		if(this.equals(LITTLEVEDIO)){
			return "LITTLEVEDIO";
		}else if(this.equals(PICTURE)){
			return "PICTURE";
		}else{
			throw new Exception("错误的图文小视频类型！");
		}
	}

	public static VideoPicType fromName(String name) throws Exception{
		if("小视频".equals(name)){
			return LITTLEVEDIO;
		}else if("图文".equals(name)){
			return PICTURE;
		}else {
			throw new Exception("错误的图文小视频类型："+name);
		}
	}

	public static VideoPicType fromString(String s) throws Exception{
		if("LITTLEVEDIO".equals(s)){
			return LITTLEVEDIO;
		}else if("PICTURE".equals(s)){
			return PICTURE;
		}else {
			throw new Exception("错误的图文小视频类型："+s);
		}
	}

}
