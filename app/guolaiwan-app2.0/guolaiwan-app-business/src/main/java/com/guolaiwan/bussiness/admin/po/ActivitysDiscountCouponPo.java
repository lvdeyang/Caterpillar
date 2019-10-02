package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;

//优惠券
@Entity
@Table(name = "t_sys_activity_discountcoupon")
public class ActivitysDiscountCouponPo extends AbstractBasePO {
	// 活动表ID
	private long activityId;
	// 优惠券类型1-100 100-500 500-1000

	private int couponType;
	// 商品ID
	private long modularId;
	// 优惠券期限
	private Date startDate;
	private Date endDate;
	// 商家ID
	private long merchantId;
	// 优惠券通用类型 商家 物品 所有都通用
	private int type;

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public int getCouponType() {
		return couponType;
	}

	public void setCouponType(int couponType) {
		this.couponType = couponType;
	}

	public long getModularId() {
		return modularId;
	}

	public void setModularId(long modularId) {
		this.modularId = modularId;
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

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

}
