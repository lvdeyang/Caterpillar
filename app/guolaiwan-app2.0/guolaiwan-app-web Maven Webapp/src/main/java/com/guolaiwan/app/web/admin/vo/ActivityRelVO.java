package com.guolaiwan.app.web.admin.vo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.guolaiwan.bussiness.admin.po.ActivityRelPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ActivityRelVO extends AbstractBaseVO<ActivityRelVO, ActivityRelPO> {
	
	
	private String beginDate;
	private String endDate;
	private String beginTime;
	private String endTime;
	private String bookBeginTime;
	private String bookEndTime;
	private long activityId;
	private long productId;
	private String productName;
	private int dayStock;
	private long productStock;//库存
	private String price;
	private int expireTime;
	
	public long getProductStock() {
		return productStock;
	}

	public ActivityRelVO setProductStock(long productStock) {
		this.productStock = productStock;
		return this;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public ActivityRelVO setBeginDate(String beginDate) {
		this.beginDate = beginDate;
		return this;
	}

	public String getEndDate() {
		return endDate;
	}

	public ActivityRelVO setEndDate(String endDate) {
		this.endDate = endDate;
		return this;
	}

	public long getActivityId() {
		return activityId;
	}

	public ActivityRelVO setActivityId(long activityId) {
		this.activityId = activityId;
		return this;
	}

	public long getProductId() {
		return productId;
	}

	public ActivityRelVO setProductId(long productId) {
		this.productId = productId;
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public ActivityRelVO setProductName(String productName) {
		this.productName = productName;
		return this;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public ActivityRelVO setBeginTime(String beginTime) {
		this.beginTime = beginTime;
		return this;
	}

	public String getEndTime() {
		return endTime;
	}

	public ActivityRelVO setEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}

	public String getBookBeginTime() {
		return bookBeginTime;
	}

	public ActivityRelVO setBookBeginTime(String bookBeginTime) {
		this.bookBeginTime = bookBeginTime;
		return this;
	}

	public String getBookEndTime() {
		return bookEndTime;
	}

	public ActivityRelVO setBookEndTime(String bookEndTime) {
		this.bookEndTime = bookEndTime;
		return this;
	}

	@Override
	public ActivityRelVO set(ActivityRelPO entity) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
		String beginDate = entity.getBeginDate()==null?"":formatter.format(entity.getBeginDate());
		String endDate = entity.getEndDate()==null?"":formatter.format(entity.getEndDate());
		String beginTime = entity.getBeginTime()==null?"":formatter1.format(entity.getBeginTime());
		String endTime = entity.getEndTime()==null?"":formatter1.format(entity.getEndTime());
		String bookBeginDate = entity.getBookBeginTime()==null?"":formatter.format(entity.getBookBeginTime());
		String bookEndDate = entity.getBookEndTime()==null?"":formatter.format(entity.getBookEndTime());
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(formatter.format(entity.getUpdateTime()))
		.setBeginDate(beginDate)
		.setEndDate(endDate)
		.setBeginTime(beginTime)
		.setEndTime(endTime)
		.setBookBeginTime(bookBeginDate)
		.setBookEndTime(bookEndDate)
		.setProductId(entity.getProductId())
		.setActivityId(entity.getActivityId())
		.setProductStock(entity.getProductStock())
		.setDayStock(entity.getDayStock())
		.setExpireTime(entity.getExpireTime())
		.setPrice(new DecimalFormat("0.00").format(((double)entity.getPrice()/100)))
		.setProductName(entity.getProductName());
		return this;

	}

	public int getDayStock() {
		return dayStock;
	}

	public ActivityRelVO setDayStock(int dayStock) {
		this.dayStock = dayStock;
		return this;
	}

	public String getPrice() {
		return price;
	}

	public ActivityRelVO setPrice(String price) {
		this.price = price;
		return this;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public ActivityRelVO setExpireTime(int expireTime) {
		this.expireTime = expireTime;
		return this;
	}
}
