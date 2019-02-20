package com.guolaiwan.bussiness.order.dto;

import java.util.Date;

public class OrderDetailStatisticsTableDTO {

	//订单编号
	private String orderId;
	
	//下单时间
	private Date orderTime;
	
	//产品名称
	private String product;
	
	//产品类型
	private String productType;
	
	//订购数量
	private int num;
	
	//总价
	private int count;
	
	//订单状态
	private String status;
	
	//买家姓名
	private String buyer;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	
}
