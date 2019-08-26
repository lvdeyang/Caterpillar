package com.sumavision.tetris.business.role.event;

import org.springframework.context.ApplicationEvent;

public class BusinessRoleRemovedEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	
	/** 业务角色id */
	private Long roleId;
	
	/** 业务角色名称 */
	private String roleName;
	
	/** 企业id */
	private String companyId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public BusinessRoleRemovedEvent(
			Object source,
			Long roleId,
			String roleName,
			String companyId) {
		super(source);
		this.roleId = roleId;
		this.roleName = roleName;
		this.companyId = companyId;
	}
	
}
