package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_product_combo")
public class ProductComboPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	private long  productId;
	
	private String productName;
	
	private String combo;
	private long comboprice;

	public long getComboprice() {
		return comboprice;
	}

	public void setComboprice(long comboprice) {
		this.comboprice = comboprice;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}
}
