package com.chenxi.web.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.chenxi.web.classes.Moudular;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "recomm")
public class RecommPo extends AbstractBasePO{

	private long contentId;
	private long isrecomm;
	private Moudular moudular;
	public long getContentId() {
		return contentId;
	}
	public void setContentId(long contentId) {
		this.contentId = contentId;
	}
	public long getIsrecomm() {
		return isrecomm;
	}
	public void setIsrecomm(long isrecomm) {
		this.isrecomm = isrecomm;
	}
	@Enumerated(EnumType.STRING)
	public Moudular getMoudular() {
		return moudular;
	}
	public void setMoudular(Moudular moudular) {
		this.moudular = moudular;
	}
	
}
