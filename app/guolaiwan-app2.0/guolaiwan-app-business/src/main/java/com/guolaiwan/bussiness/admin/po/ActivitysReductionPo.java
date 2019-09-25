package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_activitys_reduction")
// 满减
public class ActivitysReductionPo extends AbstractBasePO {
	// 活动表ID
	private long activityId;
	// 商品ID
	private long productId;
	// 满
	private long ceil;
	// 减
	private long cut;
	// 是否该商户下所有商品都满减
	private int reductionAll;

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

	public long getCeil() {
		return ceil;
	}

	public void setCeil(long ceil) {
		this.ceil = ceil;
	}

	public long getCut() {
		return cut;
	}

	public void setCut(long cut) {
		this.cut = cut;
	}

	public int getReductionAll() {
		return reductionAll;
	}

	public void setReductionAll(int reductionAll) {
		this.reductionAll = reductionAll;
	}
}
