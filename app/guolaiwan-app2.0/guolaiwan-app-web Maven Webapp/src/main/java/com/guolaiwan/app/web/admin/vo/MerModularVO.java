package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.MerModularPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class MerModularVO extends AbstractBaseVO<MerModularVO, MerModularPO> {

	//名称
	private String name;
	//是否启用
	private int isv;
	//级别
	private int level;
	//所属分类
	private long parentId;
	//所属分类名称
	private String parentName;
	
	

	public String getName() {
		return name;
	}



	public MerModularVO setName(String name) {
		this.name = name;
		return this;
	}



	public int getIsv() {
		return isv;
	}



	public MerModularVO setIsv(int isv) {
		this.isv = isv;
		return this;
	}



	public int getLevel() {
		return level;
	}



	public MerModularVO setLevel(int level) {
		this.level = level;
		return this;
	}



	public long getParentId() {
		return parentId;
	}



	public MerModularVO setParentId(long parentId) {
		this.parentId = parentId;
		return this;
	}



	public String getParentName() {
		return parentName;
	}



	public MerModularVO setParentName(String parentName) {
		this.parentName = parentName;
		return this;
	}



	@Override
	public MerModularVO set(MerModularPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setName(entity.getName())
		.setIsv(entity.getIsv())
		.setLevel(entity.getLevel())
		.setParentId(entity.getParentId())
		.setParentName(entity.getParentName());
		return this;
	}

}
