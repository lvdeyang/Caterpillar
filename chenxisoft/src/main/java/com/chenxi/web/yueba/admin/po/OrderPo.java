package com.chenxi.web.yueba.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.chenxi.web.classes.ContentSource;
import com.chenxi.web.classes.Moudular;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "order")
public class OrderPo extends AbstractBasePO{
	private Date from;
	private Date to;
	private long workerId;
	private long userId;
	private double price;
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
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
	
	
}
