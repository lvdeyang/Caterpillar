package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_merchantchildren")
public class MerchantChildrenPO extends AbstractBasePO {
     private long childrenId;
     private long merchantId;
     public long getChildrenId() {
		return childrenId;
	}
	public void setChildrenId(long childrenId) {
		this.childrenId = childrenId;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getChildrenName() {
		return childrenName;
	}
	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}
	private String merchantName;
     private String childrenName;
}
