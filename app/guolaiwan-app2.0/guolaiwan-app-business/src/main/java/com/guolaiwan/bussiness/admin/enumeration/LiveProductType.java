package com.guolaiwan.bussiness.admin.enumeration;

public enum LiveProductType {

	FIXEDPRICE("固定价格"),
	// Auction
	AUCTIONPRICE("拍卖价格");
	
	private String name;
	
	private LiveProductType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(FIXEDPRICE)){
			return "固定价格";
		}else if(this.equals(AUCTIONPRICE)){
			return "拍卖价格";
		}else{
			throw new Exception("错误的价格类型");
		}
	}
	
	public static LiveProductType fromName(String name) throws Exception{
		if("固定价格".equals(name)){
			return FIXEDPRICE;
		}else if("拍卖价格".equals(name)){
			return AUCTIONPRICE;
		}else {
			throw new Exception("错误的价格类型："+name);
		}
	}
	
	public static LiveProductType fromString(String s) throws Exception{
		if("FIXEDPRICE".equals(s)){
			return FIXEDPRICE;
		}else if("AUCTIONPRICE".equals(s)){
			return AUCTIONPRICE;
		}else {
			throw new Exception("错误的价格	类型："+s);
		}
	}
}
