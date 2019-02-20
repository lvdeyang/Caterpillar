package pub.caterpillar.fragment.core.module.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 模块菜单权限表<br/>
 * <p>模块菜单权限表</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午3:59:31
 */
@Entity
@Table(name = AbstractBasePO.PREFIX + "MODULE_MENU_AUTHORITY")
public class ModuleMenuAuthorityPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/** 权限id */
	private Long roleId;
	
	/** 菜单id */
	private Long menuId;

	/** 权限id */
	@Column(name = "ROLE_ID")
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/** 菜单id */
	@Column(name = "MENU_ID")
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}
