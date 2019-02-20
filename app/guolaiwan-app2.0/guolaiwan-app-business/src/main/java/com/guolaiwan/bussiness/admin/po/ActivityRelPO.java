package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_activity_relation")
public class ActivityRelPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	
	private Date beginDate;
	private Date endDate;
	private Date beginTime;
	private Date endTime;
	//关联活动多对一
	private long activityId;
	//商品Id
	private long productId;
	//商品名称（由于显示）
	private String productName;
	
	private long productStock;//库存
	
	private int dayStock;
	//一日一个
	private int onePerDay;//0,1是的
	//集赞购买
	private int surpportBuy;//0,1是的
	//集赞个数
	private int surpportCount;
	
	
	private long price;
	
	public long getProductStock() {
		return productStock;
	}
	public void setProductStock(long productStock) {
		this.productStock = productStock;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public long getActivityId() {
		return activityId;
	}
	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getOnePerDay() {
		return onePerDay;
	}
	public void setOnePerDay(int onePerDay) {
		this.onePerDay = onePerDay;
	}
	public int getSurpportBuy() {
		return surpportBuy;
	}
	public void setSurpportBuy(int surpportBuy) {
		this.surpportBuy = surpportBuy;
	}
	public int getSurpportCount() {
		return surpportCount;
	}
	public void setSurpportCount(int surpportCount) {
		this.surpportCount = surpportCount;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getDayStock() {
		return dayStock;
	}
	public void setDayStock(int dayStock) {
		this.dayStock = dayStock;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
}
