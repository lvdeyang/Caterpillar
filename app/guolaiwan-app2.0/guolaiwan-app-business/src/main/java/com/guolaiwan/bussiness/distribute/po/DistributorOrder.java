package com.guolaiwan.bussiness.distribute.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.alibaba.druid.support.logging.Log;
import com.guolaiwan.bussiness.distribute.classify.DistributorOrderStatus;
import com.guolaiwan.bussiness.distribute.classify.DistributorType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "distributor_Order")
public class DistributorOrder extends AbstractBasePO {

	private long productId;
	private long distributorId;
	private long parentId;
	private DistributorType type;
	private double price;
	private int count;
	private long region;
	private DistributorOrderStatus status;
	private String rejectReason;
	private String contractPicUrl;
	private String contractVideoUrl;
	private Date applicationDate;


	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getContractPicUrl() {
		return contractPicUrl;
	}
	public void setContractPicUrl(String contractPicUrl) {
		this.contractPicUrl = contractPicUrl;
	}
	public String getContractVideoUrl() {
		return contractVideoUrl;
	}
	public void setContractVideoUrl(String contractVideoUrl) {
		this.contractVideoUrl = contractVideoUrl;
	}
	public long getRegion() {
		return region;
	}
	public void setRegion(long region) {
		this.region = region;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(long distributorId) {
		this.distributorId = distributorId;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	@Enumerated(EnumType.STRING)
	public DistributorType getType() {
		return type;
	}
	public void setType(DistributorType type) {
		this.type = type;
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
	@Enumerated(EnumType.STRING)
	public DistributorOrderStatus getStatus() {
		return status;
	}
	public void setStatus(DistributorOrderStatus status) {
		this.status = status;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	
}
