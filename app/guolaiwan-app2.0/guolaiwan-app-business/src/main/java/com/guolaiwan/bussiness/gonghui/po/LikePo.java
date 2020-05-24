package com.guolaiwan.bussiness.gonghui.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.guolaiwan.bussiness.gonghui.enumeration.LikeType;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "likePo")
public class LikePo extends AbstractBasePO {
	private long contentId;
	private long userId;
	private LikeType type;
	public long getContentId() {
		return contentId;
	}
	public void setContentId(long contentId) {
		this.contentId = contentId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Enumerated(EnumType.STRING)
	public LikeType getType() {
		return type;
	}
	public void setType(LikeType type) {
		this.type = type;
	}
	
}
