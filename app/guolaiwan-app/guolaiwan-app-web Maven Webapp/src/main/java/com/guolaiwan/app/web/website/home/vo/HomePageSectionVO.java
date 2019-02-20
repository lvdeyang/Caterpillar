package com.guolaiwan.app.web.website.home.vo;

import java.util.List;
import com.guolaiwan.app.web.website.vo.ShelveProductVO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class HomePageSectionVO extends AbstractBaseVO<HomePageSectionVO, MenuPO> {

	//版块标题
	private String title;
	
	//版块简介
	private String summary;
	
	//版块内容
	private List<ShelveProductVO> products;

	public String getTitle() {
		return title;
	}

	public HomePageSectionVO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getSummary() {
		return summary;
	}

	public HomePageSectionVO setSummary(String summary) {
		this.summary = summary;
		return this;
	}

	public List<ShelveProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ShelveProductVO> products) {
		this.products = products;
	}

	@Override
	public HomePageSectionVO set(MenuPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setTitle(entity.getName())
			.setSummary(entity.getSummary());
		return this;
	}
	
	
	
}
