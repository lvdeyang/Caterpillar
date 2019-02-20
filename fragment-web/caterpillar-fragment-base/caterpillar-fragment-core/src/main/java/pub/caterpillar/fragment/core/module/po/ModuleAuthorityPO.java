package pub.caterpillar.fragment.core.module.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 模块权限表<br/>
 * <p>模块权限表</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午3:56:40
 */
@Entity
@Table(name = AbstractBasePO.PREFIX + "MODULE_AUTHORITY")
public class ModuleAuthorityPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/**  权限id */
	private Long roleId;
	
	/**  模块id */
	private Long moduleId;

	/**  权限id */
	@Column(name = "ROLE_ID")
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**  模块id */
	@Column(name = "MODULE_ID")
	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

}
