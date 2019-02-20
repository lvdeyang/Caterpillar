package com.guolaiwan.bussiness.admin.po;

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
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.MenuRenderType;
import com.guolaiwan.bussiness.chapman.product.po.ShelveProductPO;
import com.guolaiwan.bussiness.common.enumeration.BooleanType;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 菜单
 * lvdeyang 2017年7月5日
 */
@Entity
@Table(name = "t_app_menu")
public class MenuPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//菜单名称
	private String name;
	
	//菜单详情
	private String summary;
	
	//渲染类型
	private MenuRenderType renderType;
	
	//父菜单
	private MenuPO parent;
	
	//子菜单
	private Set<MenuPO> subMenus;
	
	//关联货架商品
	private Set<ShelveProductPO> products;
	
	//关联页面
	private Set<PagePO> pages;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "render_type")
	public MenuRenderType getRenderType() {
		return renderType;
	}

	public void setRenderType(MenuRenderType renderType) {
		this.renderType = renderType;
	}
	
	@ManyToOne
	@JoinColumn(name = "menu_id")
	public MenuPO getParent() {
		return parent;
	}

	public void setParent(MenuPO parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<MenuPO> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(Set<MenuPO> subMenus) {
		this.subMenus = subMenus;
	}

	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<ShelveProductPO> getProducts() {
		return products;
	}

	public void setProducts(Set<ShelveProductPO> products) {
		this.products = products;
	}

	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<PagePO> getPages() {
		return pages;
	}

	public void setPages(Set<PagePO> pages) {
		this.pages = pages;
	}
	
}
