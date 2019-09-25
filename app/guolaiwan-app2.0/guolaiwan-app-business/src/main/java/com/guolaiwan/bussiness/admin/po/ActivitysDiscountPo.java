package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;

//折扣
@Entity
@Table(name = "t_sys_activitys_discount")
public class ActivitysDiscountPo extends AbstractBasePO {
	// 活动表ID
	private long activityId;
	// 折
	private Double discount;
	// 折后单价
	private long discountPrice;
	// 商品ID
	private long modularId;
	// 是否把商户下所有商品加入折扣
	private int discountAll;

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public long getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(long discountPrice) {
		this.discountPrice = discountPrice;
	}

	public long getModularId() {
		return modularId;
	}

	public void setModularId(long modularId) {
		this.modularId = modularId;
	}

	public int getDiscountAll() {
		return discountAll;
	}

	public void setDiscountAll(int discountAll) {
		this.discountAll = discountAll;
	}

}
