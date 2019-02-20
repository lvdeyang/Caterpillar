package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.ReVideoPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ReVideoVO extends AbstractBaseVO<ReVideoVO,ReVideoPO>{

	//名称
	private String name;

	//url地址
	private String slideurl;

	//排序
	private int sort;
	//是否启用
	private int enable;

	//排序
	private long comId;
	//是否启用
	private String comName;

	//是否启用
	private int reCount;




	public String getName() {
		return name;
	}
	public ReVideoVO setName(String name) {
		this.name = name;
		return this;
	}
	public String getSlideurl() {
		return slideurl;
	}
	public ReVideoVO setSlideurl(String slideurl) {
		this.slideurl = slideurl;
		return this;
	}

	public int getSort() {
		return sort;
	}
	public ReVideoVO setSort(int sort) {
		this.sort = sort;
		return this;
	}
	public int getEnable() {
		return enable;
	}
	public ReVideoVO setEnable(int enable) {
		this.enable = enable;
		return this;
	}

	public long getComId() {
		return comId;
	}
	public ReVideoVO setComId(long comId) {
		this.comId = comId;
		return this;
	}
	public String getComName() {
		return comName;
	}
	public ReVideoVO setComName(String comName) {
		this.comName = comName;
		return this;
	}
	


	public int getReCount() {
		return reCount;
	}
	public ReVideoVO setReCount(int reCount) {
		this.reCount = reCount;
		return this;
	}
	@Override
	public ReVideoVO set(ReVideoPO entity)throws Exception{
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setName(entity.getName())
		.setSlideurl(entity.getSlideurl())
		.setSort(entity.getSort())
		.setEnable(entity.getEnable())
		.setComId(entity.getComId())
		.setComName(entity.getComName());
		if(entity.getComId()==1l){
			this.setComName("总公司的首页");
		}else{
			this.setComName(entity.getComName()+"首页");
		}
		return this;

	}
}
