package com.guolaiwan.app.web.admin.layout.vo;

import com.guolaiwan.bussiness.admin.po.FilterPO;
import com.guolaiwan.bussiness.common.enumeration.OperatorType;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class FilterVO extends AbstractBaseVO<FilterVO, FilterPO> {

	//备注
	private String remarks;

	//过滤器类型
	private String type;
	
	//运算符
	private String operator;
	
	//值1
	private String value_1;
	
	//值2
	private String value_2;
	
	public String getRemarks() {
		return remarks;
	}

	public FilterVO setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public String getType() {
		return type;
	}

	public FilterVO setType(String type) {
		this.type = type;
		return this;
	}

	public String getOperator() {
		return operator;
	}

	public FilterVO setOperator(String operator) {
		this.operator = operator;
		return this;
	}

	public String getValue_1() {
		return value_1;
	}

	public FilterVO setValue_1(String value_1) {
		this.value_1 = value_1;
		return this;
	}

	public String getValue_2() {
		return value_2;
	}

	public FilterVO setValue_2(String value_2) {
		this.value_2 = value_2;
		return this;
	}

	@Override
	public FilterVO set(FilterPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setRemarks(entity.getRemarks())
			.setOperator(entity.getOperator()==null||"".equals(entity.getOperator())?null:OperatorType.fromString(entity.getOperator()).getName())
			.setValue_1(entity.getValue_1())
			.setValue_2(entity.getValue_2())
			.setType(entity.getType().getName());
		return this;
	}

}
