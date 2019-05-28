package com.guolaiwan.app.xinglongshan.dto;

import java.util.List;


/**
 * 订单请求对象
 * @author crazy_cabbage
 *
 */
public class OrderRequest extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2093789760747145234L;
	/**
	 * 订单明细主键
	 */
	private String orderDetailId;
	/**
	 * 退票对象
	 */
	private ReturnTicket returnTicket;
	/**
	 * 批量退票对象
	 */
	private List<ReturnTicket> returnTickets;
    /** 
     * 订单对象
     */
	private Order order;
	/**
	 *到付确认对象 
	 */
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public ReturnTicket getReturnTicket() {
		return returnTicket;
	}
	public void setReturnTicket(ReturnTicket returnTicket) {
		this.returnTicket = returnTicket;
	}
	public List<ReturnTicket> getReturnTickets() {
		return returnTickets;
	}
	public void setReturnTickets(List<ReturnTicket> returnTickets) {
		this.returnTickets = returnTickets;
	}
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	
}
