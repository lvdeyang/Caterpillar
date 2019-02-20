package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 
 * <p>Title: RoleMenuPO</p>
 * <p>Description: 角色权限关系表</p>
 * <p>Company: guolaiwan</p> 
 * @author Mr.Sun
 * @date 2017年11月21日 上午8:46:37
 */

@Entity
@Table(name = "t_sys_role_menu")
public class RoleMenuPO extends AbstractBasePO {

	// 角色id
	private int roleId;
	// 权限id
	private int menuId;
	//权限fID
	private int menuFid;
	
	public int getMenuFid() {
		return menuFid;
	}
	public void setMenuFid(int menuFid) {
		this.menuFid = menuFid;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	
}
