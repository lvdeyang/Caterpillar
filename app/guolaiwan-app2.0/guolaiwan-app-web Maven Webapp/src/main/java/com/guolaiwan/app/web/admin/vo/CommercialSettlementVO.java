package com.guolaiwan.app.web.admin.vo;

public class CommercialSettlementVO {

	private long id;
	private String shopName;
	private String shopBankId;
	private String shopLinkPerson;
	private String shopOpenBank;
	public String getShopLinkPerson() {
		return shopLinkPerson;
	}
	public void setShopLinkPerson(String shopLinkPerson) {
		this.shopLinkPerson = shopLinkPerson;
	}
	public String getShopOpenBank() {
		return shopOpenBank;
	}
	public void setShopOpenBank(String shopOpenBank) {
		this.shopOpenBank = shopOpenBank;
	}
	private double price;
	private double accrued;
	public double getAccrued() {
		return accrued;
	}
	public void setAccrued(double accrued) {
		this.accrued = accrued;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopBankId() {
		return shopBankId;
	}
	public void setShopBankId(String shopBankId) {
		this.shopBankId = shopBankId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
