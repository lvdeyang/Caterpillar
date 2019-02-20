package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.enumeration.RecommendType;
import com.guolaiwan.bussiness.admin.po.CompanyRelPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class CompanyRelVO extends AbstractBaseVO<CompanyRelVO, CompanyRelPO> {


	//分公司ID
	private Long sonComId;
	//轮播图，视频，特别推荐id
	private Long relaId;
	//关联的类型
	private String rType;
	
	
	
	




	public Long getSonComId() {
		return sonComId;
	}








	public CompanyRelVO setSonComId(Long sonComId) {
		this.sonComId = sonComId;
		return this;
	}








	public Long getRelaId() {
		return relaId;
	}








	public CompanyRelVO setRelaId(Long relaId) {
		this.relaId = relaId;
		return this;
	}








	public String getrType() {
		return rType;
	}








	public CompanyRelVO setrType(String rType) {
		this.rType = rType;
		return this;
	}








	@Override
	public CompanyRelVO set(CompanyRelPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setRelaId(entity.getRelaId())
		.setrType(entity.getrType().getName())
		.setSonComId(entity.getSonComId());
		return this;
	}

}
