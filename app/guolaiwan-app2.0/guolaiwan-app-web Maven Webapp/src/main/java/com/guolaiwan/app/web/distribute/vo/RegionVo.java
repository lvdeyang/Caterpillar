package com.guolaiwan.app.web.distribute.vo;

import com.guolaiwan.bussiness.distribute.po.RegionPo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class RegionVo extends AbstractBaseVO<RegionVo,RegionPo> {
	private long parentId;
    private String code;
    private String name;
	
	public long getParentId(){
		return parentId;
	}
	
	public RegionVo setParentId(long parentId) {
		this.parentId = parentId;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public RegionVo setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public String getcode(){
		return code;
	}
	
	public RegionVo setCode(String code)
	{
		this.code = code;
		return this;
	}

	public RegionVo set(RegionPo entity) throws Exception {

		this.setId(entity.getId())
		.setParentId(entity.getParentId())
		.setUuid(entity.getUuid())
		.setName(entity.getName())
		.setCode(entity.getCode());
		return this;
	}
	

}
