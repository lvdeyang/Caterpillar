package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_NhEtickets")
public class NhEticketsPo extends AbstractBasePO {
	private static final long serialVersionUID = 1L;
	// 订单Id
	private long orderId;

	// 电子票号
	private String sn;

	// 二维码
	private String url;

	// 消费状态
	private int isInUse;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIsInUse() {
		return isInUse;
	}

	public void setIsInUse(int isInUse) {
		this.isInUse = isInUse;
	}

}
