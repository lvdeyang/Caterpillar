package com.sumavision.tetris.organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 描述用户隶属部门关系<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月24日 上午9:16:37
 */
@Entity
@Table(name = "TETRIS_ORGANIZATION_USER_PERMISSION")
public class OrganizationUserPermissionPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/** 关联用户id */
	private String userId;
	
	/** 关联部门id */
	private Long organizationId;

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ORGANIZATION_ID")
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
}
