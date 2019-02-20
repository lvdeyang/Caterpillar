package com.guolaiwan.bussiness.chapman.product.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 排期表
 * lvdeyang 2017年7月31日
 */
@Entity
@Table(name = "t_app_schedule")
public class SchedulePO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	
	//关联产品
	private ProductPO product;

	@ManyToOne
	@JoinColumn(name = "product_id")
	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}
	
}
