package com.guolaiwan.bussiness.Parking.po;
//订单表
import javax.persistence.Entity;

import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
/**
 * 
 * @author 订单表
 *
 */
@Entity
@Table(name = "t_order_table")
public class OrderPO extends AbstractBasePO {
	//用户 订单id
	private long orderId;
	//景区id
	private long attractionsId;
	//停车场层
	private String parkingLayer;
	//停车场 名称
	private String parkingName;
	//停车场营业时间
	private String Time;
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
	//订单状态         // 未支付  已支付   已过期
	private String orderStatus;
	//是否超时
	private int overTime;

	
	
	
	
	/**
	 * 景区id
	 * @return
	 */
	public long getAttractionsId() {
		return attractionsId;
	}
	/**
	 * 景区id
	 * @return
	 */
	public void setAttractionsId(long attractionsId) {
		this.attractionsId = attractionsId;
	}
	/**
	 * 车场名称
	 * @return
	 */
	public String getParkingName() {
		return parkingName;
	}
	/**
	 * 车场名称
	 * @return
	 */
	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
	/**
	 * 停车场 营业时间
	 * @return
	 */
	public String getTime() {
		return Time;
	}
	/**
	 * 停车场 营业时间
	 * @return
	 */
	public void setTime(String time) {
		Time = time;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
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



}
