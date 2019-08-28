package com.guolaiwan.bussiness.admin.po;



import javax.persistence.Entity;
import javax.persistence.Table;


import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "t_sys_commonproblem")
public class CommonProblemPO extends AbstractBasePO{
   //问题名称
	private String problemName;
	//问题描述
	private  String problemdescribe;	
	//操作图片
	private String problemImg;	
	// 一级模块名称
	private String modularName; 
	// 一级模块 Code
	private Integer  modularCode;	
	// 二级模块名称
	private String modularClassName;
	//二级模块 Code
	private Integer  modularClassCode;
	//是否显示
	private Integer isView;
	//排序
	private Long sort;
	
	public String getProblemName() {
		return problemName;
	}
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	public String getProblemdescribe() {
		return problemdescribe;
	}
	public void setProblemdescribe(String problemdescribe) {
		this.problemdescribe = problemdescribe;
	}
	public String getProblemImg() {
		return problemImg;
	}
	public void setProblemImg(String problemImg) {
		this.problemImg = problemImg;
	}
	public String getModularName() {
		return modularName;
	}
	public void setModularName(String modularName) {
		this.modularName = modularName;
	}
	public Integer getModularCode() {
		return modularCode;
	}
	public void setModularCode(Integer modularCode) {
		this.modularCode = modularCode;
	}
	public String getModularClassName() {
		return modularClassName;
	}
	public void setModularClassName(String modularClassName) {
		this.modularClassName = modularClassName;
	}
	public Integer getModularClassCode() {
		return modularClassCode;
	}
	public void setModularClassCode(Integer modularClassCode) {
		this.modularClassCode = modularClassCode;
	}
	public Integer getIsView() {
		return isView;
	}
	public void setIsView(Integer isView) {
		this.isView = isView;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	
} 
