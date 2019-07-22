package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_votepics")
public class VotePicsPo extends AbstractBasePO {
	
	private static final long serialVersionUID = -7247469068334519056L;
	//投票活动Id
	private long optionId;
	//图片的id
	private long picId;
	//图片的路径
	private String slidepic;
	//排序
	private long ranking;
	
	
	
	public long getRanking() {
		return ranking;
	}
	public void setRanking(long ranking) {
		this.ranking = ranking;
	}
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

	

}
