package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.CarouselPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class CarouselVO extends AbstractBaseVO<CarouselVO,CarouselPO>{

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

	private long productId;//关联的商品id
	
	private String productName;//关联的商品名称

	private long merchantId;
	
	private String classify;
	
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public String getProductName() {
		return productName;
	}
	public CarouselVO setProductName(String productName) {
		this.productName = productName;
		return this;
	}
	public long getProductId() {
		return productId;
	}
	public CarouselVO setProductId(long productId) {
		this.productId = productId;
		return this;
	}

	public String getName() {
		return name;
	}
	public CarouselVO setName(String name) {
		this.name = name;
		return this;
	}
	public long getPicId() {
		return picId;
	}
	public CarouselVO setPicId(long picId) {
		this.picId = picId;
		return this;
	}
	public String getSlidepic() {
		return slidepic;
	}
	public CarouselVO setSlidepic(String slidepic) {
		this.slidepic = slidepic;
		return this;
	}
	public String getSlideurl() {
		return slideurl;
	}
	public CarouselVO setSlideurl(String slideurl) {
		this.slideurl = slideurl;
		return this;
	}

	public int getSort() {
		return sort;
	}
	public CarouselVO setSort(int sort) {
		this.sort = sort;
		return this;
	}
	public int getEnable() {
		return enable;
	}
	public CarouselVO setEnable(int enable) {
		this.enable = enable;
		return this;
	}

	public long getComId() {
		return comId;
	}
	public CarouselVO setComId(long comId) {
		this.comId = comId;
		return this;
	}
	public String getComName() {
		return comName;
	}
	public CarouselVO setComName(String comName) {
		this.comName = comName;
		return this;
	}
	public String getClassify() {
		return classify;
	}
	public CarouselVO setClassify(String classify) {
		this.classify = classify;
		return this;
	}	


	public int getReCount() {
		return reCount;
	}
	public CarouselVO setReCount(int reCount) {
		this.reCount = reCount;
		return this;
	}
	@Override
	public CarouselVO set(CarouselPO entity)throws Exception{
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setName(entity.getName())
		.setPicId(entity.getPicId())
		.setSlidepic(entity.getSlidepic())
		.setSlideurl(entity.getSlideurl())
		.setSort(entity.getSort())
		.setEnable(entity.getEnable())
		.setComId(entity.getComId())
		.setProductId(entity.getProductId())
		.setProductName(entity.getProductName())
		.setComName(entity.getComName())
		.setClassify(entity.getClassify());
		if(entity.getComId()==1l){
			this.setComName("总公司的首页");
		}else{
			this.setComName(entity.getComName()+"首页");
		}
		return this;

	}
}