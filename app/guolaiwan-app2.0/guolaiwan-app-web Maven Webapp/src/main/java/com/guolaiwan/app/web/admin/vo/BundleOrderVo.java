package com.guolaiwan.app.web.admin.vo;

import java.util.ArrayList;
import java.util.List;

public class BundleOrderVo {
	private String id;
	private String userTel;
	private String createDate;
	private String productName;
	private String orderAllMoney;
	private String reason;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOrderAllMoney() {
		return orderAllMoney;
	}
	public void setOrderAllMoney(String orderAllMoney) {
		this.orderAllMoney = orderAllMoney;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
