package com.guolaiwan.bussiness.order.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 订单详情
 * lvdeyang 2017年6月22日
 */
@Entity
@Table(name = "t_app_order_detail")
public class OrderDetailPO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;
	
	//产品
	private ProductPO product;
	
	//产品商户
	private ChapmanPO chapman;
	
	//隶属订单
	private OrderPO order;
	
	//商品数量
	private int num;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}

	@ManyToOne
	@JoinColumn(name = "chapman_id")
	public ChapmanPO getChapman() {
		return chapman;
	}

	public void setChapman(ChapmanPO chapman) {
		this.chapman = chapman;
	}

	@ManyToOne
	@JoinColumn(name = "order_id")
	public OrderPO getOrder() {
		return order;
	}

	public void setOrder(OrderPO order) {
		this.order = order;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
