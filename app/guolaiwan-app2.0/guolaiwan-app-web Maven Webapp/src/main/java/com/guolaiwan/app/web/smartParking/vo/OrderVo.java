package com.guolaiwan.app.web.smartParking.vo;

import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class OrderVo  extends AbstractBaseVO<OrderVo, OrderPO>{

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
	private Double  parkingCost;
	//停车总时间
	private Double  stoppingTime;
	//预订时间
	private String bookingTime;
	//到期时间
	private String dueTime;
	//订单状态         // 未支付  已支付   已过期  申请退款 正在退款 退款完成
	private String orderStatus;
	//退款理由         
	private String refund;
	//车牌号
	private String platenumber;
	//超时金额
	private double overTimeMoney;
	//超时时间
    private double overTime;
	//二维码路径
	private String path;
	//订单编号 
    private String orderNo;
	
	
	

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public double getOverTime() {
		return overTime;
	}

	public OrderVo setOverTime(double overTime) {
		this.overTime = overTime;
		return this;
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
	 * @return
	 */
	public OrderVo setOverTimeMoney(double overTimeMoney) {
		this.overTimeMoney = overTimeMoney;
		return this;
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
	 * @return
	 */
	public OrderVo setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
		return this;
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
	 * @return
	 */
	public OrderVo setRefund(String refund) {
		this.refund = refund;
		return this;
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
	 * @return
	 */
	public OrderVo setStoppingTime(Double stoppingTime) {
		this.stoppingTime = stoppingTime;
		return this;
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
	 * @return
	 */
	public OrderVo setAttractionsId(long attractionsId) {
		this.attractionsId = attractionsId;
		return this;
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
	 * @return
	 */
	public OrderVo setParkingName(String parkingName) {
		this.parkingName = parkingName;
		return this;
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
	 * @return
	 */
	public OrderVo setTime(String time) {
		Time = time;
		return this;
	}
	public long getOrderId() {
		return orderId;
	}
	public OrderVo setOrderId(long orderId) {
		this.orderId = orderId;
		return this;
	}
	/**
	 * 停车 层
	 */
	public String getParkingLayer() {
		return parkingLayer;
	}
	/**
	 * 停车 层
	 * @return 
	 */
	public OrderVo setParkingLayer(String parkingLayer) {
		this.parkingLayer = parkingLayer;
		return this;
	}

	/**
	 * 停车 区
	 */
	public String getParkingDistrict() {
		return parkingDistrict;
	}

	/**
	 * 停车 区
	 * @return 
	 */
	public OrderVo setParkingDistrict(String parkingDistrict) {
		this.parkingDistrict = parkingDistrict;
		return this;
	}


	/**
	 * 车位编号
	 */
	public int getParkingNumber() {
		return parkingNumber;
	}

	/**
	 * 车位编号
	 * @return 
	 */
	public OrderVo setParkingNumber(int parkingNumber) {
		this.parkingNumber = parkingNumber;
		return this;
	}

	/**
	 * 停车费用
	 */
	public Double getParkingCost() {
		return parkingCost;
	}
	/**
	 * 停车费用
	 * @return 
	 */
	public OrderVo setParkingCost(Double parkingCost) {
		this.parkingCost = parkingCost;
		return this;
	}



	public OrderVo setParkingCost(double parkingCost) {
		this.parkingCost = parkingCost;
		return this;
	}

	/**
	 * 预订时间
	 */
	public String getBookingTime() {
		return bookingTime;
	}

	/**
	 * 预订时间
	 * @return 
	 */
	public OrderVo setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
		return this;
	}

	/**
	 * 到期时间
	 */
	public String getDueTime() {
		return dueTime;
	}

	/**
	 * 到期时间
	 * @return 
	 */
	public OrderVo setDueTime(String dueTime) {
		this.dueTime = dueTime;
		return this;
}

	/**
	 * 订单状态
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 订单状态
	 * @return 
	 */
	public OrderVo setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
		return this;
	}

    /**
     * 二维码路径
     * @return
     */
	public String getPath() {
		return path;
	}

	 /**
	  * 二维码路径
	  * @return
	  */
	public void setPath(String path) {
		this.path = path;
	}

	
	@Override
	public OrderVo set(OrderPO entity) throws Exception {
	this.setOverTime(entity.getOverTime())
	.setId(entity.getId())
	.setAttractionsId(entity.getAttractionsId())
	.setOrderId(entity.getOrderId())
	.setParkingLayer(entity.getParkingLayer())
	.setParkingName(entity.getParkingName())
	.setTime(entity.getTime())
	.setParkingDistrict(entity.getParkingDistrict())
	.setParkingNumber(entity.getParkingNumber())
	.setParkingCost(entity.getParkingCost())
	.setStoppingTime(entity.getStoppingTime())
	.setBookingTime(entity.getBookingTime())
	.setDueTime(entity.getDueTime())
	.setOrderStatus(entity.getOrderStatus())
	.setRefund(entity.getRefund())
	.setPlatenumber(entity.getPlatenumber())
	.setOverTimeMoney(entity.getOverTimeMoney())
	.setPath(entity.getPath())
	;
	return this;
	}


}
