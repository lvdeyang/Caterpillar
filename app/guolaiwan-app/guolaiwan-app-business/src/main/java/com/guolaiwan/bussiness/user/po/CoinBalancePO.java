package com.guolaiwan.bussiness.user.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.common.po.CoinPO;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 货币余额
 * @author lvdeyang
 */
@Entity
@Table(name = "t_app_coin_balance")
public class CoinBalancePO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;
	
	//隶属用户
	private UserPO user;
	
	//货币
	private CoinPO coin;
	
	//余额
	private Long balance;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name = "coin_id")
	public CoinPO getCoin() {
		return coin;
	}

	public void setCoin(CoinPO coin) {
		this.coin = coin;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
