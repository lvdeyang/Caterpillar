package com.guolaiwan.bussiness.Parking.po;
//订单表
import javax.persistence.Entity;

import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

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
	//订单 二维码编号
	private String Time;
	// 停车 区
	private String  parkingDistrict;
	//车位编号
	private int parkingNumber;
	//停车费用
	private double  parkingCost;
	//停车总时间
	private Double  stoppingTime;
	//预订时间
	private String bookingTime;
	//到期时间
	private String dueTime;
	//订单状态         //  NOTPAY 未支付        PAYSUCCESS 已支付           PARKING 正在停车         PAST已过期     REFUNDING 申请退款             REFUNDED 退款完成
	private String orderStatus;
	//退款理由         
	private String refund;
	//车牌号
	private String platenumber;
	//超时金额
	private double overTimeMoney;
	//超时时间
	private double overTime;
	//微信  预订 
	private String orderNo;

	// 二维码路径
	private String path;

	
	
	
	
	

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public double getOverTime() {
		return overTime;
	}

	public void setOverTime(double overTime) {
		this.overTime = overTime;
	}

	/**
	 * 超时金额
	 * @return
	 */
	public double getOverTimeMoney() {
		return overTimeMoney;
	}

	/**
	 * 超时金额
	 * @return
	 */
	public void setOverTimeMoney(double overTimeMoney) {
		this.overTimeMoney = overTimeMoney;
	}

	/**
	 * 车牌号
	 * @return
	 */
	public String getPlatenumber() {
		return platenumber;
	}

	/**
	 * 车牌号
	 * @return
	 */
	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}


	/**
	 * 退款理由
	 * @return
	 */
	public String getRefund() {
		return refund;
	}


	/**
	 * 退款理由
	 * @return
	 */
	public void setRefund(String refund) {
		this.refund = refund;
	}






	/**
	 * 停车时间
	 * @return
	 */
	public Double getStoppingTime() {
		return stoppingTime;
	}	



	/**
	 * 停车时间
	 * @return
	 */
	public void setStoppingTime(Double stoppingTime) {
		this.stoppingTime = stoppingTime;
	}

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
	public int getParkingNumber() {
		return parkingNumber;
	}

	/**
	 * 车位编号
	 */
	public void setParkingNumber(int parkingNumber) {
		this.parkingNumber = parkingNumber;
	}

	/**
	 * 停车费用
	 */
	public Double getParkingCost() {
		return parkingCost;
	}
	/**
	 * 停车费用
	 */
	public void setParkingCost(Double parkingCost) {
		this.parkingCost = parkingCost;
	}



	public void setParkingCost(double parkingCost) {
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
	 * 二维码路径
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 二维码路径
	 */
	public void setPath(String path) {
		this.path = path;
	}




}
