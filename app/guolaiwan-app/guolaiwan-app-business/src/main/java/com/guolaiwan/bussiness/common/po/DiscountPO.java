package com.guolaiwan.bussiness.common.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.guolaiwan.bussiness.user.po.DiscountBalancePO;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 折扣券
 * lvdeyang 2017年6月21日
 */
@Entity
@Table(name = "t_app_discount")
public class DiscountPO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;
	
	//折扣券描述
	private String introduction;
	
	//计算公式
	private String formula;
	
    private Set<DiscountBalancePO> discountBalances;
    
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	@OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	public Set<DiscountBalancePO> getDiscountBalances() {
		return discountBalances;
	}

	public void setDiscountBalances(Set<DiscountBalancePO> discountBalances) {
		this.discountBalances = discountBalances;
	}

}
