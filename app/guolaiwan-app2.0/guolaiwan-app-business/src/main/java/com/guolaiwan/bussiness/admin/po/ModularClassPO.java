package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_Sys_Modular_Class")
public class ModularClassPO extends AbstractBasePO {
	private static final long serialVersionUID = 1L;
	//分类标识
	private String classCode;
	//绑定模块标识
	private String classmodularCode;

	// 分类名称
	private String className;

	//是否显示
	private int classIsv;
	//排序
	private int classSort;
	//公司Id
	private long comId;
	//公司名
	private String comName;


	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassmodularCode() {
		return classmodularCode;
	}
	public void setClassmodularCode(String classmodularCode) {
		this.classmodularCode = classmodularCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getClassIsv() {
		return classIsv;
	}
	public void setClassIsv(int classIsv) {
		this.classIsv = classIsv;
	}
	public int getClassSort() {
		return classSort;
	}
	public void setClassSort(int classSort) {
		this.classSort = classSort;
	}
	public long getComId() {
		return comId;
	}
	public void setComId(long comId) {
		this.comId = comId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	
}