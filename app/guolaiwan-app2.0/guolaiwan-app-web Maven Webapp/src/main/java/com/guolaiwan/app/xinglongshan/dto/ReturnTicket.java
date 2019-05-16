package com.guolaiwan.app.xinglongshan.dto;

import java.math.BigDecimal;


public class ReturnTicket extends BaseDto {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1424394304987431231L;
	/**
	 * 子订单编码
	 */
	private String orderCode;
	/**
	 * 子订单类型
	 */
	private String orderType;
	/**
	 *退票数量
	 */
	private Integer returnNum;
	/**
	 * 订单明细主键
	 */
	private Long orderDetailId;
	/**
	 * 结算单价
	 */
	private BigDecimal price;
	/**
	 * 退款类别
	 */
	private String returnFeeCategory;
	/**
	 * 退款费率
	 */
	private String returnFeeRate;
	/**
	 * 可退票数量
	 */
	private Integer canReturnNum;
	/**
	 * 第三方退单号 
	 * **/
	private String thirdReturnCode;
	
	private Long showOrderSeatId;
	private Long sessionTicketId;
	
	
	/**
	 * 退票实名制
	 * @return
	 */
	private String idCards;
	public String getIdCards() {
		return idCards;
	}
	public void setIdCards(String idCards) {
		this.idCards = idCards;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Integer getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}
	public Long getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getReturnFeeCategory() {
		return returnFeeCategory;
	}
	public void setReturnFeeCategory(String returnFeeCategory) {
		this.returnFeeCategory = returnFeeCategory;
	}
	public String getReturnFeeRate() {
		return returnFeeRate;
	}
	public void setReturnFeeRate(String returnFeeRate) {
		this.returnFeeRate = returnFeeRate;
	}
	public Integer getCanReturnNum() {
		return canReturnNum;
	}
	public void setCanReturnNum(Integer canReturnNum) {
		this.canReturnNum = canReturnNum;
	}
	public String getThirdReturnCode() {
		return thirdReturnCode;
	}
	public void setThirdReturnCode(String thirdReturnCode) {
		this.thirdReturnCode = thirdReturnCode;
	}
	public Long getShowOrderSeatId() {
		return showOrderSeatId;
	}
	public void setShowOrderSeatId(Long showOrderSeatId) {
		this.showOrderSeatId = showOrderSeatId;
	}
	public Long getSessionTicketId() {
		return sessionTicketId;
	}
	public void setSessionTicketId(Long sessionTicketId) {
		this.sessionTicketId = sessionTicketId;
	}
	
}
