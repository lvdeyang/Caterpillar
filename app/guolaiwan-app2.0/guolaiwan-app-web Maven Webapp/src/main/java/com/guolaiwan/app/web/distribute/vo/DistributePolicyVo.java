package com.guolaiwan.app.web.distribute.vo;

import java.io.Serializable;

import com.guolaiwan.bussiness.distribute.po.DistributePolicy;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class DistributePolicyVo extends AbstractBaseVO<DistributePolicyVo, DistributePolicy> implements Serializable {
	private double price;
	private int count;
	private long proid;
	
	public long getProid() {
		return proid;
	}
	public void setProid(long proid) {
		this.proid = proid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public DistributePolicyVo set(DistributePolicy entity) throws Exception {
		DistributePolicyVo dp = new DistributePolicyVo();
		dp.setId(entity.getId());
		dp.setCount(entity.getCount());
		dp.setPrice(entity.getPrice());
		dp.setProid(entity.getDistributeProduct().getId());
		return dp;
	}
	
}
