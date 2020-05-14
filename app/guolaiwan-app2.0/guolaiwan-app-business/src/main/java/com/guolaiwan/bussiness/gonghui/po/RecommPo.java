package com.guolaiwan.bussiness.gonghui.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.guolaiwan.bussiness.gonghui.enumeration.RecomType;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "recomm")
public class RecommPo extends AbstractBasePO{

	private long contentId;
	private long isrecomm;
	private String contentName;
	private RecomType type;
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
	
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	@Enumerated(EnumType.STRING)
	public RecomType getType() {
		return type;
	}
	public void setType(RecomType type) {
		this.type = type;
	}
	
	
}
