package com.guolaiwan.bussiness.distribute.classify;

public enum DistributorOrderStatus {

	CHECKED("审核中"),
	PASSED("审核通过"),
	NOTPASSED("审核未通过"),
	PAYED("已支付");
	
	private String name;
	
	private DistributorOrderStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

	
}
