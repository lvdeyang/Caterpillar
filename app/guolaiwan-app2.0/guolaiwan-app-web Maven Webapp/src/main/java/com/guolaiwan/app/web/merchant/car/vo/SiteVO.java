package com.guolaiwan.app.web.merchant.car.vo;

import java.util.Iterator;
import java.util.List;

import com.guolaiwan.bussiness.merchant.car.po.RoutePO;
import com.guolaiwan.bussiness.merchant.car.po.SitePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class SiteVO extends AbstractBaseVO<SiteVO, SitePO>{
	//路线名称
	private String name;
	//价格
	private Long price;

	public String getName() {
		return name;
	}

	public SiteVO setName(String name) {
		this.name = name;
		return this;
	}

	public Long getPrice() {
		return price;
	}

	public SiteVO setPrice(Long price) {
		this.price = price;
		return this;
	}




	public SiteVO set(SitePO entity)throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setName(entity.getName())
		.setPrice(entity.getPrice());
		return this;
	}

	

}
