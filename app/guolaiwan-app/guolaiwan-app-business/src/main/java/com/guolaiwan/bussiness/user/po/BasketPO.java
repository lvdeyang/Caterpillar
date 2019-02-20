package com.guolaiwan.bussiness.user.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 购物车
 * lvdeyang 2017年7月10日
 */
@Entity
@Table(name = "t_app_basket")
public class BasketPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	//用户
	private UserPO user;
	
	//商品
	private ProductPO product;
	
	//数量
	private int num;

	@ManyToOne
	@JoinColumn(name = "user_id")
	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "product_id")
	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
