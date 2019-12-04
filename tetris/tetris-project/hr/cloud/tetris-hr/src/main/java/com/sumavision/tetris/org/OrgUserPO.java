package com.sumavision.tetris.org;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 菜单数据，包含父菜单和子菜单<br/>
 * <p>详细描述</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月20日 上午10:41:49
 */
@Entity
@Table(name="TETRIS_ORG_USER")
public class OrgUserPO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;
	
	private long userId;
	
	private long orgId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	
	
	
	
}
