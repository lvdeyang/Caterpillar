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
	//添加时间
	private String addTime;
	//图片的id
	private long picId;
	//图片的路径
	private String slidepic;
	//投票活动的id
	private long optionId;

	
	public long getOptionId() {
		return optionId;
	}

	public void setOptionId(long optionId) {
		this.optionId = optionId;
	}

	public long getPicId() {
		return picId;
	}

	public void setPicId(long picId) {
		this.picId = picId;
	}

	public String getSlidepic() {
		return slidepic;
	}

	public void setSlidepic(String slidepic) {
		this.slidepic = slidepic;
	}

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
