package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.BewritePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class BewriteVO  extends AbstractBaseVO<BewriteVO, BewritePO>{

	//url
	private String url; 
	//描述
	private String miaoshu;
	//图片Id
	private long imgId;
	public String getUrl() {
		return url;
	}
	public BewriteVO setUrl(String url) {
		this.url = url;
		return this;
	}
	public String getMiaoshu() {
		return miaoshu;
	}
	public BewriteVO setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
		return this;
	}
	
	public long getImgId() {
		return imgId;
	}
	public void setImgId(long imgId) {
		this.imgId = imgId;
	}
	@Override
	public BewriteVO set(BewritePO entity)throws Exception{
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUrl(entity.getUrl())
		.setMiaoshu(entity.getMiaoshu())
		.setImgId(entity.getImgId());
		return this;
	}
	

}
