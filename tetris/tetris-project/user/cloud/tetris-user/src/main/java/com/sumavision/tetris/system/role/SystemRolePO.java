package com.sumavision.tetris.system.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 系统角色<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月15日 下午4:47:20
 */
@Entity
@Table(name = "TETRIS_USER_SYSTEM_ROLE")
public class SystemRolePO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/** 角色名 */
	private String name;
	
	/** 是否是自动生成的 */
	private boolean autoGeneration;

	/** 隶属系统角色组 */
	private Long systemRoleGroupId;
	
	/** 角色类型 */
	private SystemRoleType type;
	
	/** 业务角色隶属企业id */
	private Long companyId;

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "AUTO_GENERATION")
	public boolean isAutoGeneration() {
		return autoGeneration;
	}

	public void setAutoGeneration(boolean autoGeneration) {
		this.autoGeneration = autoGeneration;
	}

	@Column(name = "SYSTEM_ROLE_GROUP_ID")
	public Long getSystemRoleGroupId() {
		return systemRoleGroupId;
	}

	public void setSystemRoleGroupId(Long systemRoleGroupId) {
		this.systemRoleGroupId = systemRoleGroupId;
	}

	@Enumerated(value = EnumType.STRING)
	@Column(name = "TYPE")
	public SystemRoleType getType() {
		return type;
	}

	public void setType(SystemRoleType type) {
		this.type = type;
	}

	@Column(name = "COMPANY_ID")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
}
