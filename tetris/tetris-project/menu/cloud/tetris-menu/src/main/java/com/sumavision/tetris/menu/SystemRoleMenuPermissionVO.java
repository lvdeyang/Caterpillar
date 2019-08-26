package com.sumavision.tetris.menu;

import java.util.Comparator;
import org.springframework.stereotype.Component;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.mvc.converter.AbstractBaseVO;
import com.sumavision.tetris.system.role.SystemRoleVO;

public class SystemRoleMenuPermissionVO extends AbstractBaseVO<SystemRoleMenuPermissionVO, Object>{

	private String roleId;
	
	private String roleName;
	
	private Long menuId;
	
	private boolean autoGeneration;
	
	public String getRoleId() {
		return roleId;
	}

	public SystemRoleMenuPermissionVO setRoleId(String roleId) {
		this.roleId = roleId;
		return this;
	}

	public String getRoleName() {
		return roleName;
	}

	public SystemRoleMenuPermissionVO setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public Long getMenuId() {
		return menuId;
	}

	public SystemRoleMenuPermissionVO setMenuId(Long menuId) {
		this.menuId = menuId;
		return this;
	}

	public boolean isAutoGeneration() {
		return autoGeneration;
	}

	public SystemRoleMenuPermissionVO setAutoGeneration(boolean autoGeneration) {
		this.autoGeneration = autoGeneration;
		return this;
	}

	@Override
	public SystemRoleMenuPermissionVO set(Object entity) throws Exception {
		return null;
	}
	
	public SystemRoleMenuPermissionVO set(SystemRoleMenuPermissionPO permission, SystemRoleVO role){
		this.setId(permission.getId())
			.setUuid(permission.getUuid())
			.setUpdateTime(permission.getUpdateTime()==null?"":DateUtil.format(permission.getUpdateTime(), DateUtil.dateTimePattern))
			.setMenuId(permission.getMenuId())
			.setAutoGeneration(permission.isAutoGeneration())
			.setRoleId(role.getId())
			.setRoleName(role.getName());
		return this;
	}
	
	@Component
	public static class SystemRoleMenuPermissionComparator implements Comparator<SystemRoleMenuPermissionVO>{

		@Override
		public int compare(SystemRoleMenuPermissionVO o1, SystemRoleMenuPermissionVO o2) {
			return o2.getUpdateTime().compareTo(o1.getUpdateTime());
		}
		
	}
	
}
