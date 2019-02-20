package com.guolaiwan.bussiness.admin.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.guolaiwan.bussiness.admin.enumeration.SectionType;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 页面版块
 * lvdeyang 2017年7月13日
 */
@Entity
@Table(name = "t_app_section")
public class SectionPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	//版块类型
	private SectionType type;
	
	//模板名称
	private String template;
	
	//备注
	private String remarks;
	
	//标题
	private String title;
	
	//详情
	private String summary;
	
	//关联页面
	private PagePO page;
	
	//关联过滤器
	private Set<FilterPO> filters;

	public SectionType getType() {
		return type;
	}

	public void setType(SectionType type) {
		this.type = type;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@ManyToOne
	@JoinColumn(name = "page_id")
	public PagePO getPage() {
		return page;
	}

	public void setPage(PagePO page) {
		this.page = page;
	}

	@OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<FilterPO> getFilters() {
		return filters;
	}

	public void setFilters(Set<FilterPO> filters) {
		this.filters = filters;
	}
	
}
