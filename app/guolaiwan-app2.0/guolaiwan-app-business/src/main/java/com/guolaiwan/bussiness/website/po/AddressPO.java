package com.guolaiwan.bussiness.website.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_website_address")
public class AddressPO extends AbstractBasePO {

	private static final long serialVersionUID = -7946339945888094726L;
	
	// 所属用户
	private long userId;
	// 收货人
	private String consigneeName;
	//省
	private String province;
	//市
	private String city;
	//区
	private String district;
	// 详细地址
	private String consigneeAddress;
	// 固定电话
	private String consigneeMobile;
	// 手机号码
	private String consigneePhone;
	// 邮箱
	private String consigneeEmail;
	// 地址别名
	private String consigneeAlias;
	// 是否为默认地址:1代表TRUE,0代表FALSE
	private int defaultAddress;
	
	private String idNum;
	
	private String photo;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	public String getConsigneeMobile() {
		return consigneeMobile;
	}
	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}
	public String getConsigneePhone() {
		return consigneePhone;
	}
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	public String getConsigneeEmail() {
		return consigneeEmail;
	}
	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}
	public String getConsigneeAlias() {
		return consigneeAlias;
	}
	public void setConsigneeAlias(String consigneeAlias) {
		this.consigneeAlias = consigneeAlias;
	}
	public int getDefaultAddress() {
		return defaultAddress;
	}
	public void setDefaultAddress(int defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	@Column(columnDefinition="BLOB")
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
}
