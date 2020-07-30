package com.guolaiwan.bussiness.merchant.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import com.guolaiwan.bussiness.admin.enumeration.ActivityType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_reportorderall")
public class ReportOrderAllPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	
	private int count;
	private String Sex;
	private String sexString;
	private int type;//0线下，1旅行社，2三方ota
	private String typeString;
	private long productId;
	private String productName;
	private long merchantId;
	private String merchantName;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getSexString() {
		return sexString;
	}
	public void setSexString(String sexString) {
		this.sexString = sexString;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTypeString() {
		return typeString;
	}
	public void setTypeString(String typeString) {
		this.typeString = typeString;
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
	
	

	
}
