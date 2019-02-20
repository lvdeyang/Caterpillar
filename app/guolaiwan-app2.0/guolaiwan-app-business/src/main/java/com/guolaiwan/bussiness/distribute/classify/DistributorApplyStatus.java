package com.guolaiwan.bussiness.distribute.classify;

public enum DistributorApplyStatus {

	CHECKED("CHECKED"),
	PASSED("PASSED"),
	NOTPASSED("NOTPASSED");
	
	private String name;
	
	private DistributorApplyStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

	
}
