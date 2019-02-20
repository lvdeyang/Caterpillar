package com.guolaiwan.bussiness.order.dto;

public class OrderDetailStatisticsBarDTO {

	//订单月份
	private String orderMonth;
	
	//商品类型
	private String productType;
	
	//销售额
	private Long count;

	public String getOrderMonth() {
		return orderMonth;
	}

	public void setOrderMonth(String orderMonth) {
		this.orderMonth = orderMonth;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
}
