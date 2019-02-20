package com.guolaiwan.app.web.chapman.tag.vo;

import com.guolaiwan.bussiness.common.po.CodeListPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class TagVO extends AbstractBaseVO<TagVO, CodeListPO> {

	//标签代码
	private String code;
	
	//标签名称
	private String name;
	
	public String getCode() {
		return code;
	}

	public TagVO setCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public TagVO setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public TagVO set(CodeListPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setCode(entity.getCode())
			.setName(entity.getName());
		return this;
	}

}
