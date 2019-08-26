package com.sumavision.tetris.organization;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.user.UserPO;

public class OrganizationUserPermissionVO {

	private Long id;
	
	private String uuid;
	
	private String updateTime;
	
	private Long organizationId;
	
	private String organizationName;
	
	private String userId;
	
	private String nickname;

	public Long getId() {
		return id;
	}

	public OrganizationUserPermissionVO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public OrganizationUserPermissionVO setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public OrganizationUserPermissionVO setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public OrganizationUserPermissionVO setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
		return this;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public OrganizationUserPermissionVO setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public OrganizationUserPermissionVO setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getNickname() {
		return nickname;
	}

	public OrganizationUserPermissionVO setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public OrganizationUserPermissionVO set(OrganizationUserPermissionPO permission, OrganizationPO organization){
		this.setId(permission.getId())
			.setUuid(permission.getUuid())
			.setUpdateTime(permission.getUpdateTime()==null?"":DateUtil.format(permission.getUpdateTime(), DateUtil.dateTimePattern))
			.setOrganizationId(organization.getId())
			.setOrganizationName(organization.getName())
			.setUserId(permission.getUserId());
		return this;
	}
	
	public OrganizationUserPermissionVO set(OrganizationUserPermissionPO permission, UserPO user){
		this.setId(permission.getId())
			.setUuid(permission.getUuid())
			.setUpdateTime(permission.getUpdateTime()==null?"":DateUtil.format(permission.getUpdateTime(), DateUtil.dateTimePattern))
			.setOrganizationId(permission.getOrganizationId())
			.setUserId(user.getId().toString())
			.setNickname(user.getNickname());
		return this;
	}
	
}
