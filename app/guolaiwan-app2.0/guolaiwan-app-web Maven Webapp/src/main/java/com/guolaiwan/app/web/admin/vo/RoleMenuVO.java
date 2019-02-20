package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.RoleMenuPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

/**
 * VoV oVo VoV oVo VoV oVo VoV
 * <p>Title: RoleMenuVO</p>
 * <p>Description: 角色权限关系视图对象</p>
 * <p>Company: guolaiwan</p> 
 * @author Mr.Sun
 * @date 2017年11月21日 上午8:50:45
 */
public class RoleMenuVO extends AbstractBaseVO<RoleMenuVO, RoleMenuPO> {

	// 角色id
	private int roleId;
	// 权限id
	private int menuId;
	
	public int getRoleId() {
		return roleId;
	}

	public RoleMenuVO setRoleId(int roleId) {
		this.roleId = roleId;
		return this;
	}

	public int getMenuId() {
		return menuId;
	}

	public RoleMenuVO setMenuId(int menuId) {
		this.menuId = menuId;
		return this;
	}
	

	@Override
	public RoleMenuVO set(RoleMenuPO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setRoleId(entity.getRoleId())
		.setMenuId(entity.getMenuId());
		return this;
	}

}
