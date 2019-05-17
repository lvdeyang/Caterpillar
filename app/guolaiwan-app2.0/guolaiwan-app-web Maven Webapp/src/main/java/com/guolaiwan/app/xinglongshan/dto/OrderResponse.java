package com.guolaiwan.app.xinglongshan.dto;

import java.util.List;

/**
 * 订单响应
 * @author crazy_cabbage
 *
 */
public class OrderResponse extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5555654903297331070L;
	/**
	 * 返回订单
	 */
	private List<Order> orders;
	/**
	 * 订单
	 */
	private Order order;


	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


}
