package com.guolaiwan.bussiness.admin.enumeration;

public enum LiveStatusType {

	// LIVING
	LIVING("直播中"),
	// STOP
	STOP("停播"),
	// FORBID
	FORBID("封号");

	private String name;

	private LiveStatusType(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(LIVING)){
			return "LIVING";
		}else if(this.equals(STOP)){
			return "STOP";
		}else if(this.equals(FORBID)){
			return "FORBID";
		}else{
			throw new Exception("错误的直播状态！");
		}
	}

	public static LiveStatusType fromName(String name) throws Exception{
		if("直播中".equals(name)){
			return LIVING;
		}else if("停播".equals(name)){
			return STOP;
		}else if("封号".equals(name)) {
			return FORBID;
		}else {
			throw new Exception("错误的直播状态："+name);
		}
	}

	public static LiveStatusType fromString(String s) throws Exception{
		if("LIVING".equals(s)){
			return LIVING;
		}else if("STOP".equals(s)){
			return STOP;
		}else if("FORBID".equals(s)){
			return FORBID;
		}else{
			throw new Exception("错误的直播状态："+s);
		}
	}
}
