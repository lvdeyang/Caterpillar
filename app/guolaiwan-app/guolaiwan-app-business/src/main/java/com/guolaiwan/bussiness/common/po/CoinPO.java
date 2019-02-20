package com.guolaiwan.bussiness.common.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.guolaiwan.bussiness.user.po.CoinBalancePO;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 货币实体
 * lvdeyang 2017年6月21日
 */
@Entity
@Table(name = "t_app_coin")
public class CoinPO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;
	
	//货币描述
	private String introduction;
	
	//价值比率（跟人民币兑换比率）
	private Double ratio;
	
	private Set<CoinBalancePO> coinBalances;

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	@OneToMany(mappedBy = "coin", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	public Set<CoinBalancePO> getCoinBalances() {
		return coinBalances;
	}

	public void setCoinBalances(Set<CoinBalancePO> coinBalances) {
		this.coinBalances = coinBalances;
	}

}
