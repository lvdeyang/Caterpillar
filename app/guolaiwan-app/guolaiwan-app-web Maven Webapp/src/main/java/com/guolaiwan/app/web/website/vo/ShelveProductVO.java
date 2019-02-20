package com.guolaiwan.app.web.website.vo;

import com.guolaiwan.bussiness.chapman.product.dto.ProductDTO;
import pub.caterpillar.commons.img.ImageUtil;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ShelveProductVO extends AbstractBaseVO<ShelveProductVO, ProductDTO> {

	//标题
	private String title;
	
	//子标题
	private String subTitle;
	
	//摘要
	private String introduction;
	
	//地理位置
	private String position;
	
	//价格
	private Long price;
	
	//图片
	private String image;
	
	public String getTitle() {
		return title;
	}

	public ShelveProductVO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public ShelveProductVO setSubTitle(String subTitle) {
		this.subTitle = subTitle;
		return this;
	}

	public String getIntroduction() {
		return introduction;
	}

	public ShelveProductVO setIntroduction(String introduction) {
		this.introduction = introduction;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public ShelveProductVO setPosition(String position) {
		this.position = position;
		return this;
	}

	public Long getPrice() {
		return price;
	}

	public ShelveProductVO setPrice(Long price) {
		this.price = price;
		return this;
	}
	
	public String getImage() {
		return image;
	}

	public ShelveProductVO setImage(String image) {
		this.image = image;
		return this;
	}

	@Override
	public ShelveProductVO set(ProductDTO entity) throws Exception {
		
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setTitle(entity.getTitle())
			.setSubTitle(entity.getSubTitle())
			.setIntroduction(entity.getIntroduction())
			.setPosition(entity.getPosition())
			.setPrice(entity.getPrice())
			.setImage(ImageUtil.getUrlSchema(entity.getImage()));
		
		return this;
	}

}
