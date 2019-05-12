package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.LiveGiftPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LiveGiftVO extends AbstractBaseVO<LiveGiftVO,LiveGiftPO>{

	private String name;
	
	private int price;
	
	private long picId;
	
	private String slidepic;
	
	private int sort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getPicId() {
		return picId;
	}

	public void setPicId(long picId) {
		this.picId = picId;
	}

	public String getSlidepic() {
		return slidepic;
	}

	public void setSlidepic(String slidepic) {
		this.slidepic = slidepic;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public LiveGiftVO set(LiveGiftPO entity) throws Exception {
		this.setId(entity.getId());
		this.setName(entity.getName());
		this.setPicId(entity.getPicId());
		this.setPrice(entity.getPrice());
		this.setSlidepic(entity.getSlidepic());
		this.setSort(entity.getSort());
		this.setUpdateTime(entity.getUpdateTime());
		this.setUuid(entity.getUuid());
		return this;
	}



	



}