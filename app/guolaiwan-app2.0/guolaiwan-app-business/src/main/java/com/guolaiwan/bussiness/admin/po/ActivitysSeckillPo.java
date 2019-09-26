package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;
//秒杀
@Entity
@Table(name = "t_sys_activitys_seckill")
public class ActivitysSeckillPo extends AbstractBasePO {
	// 活动表ID
	private long activityId;
	// 商品ID
	private long productId;
	// 秒杀后的价格
	private long seckillPrice;
	// 秒杀开始时间
	private Date seckillStartDate;
	// 秒杀结束时间
	private Date seckillEndDate;
	// 限量数量
	private int seckillCount;
	// 已秒杀数量
	private int hasSellCount;
	// 是否该商户下所有商品都可以秒杀
	private int secKillAll;

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

	public long getSeckillPrice() {
		return seckillPrice;
	}

	public void setSeckillPrice(long seckillPrice) {
		this.seckillPrice = seckillPrice;
	}

	public Date getSeckillStartDate() {
		return seckillStartDate;
	}

	public void setSeckillStartDate(Date seckillStartDate) {
		this.seckillStartDate = seckillStartDate;
	}

	public Date getSeckillEndDate() {
		return seckillEndDate;
	}

	public void setSeckillEndDate(Date seckillEndDate) {
		this.seckillEndDate = seckillEndDate;
	}

	public int getSeckillCount() {
		return seckillCount;
	}

	public void setSeckillCount(int seckillCount) {
		this.seckillCount = seckillCount;
	}

	public int getHasSellCount() {
		return hasSellCount;
	}

	public void setHasSellCount(int hasSellCount) {
		this.hasSellCount = hasSellCount;
	}

	public int getSecKillAll() {
		return secKillAll;
	}

	public void setSecKillAll(int secKillAll) {
		this.secKillAll = secKillAll;
	}

}
