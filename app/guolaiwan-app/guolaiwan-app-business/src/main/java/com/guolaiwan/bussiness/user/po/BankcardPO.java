package com.guolaiwan.bussiness.user.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 银行卡
 * lvdeyang 2017年6月21日
 */
@Entity
@Table(name = "t_app_bankcard")
public class BankcardPO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;
	
	//银行名称
	private String bankname;
	
	//银行卡号码
	private String number;
	
	//隶属用户
	private UserPO user;

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}
	
}
