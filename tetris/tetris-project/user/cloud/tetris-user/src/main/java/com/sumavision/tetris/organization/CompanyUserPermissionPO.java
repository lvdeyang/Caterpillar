package com.sumavision.tetris.organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 描述用户隶属公司关系<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月24日 上午9:19:25
 */
@Entity
@Table(name = "TETRIS_COMPANY_USER_PERMISSION")
public class CompanyUserPermissionPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 关联用户id */
	private String userId;
	
	/** 关联公司id */
	private Long companyId;

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "COMPANY_ID")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
}
