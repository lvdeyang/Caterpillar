package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.ReSpecialPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ReSpecialVO extends AbstractBaseVO<ReSpecialVO,ReSpecialPO>{

	//名称
	private String name;
	//图片Id
	private long picId;
	//图片
	private String slidepic;
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
	public ReSpecialVO setName(String name) {
		this.name = name;
		return this;
	}
	public long getPicId() {
		return picId;
	}
	public ReSpecialVO setPicId(long picId) {
		this.picId = picId;
		return this;
	}
	public String getSlidepic() {
		return slidepic;
	}
	public ReSpecialVO setSlidepic(String slidepic) {
		this.slidepic = slidepic;
		return this;
	}
	public String getSlideurl() {
		return slideurl;
	}
	public ReSpecialVO setSlideurl(String slideurl) {
		this.slideurl = slideurl;
		return this;
	}

	public int getSort() {
		return sort;
	}
	public ReSpecialVO setSort(int sort) {
		this.sort = sort;
		return this;
	}
	public int getEnable() {
		return enable;
	}
	public ReSpecialVO setEnable(int enable) {
		this.enable = enable;
		return this;
	}

	public long getComId() {
		return comId;
	}
	public ReSpecialVO setComId(long comId) {
		this.comId = comId;
		return this;
	}
	public String getComName() {
		return comName;
	}
	public ReSpecialVO setComName(String comName) {
		this.comName = comName;
		return this;
	}
	


	public int getReCount() {
		return reCount;
	}
	public ReSpecialVO setReCount(int reCount) {
		this.reCount = reCount;
		return this;
	}
	@Override
	public ReSpecialVO set(ReSpecialPO entity)throws Exception{
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setName(entity.getName())
		.setPicId(entity.getPicId())
		.setSlidepic(entity.getSlidepic())
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
