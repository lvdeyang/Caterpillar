package com.chenxi.web.yueba.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.chenxi.web.classes.WorkerStatus;
import com.chenxi.web.classes.Moudular;
import com.chenxi.web.classes.OrderStatus;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "chenxiorder")
public class OrderPo extends AbstractBasePO{
	private Date fromDate;
	private Date toDate;
	private long workerId;
	private String workName;
	private long userId;
	private String userName;
	private String worderPhoto;
	private String userPhone;
	private double price;
	private String region;
	private int days;
	private OrderStatus orderStatus;
	private String fromDateStr;
	private double mleft;
    
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public long getWorkerId() {
		return workerId;
	}
	public void setWorkerId(long workerId) {
		this.workerId = workerId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	@Enumerated(EnumType.STRING)
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getWorderPhoto() {
		return worderPhoto;
	}
	public void setWorderPhoto(String worderPhoto) {
		this.worderPhoto = worderPhoto;
	}
	@Transient
	public String getFromDateStr() {
		return fromDateStr;
	}
	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public double getMleft() {
		return mleft;
	}
	public void setMleft(double mleft) {
		this.mleft = mleft;
	}
	
	
}
