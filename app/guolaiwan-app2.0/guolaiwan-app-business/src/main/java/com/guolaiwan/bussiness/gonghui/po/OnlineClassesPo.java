package com.guolaiwan.bussiness.gonghui.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "onlineclasses")
public class OnlineClassesPo extends AbstractBasePO{
    private long contentId;
    private String contentName;
    private long classesId;
	public long getContentId() {
		return contentId;
	}
	public void setContentId(long contentId) {
		this.contentId = contentId;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	
	public long getClassesId() {
		return classesId;
	}
	public void setClassesId(long classesId) {
		this.classesId = classesId;
	}
    
}
