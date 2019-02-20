package com.guolaiwan.bussiness.admin.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.guolaiwan.bussiness.common.enumeration.BooleanType;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 页面布局
 * lvdeyang 2017年7月13日
 */
@Entity
@Table(name = "t_app_page_layout")
public class PageLayoutPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	//名称
	private String name;
	
	//状态：生效、不生效
	private BooleanType enable;
	
	//页面主题
	private String theme;
	
	//备注
	private String remarks;
	
	//关联页面
	private Set<PagePO> pages;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	public BooleanType getEnable() {
		return enable;
	}

	public void setEnable(BooleanType enable) {
		this.enable = enable;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(mappedBy = "pageLayout", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<PagePO> getPages() {
		return pages;
	}

	public void setPages(Set<PagePO> pages) {
		this.pages = pages;
	}
	
}
