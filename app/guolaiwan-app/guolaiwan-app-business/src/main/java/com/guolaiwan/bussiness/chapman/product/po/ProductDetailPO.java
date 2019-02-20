package com.guolaiwan.bussiness.chapman.product.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.chapman.product.enumeration.ProductDetailStyle;
import com.guolaiwan.bussiness.chapman.product.enumeration.ProductDetailType;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 产品详情
 * lvdeyang 2017年6月22日
 */
@Entity
@Table(name = "t_app_product_detail")
public class ProductDetailPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//产品明细名称
	private String title;
	
	//产品明细描述
	private String introduction;
	
	//产品价格
	private Long price;
	
	//产品明细类型
	private ProductDetailType type;
	
	//产品明细渲染类型
	private ProductDetailStyle style;
	
	//渲染顺序
	private int index;
	
	//隶属产品
	private ProductPO product;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Enumerated(EnumType.STRING)
	public ProductDetailType getType() {
		return type;
	}

	public void setType(ProductDetailType type) {
		this.type = type;
	}

	@Enumerated(EnumType.STRING)
	public ProductDetailStyle getStyle() {
		return style;
	}

	public void setStyle(ProductDetailStyle style) {
		this.style = style;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@ManyToOne
	@JoinColumn(name = "product_id")
	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}

}
