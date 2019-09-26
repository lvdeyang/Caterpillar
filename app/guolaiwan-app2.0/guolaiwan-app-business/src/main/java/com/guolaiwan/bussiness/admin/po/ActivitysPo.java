package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_activitys")

public class ActivitysPo extends AbstractBasePO {

	// 活动名称
	private String name;
	// 规则图片
	private long rulepicId;
	// 活动文案
	private String rulepic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getRulepicId() {
		return rulepicId;
	}

	public void setRulepicId(long rulepicId) {
		this.rulepicId = rulepicId;
	}

	public String getRulepic() {
		return rulepic;
	}

	public void setRulepic(String rulepic) {
		this.rulepic = rulepic;
	}

}
