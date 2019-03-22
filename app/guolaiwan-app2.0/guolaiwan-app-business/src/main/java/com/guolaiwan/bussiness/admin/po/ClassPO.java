package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_Sys_Class")
public class ClassPO extends AbstractBasePO{
	//分类名称
	private String className;
	//排序
    private int classSort;
    //分组
    private long classGroup;
    
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getClassSort() {
		return classSort;
	}
	public void setClassSort(int classSort) {
		this.classSort = classSort;
	}
	public long getClassGroup() {
		return classGroup;
	}
	public void setClassGroup(long classGroup) {
		this.classGroup = classGroup;
	}
}
