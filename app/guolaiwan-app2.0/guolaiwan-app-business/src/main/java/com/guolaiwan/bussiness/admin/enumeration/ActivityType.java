package com.guolaiwan.bussiness.admin.enumeration;

public enum ActivityType {

	//类型
	MANJIAN("满减"),

	//打折
	DAZHE("打折"),

	//积分
	JIFEN("积分"),

	//积分
	FIXEDPRICE("固定价格");

	private String name;

	private ActivityType(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public String getFiled() throws Exception{
		if(this.equals(MANJIAN)){
			return "MANJIAN";
		}else if(this.equals(DAZHE)){
			return "DAZHE";
		}else if(this.equals(JIFEN)){
			return "JIFEN";
		}else if(this.equals(FIXEDPRICE)){
			return "FIXEDPRICE";
		}else{
			throw new Exception("错误的活动类型！");
		}
	}

	public static ActivityType fromName(String name) throws Exception{
		if("满减".equals(name)){
			return MANJIAN;
		}else if("打折".equals(name)){
			return DAZHE;
		}else if("积分".equals(name)){
			return JIFEN;
		}else if("固定价格".equals(name)){
			return FIXEDPRICE;
		}else {
			throw new Exception("错误的活动类型："+name);
		}
	}

	public static ActivityType fromString(String s) throws Exception{
		if("MANJIAN".equals(s)){
			return MANJIAN;
		}else if("DAZHE".equals(s)){
			return DAZHE;
		}else if("JIFEN".equals(s)){
			return JIFEN;
		}else if("FIXEDPRICE".equals(s)){
			return FIXEDPRICE;
		}else {
			throw new Exception("错误的活动类型："+s);
		}
	}

}
