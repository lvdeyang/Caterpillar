package com.guolaiwan.bussiness.distribute.classify;

public enum DistributorType {

	PROVINCE("PROVINCE"),
	CITY("CITY"),
	COUNTY("COUNTY");
	
	private String name;
	
	private DistributorType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static DistributorType fromString(String _name){
		if(_name.equals("PROVINCE")){
			return PROVINCE;
		}else if(_name.equals("CITY")){
			return CITY;
		}else{
			return COUNTY;
		}
	}
	
}
