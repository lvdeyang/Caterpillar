package com.guolaiwan.app.web.smartParking.vo;



public class OrderVo {

	//订单id
	private int orderId;
	//停车 层
	private String parkingLayer;
	// 停车 区
	private String  parkingDistrict;
	//车位编号
	private String parkingNumber;
	//停车费用
	private int  parkingCost;
	//预订时间
	private String bookingTime;
	//到期时间
	private String dueTime;
	//订单状态  
	private String orderStatus;
	//是否超时
	private int overTime;
	//是否过期
	private int expire;
	
	
	
	
	
	
	/**
	 * 用户id  订单
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * 用户id 订单
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * 停车 层
	 */
	public String getParkingLayer() {
		return parkingLayer;
	}
	/**
	 * 停车 层
	 */
	public void setParkingLayer(String parkingLayer) {
		this.parkingLayer = parkingLayer;
	}

	/**
	 * 停车 区
	 */
	public String getParkingDistrict() {
		return parkingDistrict;
	}

	/**
	 * 停车 区
	 */
	public void setParkingDistrict(String parkingDistrict) {
		this.parkingDistrict = parkingDistrict;
	}


	/**
	 * 车位编号
	 */
	public String getParkingNumber() {
		return parkingNumber;
	}

	/**
	 * 车位编号
	 */
	public void setParkingNumber(String parkingNumber) {
		this.parkingNumber = parkingNumber;
	}

	/**
	 * 停车费用
	 */
	public int getParkingCost() {
		return parkingCost;
	}

	/**
	 * 停车费用
	 */
	public void setParkingCost(int parkingCost) {
		this.parkingCost = parkingCost;
	}

	/**
	 * 预订时间
	 */
	public String getBookingTime() {
		return bookingTime;
	}

	/**
	 * 预订时间
	 */
	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	/**
	 * 到期时间
	 */
	public String getDueTime() {
		return dueTime;
	}

	/**
	 * 到期时间
	 */
	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}

	/**
	 * 订单状态
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 订单状态
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 是否超过预订时间
	 */
	public int getOverTime() {
		return overTime;
	}

	/**
	 * 是否超过预订时间  0未超出  1已超出
	 */
	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}

	/**
	 * 订单是否已过期  0未过期 1已过期
	 */
	public int getExpire() {
		return expire;
	}

	/**
	 * 订单是否已过期  0未过期 1已过期
	 */
	public void setExpire(int expire) {
		this.expire = expire;
	}




}
