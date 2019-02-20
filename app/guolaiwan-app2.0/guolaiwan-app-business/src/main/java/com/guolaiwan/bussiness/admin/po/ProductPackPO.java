package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_product_package")
public class ProductPackPO extends AbstractBasePO {
	//名称
	private String name;
	//现价
	private long price;
	//原价
	private long oldPrice;
	//图片
	private String pic;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public long getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(long oldPrice) {
		this.oldPrice = oldPrice;
	}
	
	
	


}
