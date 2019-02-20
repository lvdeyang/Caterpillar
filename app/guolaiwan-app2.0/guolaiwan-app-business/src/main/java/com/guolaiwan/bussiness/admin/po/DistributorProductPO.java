package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 分销商的商品表
 * @author Administrator
 *
 */
@Entity
@Table(name="t_sys_distributorProduct")
public class DistributorProductPO extends AbstractBasePO {

	
	private static final long serialVersionUID = -4033023384636709538L;
	//关联
	private ProductPO product;
	private long distributorId;
	//进价
	private long distributorPrice;
	//售价
	private long sellPrice;
	@ManyToOne
	@JoinColumn(name = "product_id")
	public ProductPO getProduct() {
		return product;
	}
	public void setProduct(ProductPO product) {
		this.product = product;
	}
	public long getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(long distributorId) {
		this.distributorId = distributorId;
	}
	public long getDistributorPrice() {
		return distributorPrice;
	}
	public void setDistributorPrice(long distributorPrice) {
		this.distributorPrice = distributorPrice;
	}
	public long getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(long sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	
}
