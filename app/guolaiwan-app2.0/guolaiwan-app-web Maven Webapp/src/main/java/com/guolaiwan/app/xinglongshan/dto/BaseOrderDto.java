package com.guolaiwan.app.xinglongshan.dto;

import java.math.BigDecimal;
import java.util.Date;


public class BaseOrderDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8254613726391534575L;
	/**
	 * 订单编码
	 */
	private String orderCode;
	/**
	 * 订单总价
	 */
	private BigDecimal totalPrice;
	/**
	 * 订单价格
	 */
	private BigDecimal price;
	/**
	 * 份数
	 */
	private Integer quantity;
	/**
	 * 发生日期
	 */
	private Date occDate;
	/**
	 * 商品编码
	 */
	private String goodsCode;
	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 起始时间
	 */
	private Date startDate;
	/**
	 * 结束时间
	 */
	private Date endDate;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getOccDate() {
		return occDate;
	}

	public void setOccDate(Date occDate) {
		this.occDate = occDate;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
