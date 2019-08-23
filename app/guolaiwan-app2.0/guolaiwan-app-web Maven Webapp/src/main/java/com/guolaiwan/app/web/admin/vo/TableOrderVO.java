package com.guolaiwan.app.web.admin.vo;

public class TableOrderVO {
	
	//订单号
	private String orderid;
	//商家名称
	private String merchantName;
//	桌子Id
	private String tableId;
//	客户姓名
	private String userName;
//	客户电话
	private String userPhone;
//	用餐时间
	private String type;
//	预定日期
	private String tableDate;
//	订桌状态
	private String tableState;
//	预定金额
	private String bookPrice;
//	订餐状态
	private String dishState;
//	订餐金额
	private String dishPrice;
	
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTableDate() {
		return tableDate;
	}
	public void setTableDate(String tableDate) {
		this.tableDate = tableDate;
	}
	public String getTableState() {
		return tableState;
	}
	public void setTableState(String tableState) {
		this.tableState = tableState;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getDishState() {
		return dishState;
	}
	public void setDishState(String dishState) {
		this.dishState = dishState;
	}
	public String getDishPrice() {
		return dishPrice;
	}
	public void setDishPrice(String dishPrice) {
		this.dishPrice = dishPrice;
	}
	



}