package com.guolaiwan.bussiness.admin.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.guolaiwan.bussiness.admin.enumeration.PageType;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 页面信息
 * lvdeyang 2017年7月13日
 */
@Entity
@Table(name = "t_app_page")
public class PagePO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//页面名称
	private String name;
	
	//页面类型
	private PageType type;
	
	//关联菜单
	private MenuPO menu;
	
	//关联布局
	private PageLayoutPO pageLayout;
	
	//关联版块
	private Set<SectionPO> sections;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	public PageType getType() {
		return type;
	}

	public void setType(PageType type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name = "menu_id")
	public MenuPO getMenu() {
		return menu;
	}

	public void setMenu(MenuPO menu) {
		this.menu = menu;
	}

	@ManyToOne
	@JoinColumn(name = "page_layout_id")
	public PageLayoutPO getPageLayout() {
		return pageLayout;
	}

	public void setPageLayout(PageLayoutPO pageLayout) {
		this.pageLayout = pageLayout;
	}

	@OneToMany(mappedBy = "page", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SectionPO> getSections() {
		return sections;
	}

	public void setSections(Set<SectionPO> sections) {
		this.sections = sections;
	}
	
}
