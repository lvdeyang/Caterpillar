package com.guolaiwan.app.web.website.info.vo;

import java.util.List;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ProductVO extends AbstractBaseVO<ProductVO, ProductPO>{

	//名称
	private String name;
	
	//地理位置
	private String position;
	
	//标签
	private List<String> tags;
	
	//扩展描述
	private List<ProductInfoVO> infos;
	
	public String getName() {
		return name;
	}

	public ProductVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public ProductVO setPosition(String position) {
		this.position = position;
		return this;
	}
	
	public List<String> getTags() {
		return tags;
	}

	public ProductVO setTags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	public List<ProductInfoVO> getInfos() {
		return infos;
	}

	public ProductVO setInfos(List<ProductInfoVO> infos) {
		this.infos = infos;
		return this;
	}

	@Override
	public ProductVO set(ProductPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getIntroduction())
			.setPosition(entity.getPosition());
		return this;
	}

}
