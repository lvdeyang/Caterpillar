package com.guolaiwan.bussiness.chapman.product.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.product.enumeration.ProductType;
import com.guolaiwan.bussiness.order.po.OrderDetailPO;
import com.guolaiwan.bussiness.user.po.BasketPO;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 产品
 * lvdeyang 2017年6月21日
 */
@Entity
@Table(name = "t_app_product")
public class ProductPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//产品名称
	private String name;
	
	//产品描述
	private String introduction;
	
	//产品价格
	private Long price;
	
	//产品类型
	private ProductType type;
	
	//地理位置
	private String position;
	
	//隶属商户
	private ChapmanPO chapman;
	
	//产品明细
	private Set<ProductDetailPO> productDetails;
	
	//关联货架
	private ShelveProductPO shelve;
	
	//父产品
	private ProductPO product;
	
	//子产品
	private Set<ProductPO> subProducts;
	
	//订单列表
	private Set<OrderDetailPO> orderDetails;
	
	//购物车
	private Set<BasketPO> baskets;
	
	//时刻图
	private Set<TimelinePO> timelines;
	
	//排期表
	private Set<SchedulePO> schedules;
	
	//图文详情
	private Set<GraphicDetailsPO> graphicDetails;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@ManyToOne
	@JoinColumn(name = "chapman_id")
	public ChapmanPO getChapman() {
		return chapman;
	}

	public void setChapman(ChapmanPO chapman) {
		this.chapman = chapman;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<ProductDetailPO> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Set<ProductDetailPO> productDetails) {
		this.productDetails = productDetails;
	}

	@OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public ShelveProductPO getShelve() {
		return shelve;
	}

	public void setShelve(ShelveProductPO shelve) {
		this.shelve = shelve;
	}

	@ManyToOne
	@JoinColumn(name = "product_id")
	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<ProductPO> getSubProducts() {
		return subProducts;
	}

	public void setSubProducts(Set<ProductPO> subProducts) {
		this.subProducts = subProducts;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<OrderDetailPO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetailPO> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<BasketPO> getBaskets() {
		return baskets;
	}

	public void setBaskets(Set<BasketPO> baskets) {
		this.baskets = baskets;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<TimelinePO> getTimelines() {
		return timelines;
	}

	public void setTimelines(Set<TimelinePO> timelines) {
		this.timelines = timelines;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SchedulePO> getSchedules() {
		return schedules;
	}

	public void setSchedules(Set<SchedulePO> schedules) {
		this.schedules = schedules;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<GraphicDetailsPO> getGraphicDetails() {
		return graphicDetails;
	}

	public void setGraphicDetails(Set<GraphicDetailsPO> graphicDetails) {
		this.graphicDetails = graphicDetails;
	}
	
}
