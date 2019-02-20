package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.FilterType;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 过滤器
 * lvdeyang 2017年7月13日
 */
@Entity
@Table(name = "t_app_filter")
public class FilterPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	
	//备注
	private String remarks;

	//过滤器类型
	private FilterType type;
	
	//运算符
	private String operator;
	
	//键1
	private String key_1;
	
	//值1
	private String value_1;
	
	//值2
	private String value_2;
	
	//条件公式
	private String formula;
	
	//关联版块
	private SectionPO section;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public FilterType getType() {
		return type;
	}

	public void setType(FilterType type) {
		this.type = type;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getKey_1() {
		return key_1;
	}

	public void setKey_1(String key_1) {
		this.key_1 = key_1;
	}

	public String getValue_1() {
		return value_1;
	}

	public void setValue_1(String value_1) {
		this.value_1 = value_1;
	}

	public String getValue_2() {
		return value_2;
	}

	public void setValue_2(String value_2) {
		this.value_2 = value_2;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	@ManyToOne
	@JoinColumn(name = "section_id")
	public SectionPO getSection() {
		return section;
	}

	public void setSection(SectionPO section) {
		this.section = section;
	}
	
}
