package com.guolaiwan.bussiness.chapman.product.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.guolaiwan.bussiness.common.po.CodeListPO;
import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_app_tag_map")
public class TagMapPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	
	//货架商品
	private ShelveProductPO product;
	
	//标签
	private Long tag;
	
	@ManyToOne
	@JoinColumn(name = "shelve_product_id")
	public ShelveProductPO getProduct() {
		return product;
	}

	public void setProduct(ShelveProductPO product) {
		this.product = product;
	}

	public Long getTag() {
		return tag;
	}

	public void setTag(Long tag) {
		this.tag = tag;
	}

}
