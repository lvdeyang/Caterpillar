package com.sumavision.tetris.org;

import java.util.List;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

public class OrgVO extends AbstractBaseVO<OrgVO, OrgPO>{

	private String name;
	private Long parentId;
	private List<OrgVO> subOrgs;
	
	public String getName() {
		return name;
	}

	public OrgVO setName(String name) {
		this.name = name;
		return this;
	}

	public Long getParentId() {
		return parentId;
	}

	public OrgVO setParentId(Long parentId) {
		this.parentId = parentId;
		return this;
	}
	
	public List<OrgVO> getSubOrgs() {
		return subOrgs;
	}

	public void setSubOrgs(List<OrgVO> subOrgs) {
		this.subOrgs = subOrgs;
	}

	@Override
	public OrgVO set(OrgPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(entity.getUpdateTime()==null?"":DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getName())
			.setParentId(entity.getParentId());
		return this;
	}

}
