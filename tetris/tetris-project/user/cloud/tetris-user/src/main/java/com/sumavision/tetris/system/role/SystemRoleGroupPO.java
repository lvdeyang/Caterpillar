package com.sumavision.tetris.system.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 系统角色组<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月15日 下午4:44:16
 */
@Entity
@Table(name = "TETRIS_USER_SYSTEM_ROLE_GROUP")
public class SystemRoleGroupPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/** 权限组名称 */
	private String name;
	
	/** 是否是自动生成的 */
	private boolean autoGeneration;

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

}
