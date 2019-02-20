package com.guolaiwan.app.web.admin.layout.vo;

import com.guolaiwan.bussiness.admin.po.PageLayoutPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class PageLayoutVO extends AbstractBaseVO<PageLayoutVO, PageLayoutPO> {

	private String name;
	
	//状态：生效、不生效
	private String enable;
	
	//页面主题
	private String theme;
	
	//备注
	private String remarks;
	
	public String getName() {
		return name;
	}

	public PageLayoutVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getEnable() {
		return enable;
	}

	public PageLayoutVO setEnable(String enable) {
		this.enable = enable;
		return this;
	}

	public String getTheme() {
		return theme;
	}

	public PageLayoutVO setTheme(String theme) {
		this.theme = theme;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public PageLayoutVO setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	@Override
	public PageLayoutVO set(PageLayoutPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getName())
			.setEnable(entity.getEnable().getName())
			.setTheme(entity.getTheme())
			.setRemarks(entity.getRemarks());
		return this;
	}

}
