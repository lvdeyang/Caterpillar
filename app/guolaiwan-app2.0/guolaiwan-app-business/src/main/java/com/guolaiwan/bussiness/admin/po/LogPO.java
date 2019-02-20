package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_log")
public class LogPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	// 权限（名称）
	private int menu;

	// 操作员
	private int operator;
	
	//操作名称operate
	private String operatName;

	//类名称
	private String methodName; 

	//方法名称
	private String className;

	//传入参数
	private String params;

	// ip
	private String ip;

	public int getMenu() {
		return menu;
	}

	public void setMenu(int menu) {
		this.menu = menu;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public String getOperatName() {
		return operatName;
	}

	public void setOperatName(String operatName) {
		this.operatName = operatName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
