package com.guolaiwan.bussiness.user.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.common.po.DiscountPO;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 折扣券余额
 * @author lvdeyang
 */
@Entity
@Table(name = "t_app_discount_balance")
public class DiscountBalancePO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;
	
	//隶属用户
	private UserPO user;
	
	//折扣券
	private DiscountPO discount;
	
	//余额
	private int balance;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name = "disAccountId")
	public DiscountPO getDiscount() {
		return discount;
	}

	public void setDiscount(DiscountPO discount) {
		this.discount = discount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
