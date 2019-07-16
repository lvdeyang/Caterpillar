package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;


@Entity 
@Table(name="t_sys_votemodular")
public class VoteModularPO extends AbstractBasePO{

	private static final long serialVersionUID = -2634019281334112353L;
	//头标签名称
	private String modularName;
	//标签描述
	private String modularDescribe;
	
	private String addTime;


	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getModularName() {
		return modularName;
	}

	public void setModularName(String modularName) {
		this.modularName = modularName;
	}

	public String getModularDescribe() {
		return modularDescribe;
	}

	public void setModularDescribe(String modularDescribe) {
		this.modularDescribe = modularDescribe;
	}
	

}
