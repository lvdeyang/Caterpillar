package com.guolaiwan.app.web.website.basket.vo;

import com.guolaiwan.bussiness.user.dto.BasketDTO;
import pub.caterpillar.commons.img.ImageUtil;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class BasketVO extends AbstractBaseVO<BasketVO, BasketDTO> {

	//购物车条目id
	private Long id;
	
	//购物车条目编辑时间
	private String updateTime;
	
	//商品数量
	private int num;
	
	//商品描述
	private String introduction;
	
	//商品单价
	private Long price;
	
	//当前购物车条目总价
	private Long count;
	
	//当前商品缩略图
	private String image;
	
	public Long getId() {
		return id;
	}

	public BasketVO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public BasketVO setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public int getNum() {
		return num;
	}

	public BasketVO setNum(int num) {
		this.num = num;
		return this;
	}

	public String getIntroduction() {
		return introduction;
	}

	public BasketVO setIntroduction(String introduction) {
		this.introduction = introduction;
		return this;
	}

	public Long getPrice() {
		return price;
	}

	public BasketVO setPrice(Long price) {
		this.price = price;
		return this;
	}

	public Long getCount() {
		return count;
	}

	public BasketVO setCount(Long count) {
		this.count = count;
		return this;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public BasketVO set(BasketDTO entity) throws Exception {
		this.setId(entity.getId())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setNum(entity.getNum())
			.setPrice(entity.getPrice())
			.setCount(entity.getCount())
			.setIntroduction(entity.getIntroduction())
			.setImage(ImageUtil.getUrlSchema(entity.getImage()));
		return this;
	}

}
