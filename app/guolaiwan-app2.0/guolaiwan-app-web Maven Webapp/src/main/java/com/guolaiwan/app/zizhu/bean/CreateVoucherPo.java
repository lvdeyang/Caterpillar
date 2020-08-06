package com.guolaiwan.app.zizhu.bean;

import java.util.List;

public class CreateVoucherPo {
	//签名
	String sign;
	//凭证类型	1:预售、2:现售
	int type;
	//窗口Id
	int salesWinId;
	//用户Id
	int userId;
	//结算方式	1:支付宝、2:微信、3:现金、4:银联
	int settlementType;
	//证件号码
	String idCard;
	//手机号
	String phone;
	//联系人
	String contactName;
	//出游时间  格式：yyyy-MM-dd
	String tourTime;
	List<Details> details;
	public class Details{
		//产品Id
		int productId;
		//数量
		int quantity;
		//姓名
		String name;
		//证件号码
		String idCard;
		//联系电话
		String phone;
		//人脸
		String face;
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getIdCard() {
			return idCard;
		}
		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getFace() {
			return face;
		}
		public void setFace(String face) {
			this.face = face;
		}
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSalesWinId() {
		return salesWinId;
	}
	public void setSalesWinId(int salesWinId) {
		this.salesWinId = salesWinId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(int settlementType) {
		this.settlementType = settlementType;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getTourTime() {
		return tourTime;
	}
	public void setTourTime(String tourTime) {
		this.tourTime = tourTime;
	}
	public List<Details> getDetails() {
		return details;
	}
	public void setDetails(List<Details> details) {
		this.details = details;
	}
	
	
}
