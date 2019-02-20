package com.guolaiwan.bussiness.user.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 用户操作详情
 * lvdeyang 2017年6月22日
 */
@Entity
@Table(name = "t_app_operation_detail")
public class OperationDetailPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//详情
	private String detail;
	
	//操作类型
	private String type;
	
	//隶属用户
	private UserPO user;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}
	
}
