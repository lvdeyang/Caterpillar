package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.RolePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class RoleVO extends AbstractBaseVO<RoleVO, RolePO> {

	// 角色名
	private String roleName;
	
	// 角色描述
	private String description;
	
	public String getRoleName() {
		return roleName;
	}

	public RoleVO setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public RoleVO setDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public RoleVO set(RolePO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setRoleName(entity.getRoleName())
		.setDescription(entity.getDescription());
		return this;
	}
	
	
}
