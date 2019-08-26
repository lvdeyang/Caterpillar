package com.sumavision.tetris.system.role;

import java.util.List;

/**
 * 系统角色分组<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月17日 上午11:45:07
 */
public class SystemRoleGroupVO {

	private Long id;
	
	private String uuid;
	
	private String updateTime;
	
	private String name;
	
	private boolean autoGeneration;
	
	private boolean isGroup = true;
	
	private List<SystemRoleVO> roles;

	public Long getId() {
		return id;
	}

	public SystemRoleGroupVO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public SystemRoleGroupVO setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public SystemRoleGroupVO setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getName() {
		return name;
	}

	public SystemRoleGroupVO setName(String name) {
		this.name = name;
		return this;
	}

	public boolean isAutoGeneration() {
		return autoGeneration;
	}

	public SystemRoleGroupVO setAutoGeneration(boolean autoGeneration) {
		this.autoGeneration = autoGeneration;
		return this;
	}
	
	public boolean getIsGroup() {
		return isGroup;
	}

	public List<SystemRoleVO> getRoles() {
		return roles;
	}

	public SystemRoleGroupVO setRoles(List<SystemRoleVO> roles) {
		this.roles = roles;
		return this;
	}

}
