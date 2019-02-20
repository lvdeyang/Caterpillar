package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 
 * <p>Title: Merchant</p>
 * <p>Description: 商户分类PO</p>
 * <p>Company: guolaiwan</p> 
 * @author Mr.Sun
 * @date 2017年11月27日 下午1:14:32
 */

@Entity
@Table(name = "t_sys_merchant_modular")
public class MerModularPO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;
	
	//名称
	private String name;
	//是否启用
	private int isv;
	//级别
	private int level;
	//所属分类
	private long parentId;
	//所属分类名称
	private String parentName;
	
	//绑定商家
	private MerchantPO merchant;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsv() {
		return isv;
	}

	public void setIsv(int isv) {
		this.isv = isv;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}



	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@ManyToOne
	@JoinColumn(name = "merchant_id")
	public MerchantPO getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantPO merchant) {
		this.merchant = merchant;
	}
	
	
	
	
	
	
	
}
