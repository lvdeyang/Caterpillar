package com.guolaiwan.bussiness.chapman.product.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.chapman.product.enumeration.AuditStatusType;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 货架商品
 * lvdeyang 2017年7月5日
 */
@Entity
@Table(name = "t_app_shelve_product")
public class ShelveProductPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	//关联主题
	private MenuPO menu;
	
	//关联商品
	private ProductPO product;
	
	//关联商品类型描述
	private String type;
	
	//商品标签
	private Set<TagMapPO> tags;
	
	//审核状态
	private AuditStatusType auditStatus;

	@ManyToOne
	@JoinColumn(name = "menu_id")
	public MenuPO getMenu() {
		return menu;
	}

	public void setMenu(MenuPO menu) {
		this.menu = menu;
	}

	@OneToOne
	@JoinColumn(name = "product_id")
	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<TagMapPO> getTags() {
		return tags;
	}

	public void setTags(Set<TagMapPO> tags) {
		this.tags = tags;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "audit_status")
	public AuditStatusType getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditStatusType auditStatus) {
		this.auditStatus = auditStatus;
	}
	
}
