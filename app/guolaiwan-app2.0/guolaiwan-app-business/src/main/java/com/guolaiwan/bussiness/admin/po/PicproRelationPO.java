package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_sys_picpro_relation ")
public class PicproRelationPO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;
	//子产品id
	private long childProID;
	//图片id
	private long childPicID;
	public long getChildProID() {
		return childProID;
	}
	public void setChildProID(long childProID) {
		this.childProID = childProID;
	}
	public long getChildPicID() {
		return childPicID;
	}
	public void setChildPicID(long childPicID) {
		this.childPicID = childPicID;
	}
	

}
