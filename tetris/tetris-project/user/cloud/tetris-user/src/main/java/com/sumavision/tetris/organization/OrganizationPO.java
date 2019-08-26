package com.sumavision.tetris.organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 部门信息<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月24日 上午9:12:29
 */
@Entity
@Table(name = "TETRIS_ORGANIZATION")
public class OrganizationPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/** 角色名称 */
	private String name;
	
	/** 隶属组织 */
	private Long companyId;
	
	/** 显示排序 */
	private int serial;
	
	/** 上级部门id */
	private Long parentId;
	
	/** 上级部门id路径：/id/id/id */
	private String parentPath;
	
	/** 标识树的深度 */
	private int level;
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "COMPANY_ID")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "SERIAL")
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	@Column(name = "PARENT_ID")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "PARENT_PATH")
	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	@Column(name = "LEVEL")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
