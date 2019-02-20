package com.guolaiwan.app.web.admin.tag.vo;

import com.guolaiwan.bussiness.common.po.CodeListPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class TagVO extends AbstractBaseVO<TagVO, CodeListPO> {

	//代码名称
	private String name;
	
	//代码
	private String code;
	
	public String getName() {
		return name;
	}

	public TagVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getCode() {
		return code;
	}

	public TagVO setCode(String code) {
		this.code = code;
		return this;
	}

	@Override
	public TagVO set(CodeListPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getName())
			.setCode(entity.getCode());
		return this;
	}

}
