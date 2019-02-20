package com.guolaiwan.app.web.admin.layout.vo;

import com.guolaiwan.bussiness.admin.po.SectionPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class SectionVO extends AbstractBaseVO<SectionVO, SectionPO> {

	//版块类型
	private String type;
	
	//模板名称
	private String template;
	
	//备注
	private String remarks;
	
	//标题
	private String title;
	
	//详情
	private String summary;
	
	public String getType() {
		return type;
	}

	public SectionVO setType(String type) {
		this.type = type;
		return this;
	}

	public String getTemplate() {
		return template;
	}

	public SectionVO setTemplate(String template) {
		this.template = template;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public SectionVO setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public SectionVO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getSummary() {
		return summary;
	}

	public SectionVO setSummary(String summary) {
		this.summary = summary;
		return this;
	}

	@Override
	public SectionVO set(SectionPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setType(entity.getType().getName())
			.setTemplate(entity.getTemplate())
			.setRemarks(entity.getRemarks())
			.setTitle(entity.getTitle())
			.setSummary(entity.getSummary());
		return this;
	}

}
