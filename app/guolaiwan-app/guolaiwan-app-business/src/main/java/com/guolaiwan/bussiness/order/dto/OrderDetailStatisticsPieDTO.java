package com.guolaiwan.bussiness.order.dto;

public class OrderDetailStatisticsPieDTO {

	//商品类型
	private String type;
	
	//总销售额
	private Long count;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	} 
	
}
