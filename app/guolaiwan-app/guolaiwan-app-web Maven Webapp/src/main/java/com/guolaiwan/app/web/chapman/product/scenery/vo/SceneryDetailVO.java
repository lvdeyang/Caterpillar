package com.guolaiwan.app.web.chapman.product.scenery.vo;

import com.guolaiwan.bussiness.chapman.product.po.ProductDetailPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class SceneryDetailVO extends AbstractBaseVO<SceneryDetailVO, ProductDetailPO>{

	//扩展信息标题
	private String title;
	
	//扩展信息描述
	private String introduction;
	
	//扩展信息类型
	private String type;
	
	//渲染样式名称
	private String styleName;
	
	//渲染样式
	private String css;
	
	//渲染顺序
	private int index;

	public String getTitle() {
		return title;
	}

	public SceneryDetailVO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getIntroduction() {
		return introduction;
	}

	public SceneryDetailVO setIntroduction(String introduction) {
		this.introduction = introduction;
		return this;
	}

	public String getType() {
		return type;
	}

	public SceneryDetailVO setType(String type) {
		this.type = type;
		return this;
	}

	public String getStyleName() {
		return styleName;
	}

	public SceneryDetailVO setStyleName(String styleName) {
		this.styleName = styleName;
		return this;
	}

	public String getCss() {
		return css;
	}

	public SceneryDetailVO setCss(String css) {
		this.css = css;
		return this;
	}

	public int getIndex() {
		return index;
	}

	public SceneryDetailVO setIndex(int index) {
		this.index = index;
		return this;
	}

	@Override
	public SceneryDetailVO set(ProductDetailPO entity) throws Exception {
		this.setId(entity.getId())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setTitle(entity.getTitle())
			.setIntroduction(entity.getIntroduction())
			.setType(entity.getType().getName())
			.setStyleName(entity.getStyle()==null?null:entity.getStyle().getName())
			.setCss(entity.getStyle()==null?null:entity.getStyle().getCss())
			.setIndex(entity.getIndex());
		return this;
	}

}
