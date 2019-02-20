package com.guolaiwan.app.web.publicnum.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bytedeco.javacpp.RealSense.intrinsics;

import com.guolaiwan.app.web.admin.vo.OrderInfoVO;


public class BundleOrderVo {
    private long orderId;
    private String payDate;
    private String userName;
    private int isBundle;//0表示独立订单，1表示合并订单
    private String orderStatus;
    private List<OrderInfoVO> orderList=new ArrayList<OrderInfoVO>();
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getIsBundle() {
		return isBundle;
	}
	public void setIsBundle(int isBundle) {
		this.isBundle = isBundle;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<OrderInfoVO> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderInfoVO> orderList) {
		this.orderList = orderList;
	}
    
}
