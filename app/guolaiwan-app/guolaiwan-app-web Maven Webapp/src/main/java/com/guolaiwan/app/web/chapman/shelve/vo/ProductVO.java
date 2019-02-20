package com.guolaiwan.app.web.chapman.shelve.vo;

import com.guolaiwan.bussiness.chapman.product.dto.ProductDTO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ProductVO extends AbstractBaseVO<ProductVO, ProductDTO> {

	//商品描述
	private String introduction;
	
	//地理位置
	private String position;
	
	public String getIntroduction() {
		return introduction;
	}

	public ProductVO setIntroduction(String introduction) {
		this.introduction = introduction;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public ProductVO setPosition(String position) {
		this.position = position;
		return this;
	}

	@Override
	public ProductVO set(ProductDTO entity) throws Exception {
		
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setIntroduction(entity.getIntroduction())
			.setPosition(entity.getPosition());
		
		return this;
	}

}
