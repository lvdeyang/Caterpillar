package com.guolaiwan.app.web.chapman.product.scenery.vo;

import com.guolaiwan.bussiness.chapman.product.dto.ProductDTO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class SceneryVO extends AbstractBaseVO<SceneryVO, ProductDTO>{

	//景点名称
	private String name;
	
	//景点描述
	private String introduction;
	
	//景点地理位置
	private String position;
	
	//价格
	private Long price;
	
	public String getName() {
		return name;
	}

	public SceneryVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getIntroduction() {
		return introduction;
	}

	public SceneryVO setIntroduction(String introduction) {
		this.introduction = introduction;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public SceneryVO setPosition(String position) {
		this.position = position;
		return this;
	}
	
	public Long getPrice() {
		return price;
	}

	public SceneryVO setPrice(Long price) {
		this.price = price;
		return this;
	}

	@Override
	public SceneryVO set(ProductDTO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getName())
			.setIntroduction(entity.getIntroduction())
			.setPosition(entity.getPosition())
			.setPrice(entity.getPrice());
		return this;
	}

}
