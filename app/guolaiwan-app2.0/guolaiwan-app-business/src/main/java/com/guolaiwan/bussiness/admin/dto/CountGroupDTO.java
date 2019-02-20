package com.guolaiwan.bussiness.admin.dto;

import java.text.DecimalFormat;

import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;

public class CountGroupDTO {
	//id
	private long id;
	//名称
	private String name;
	//数量
	private int count;
	//类型
	private int type;
	//总价
	private long sumMoney;
	//总价str
	private String sumMoneyStr;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(long sumMoney) {
		this.sumMoney = sumMoney;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSumMoneyStr() {
		return sumMoneyStr;
	}
	public void setSumMoneyStr(String sumMoneyStr) {
		this.sumMoneyStr = sumMoneyStr;
	}
	



}
