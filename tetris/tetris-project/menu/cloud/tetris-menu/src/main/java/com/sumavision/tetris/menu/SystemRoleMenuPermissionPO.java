package com.sumavision.tetris.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 菜单权限<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月20日 上午11:55:29
 */
@Entity
@Table(name = "TETRIS_SYSTEM_ROLE_MENU_PERMISSION")
public class SystemRoleMenuPermissionPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 菜单id */
	private Long menuId;
	
	/** 用户角色 */
	private String roleId;
	
	/** 是否自动生成 */
	private boolean autoGeneration;
	
	@Column(name = "MENU_ID")
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Column(name = "ROLE_ID")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "AUTO_GENERATION")
	public boolean isAutoGeneration() {
		return autoGeneration;
	}

	public void setAutoGeneration(boolean autoGeneration) {
		this.autoGeneration = autoGeneration;
	}
	
}
