package com.sumavision.tetris.system.role;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.user.UserPO;

public class UserSystemRolePermissionVO {

	private Long id;
	
	private String uuid;
	
	private String updateTime;
	
	private Long roleId;
	
	private String roleName;
	
	private Long userId;
	
	private String nickname;
	
	private boolean autoGeneration;

	public Long getId() {
		return id;
	}

	public UserSystemRolePermissionVO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public UserSystemRolePermissionVO setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public UserSystemRolePermissionVO setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Long getRoleId() {
		return roleId;
	}

	public UserSystemRolePermissionVO setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

	public String getRoleName() {
		return roleName;
	}

	public UserSystemRolePermissionVO setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public UserSystemRolePermissionVO setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public String getNickname() {
		return nickname;
	}

	public UserSystemRolePermissionVO setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public boolean isAutoGeneration() {
		return autoGeneration;
	}

	public UserSystemRolePermissionVO setAutoGeneration(boolean autoGeneration) {
		this.autoGeneration = autoGeneration;
		return this;
	}
	
	public UserSystemRolePermissionVO set(UserSystemRolePermissionPO permission, SystemRolePO role){
		this.setId(permission.getId())
			.setUuid(permission.getUuid())
			.setUpdateTime(permission.getUpdateTime()==null?"":DateUtil.format(permission.getUpdateTime(), DateUtil.dateTimePattern))
			.setAutoGeneration(permission.isAutoGeneration())
			.setUserId(permission.getUserId())
			.setRoleId(role.getId())
			.setRoleName(role.getName());
		return this;
	}
	
	public UserSystemRolePermissionVO set(UserSystemRolePermissionPO permission, UserPO user){
		this.setId(permission.getId())
			.setUuid(permission.getUuid())
			.setUpdateTime(permission.getUpdateTime()==null?"":DateUtil.format(permission.getUpdateTime(), DateUtil.dateTimePattern))
			.setAutoGeneration(permission.isAutoGeneration())
			.setRoleId(permission.getRoleId())
			.setUserId(user.getId())
			.setNickname(user.getNickname());
		return this;
	}
	
}
