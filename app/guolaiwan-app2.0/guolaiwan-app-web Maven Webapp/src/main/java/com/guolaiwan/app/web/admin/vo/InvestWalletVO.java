package com.guolaiwan.app.web.admin.vo;


import com.guolaiwan.bussiness.admin.po.InvestWalletPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class InvestWalletVO extends AbstractBaseVO<InvestWalletVO,InvestWalletPO>{

	private long userid;
	
	private String username;
	
	private long money;
	
	private String productname;

	
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	@Override
	public InvestWalletVO set(InvestWalletPO entity) throws Exception {
		this.setId(entity.getId());
		this.setUserid(entity.getUserid());
		this.setUsername(entity.getUsername());
		this.setMoney(entity.getMoney());
		this.setUpdateTime(entity.getUpdateTime());
		this.setProductname(entity.getProductname());
		return this;
	}
	

}