package com.guolaiwan.app.web.admin.category.vo;

import com.guolaiwan.bussiness.common.po.CodeListPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class CategoryVO extends AbstractBaseVO<CategoryVO, CodeListPO> {

	//代码名称
	private String name;
	
	//代码
	private String code;
	
	public String getName() {
		return name;
	}

	public CategoryVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getCode() {
		return code;
	}

	public CategoryVO setCode(String code) {
		this.code = code;
		return this;
	}
	
	@Override
	public CategoryVO set(CodeListPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setCode(entity.getCode())
			.setName(entity.getName());
		return this;
	}

}
