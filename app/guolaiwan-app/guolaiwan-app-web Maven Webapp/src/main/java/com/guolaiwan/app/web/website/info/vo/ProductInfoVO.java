package com.guolaiwan.app.web.website.info.vo;

import com.guolaiwan.bussiness.chapman.product.po.ProductDetailPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ProductInfoVO extends AbstractBaseVO<ProductInfoVO, ProductDetailPO>{

	//标题
	private String title;
	
	//内容
	private String content;
	
	public String getTitle() {
		return title;
	}

	public ProductInfoVO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public ProductInfoVO setContent(String content) {
		this.content = content;
		return this;
	}

	@Override
	public ProductInfoVO set(ProductDetailPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setTitle(entity.getTitle())
			.setContent(entity.getIntroduction());
		return this;
	}

}
