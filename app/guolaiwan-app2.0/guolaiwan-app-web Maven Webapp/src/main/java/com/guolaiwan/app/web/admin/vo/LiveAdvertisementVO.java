package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.LiveAdvertisementPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LiveAdvertisementVO extends AbstractBaseVO<LiveAdvertisementVO,LiveAdvertisementPO>{

	//名称
	private String name;
	//图片Id
	private long picId;
	//图片
	private String slidepic;
	//排序
	private int sort;
	//是否启用
	private int enable;

	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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



	public int getEnable() {
		return enable;
	}



	public void setEnable(int enable) {
		this.enable = enable;
	}



	@Override
	public LiveAdvertisementVO set(LiveAdvertisementPO entity) throws Exception {
		this.setId(entity.getId());
		this.setEnable(entity.getEnable());
		this.setName(entity.getName());
		this.setPicId(entity.getPicId());
		this.setSlidepic(entity.getSlidepic());
		this.setSort(entity.getSort());
		this.setUpdateTime(entity.getUpdateTime());
		this.setUuid(entity.getUuid());
		return this;
	}



	



}