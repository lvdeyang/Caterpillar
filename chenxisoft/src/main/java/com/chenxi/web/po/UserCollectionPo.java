package com.chenxi.web.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.chenxi.web.classes.Moudular;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "collection")
public class UserCollectionPo extends AbstractBasePO {

	private long userId;
	private long contentId;
	private Moudular moudular;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getContentId() {
		return contentId;
	}
	public void setContentId(long contentId) {
		this.contentId = contentId;
	}
	@Enumerated(EnumType.STRING)
	public Moudular getMoudular() {
		return moudular;
	}
	public void setMoudular(Moudular moudular) {
		this.moudular = moudular;
	}
	
}
