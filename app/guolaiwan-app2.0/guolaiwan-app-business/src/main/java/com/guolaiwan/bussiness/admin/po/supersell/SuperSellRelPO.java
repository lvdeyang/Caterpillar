package com.guolaiwan.bussiness.admin.po.supersell;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "supersell_relation")
public class SuperSellRelPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	
	private long supersellId;

	private long productId;

	private String productName;
	
	private String productPic;
	
	private String merchantName;
	
	private long oldPrice;
	
	private long price;

	public long getSupersellId() {
		return supersellId;
	}

	public void setSupersellId(long supersellId) {
		this.supersellId = supersellId;
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

	public long getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(long oldPrice) {
		this.oldPrice = oldPrice;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	
	
}
