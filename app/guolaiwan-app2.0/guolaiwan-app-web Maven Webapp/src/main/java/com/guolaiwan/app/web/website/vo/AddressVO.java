package com.guolaiwan.app.web.website.vo;


import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class AddressVO extends AbstractBaseVO<AddressVO, AddressPO> {

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
	// 手机号码
	private String consigneeMobile;
	// 固定电话
	private String consigneePhone;
	// 邮箱
	private String consigneeEmail;
	// 地址别名
	private String consigneeAlias;
	// 是否为默认地址:1代表TRUE,0代表FALSE
	private int defaultAddress;


	public long getUserId() {
		return userId;
	}



	public AddressVO setUserId(long userId) {
		this.userId = userId;
		return this;
	}



	public String getConsigneeName() {
		return consigneeName;
	}



	public AddressVO setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
		return this;
	}



	public String getProvince() {
		return province;
	}



	public AddressVO setProvince(String province) {
		this.province = province;
		return this;
	}



	public String getCity() {
		return city;
	}



	public AddressVO setCity(String city) {
		this.city = city;
		return this;
	}



	public String getDistrict() {
		return district;
	}



	public AddressVO setDistrict(String district) {
		this.district = district;
		return this;
	}



	public String getConsigneeAddress() {
		return consigneeAddress;
	}



	public AddressVO setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
		return this;
	}



	public String getConsigneeMobile() {
		return consigneeMobile;
	}



	public AddressVO setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
		return this;
	}



	public String getConsigneePhone() {
		return consigneePhone;
	}



	public AddressVO setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
		return this;
	}



	public String getConsigneeEmail() {
		return consigneeEmail;
	}



	public AddressVO setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
		return this;
	}



	public String getConsigneeAlias() {
		return consigneeAlias;
	}



	public AddressVO setConsigneeAlias(String consigneeAlias) {
		this.consigneeAlias = consigneeAlias;
		return this;
	}



	public int getDefaultAddress() {
		return defaultAddress;
	}



	public AddressVO setDefaultAddress(int defaultAddress) {
		this.defaultAddress = defaultAddress;
		return this;
	}


	@Override
	public AddressVO set(AddressPO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUserId(entity.getUserId())
		.setConsigneeName(entity.getConsigneeName())
		.setProvince(entity.getProvince())
		.setCity(entity.getCity())
		.setDistrict(entity.getDistrict())
		.setConsigneeAddress(entity.getConsigneeAddress())
		.setConsigneeMobile(entity.getConsigneeMobile())
		.setConsigneePhone(entity.getConsigneePhone())
		.setConsigneeEmail(entity.getConsigneeEmail())
		.setConsigneeAlias(entity.getConsigneeAlias())
		.setDefaultAddress(entity.getDefaultAddress());
		return this;
	}
}
