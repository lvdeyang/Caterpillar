package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.LogPO;
import com.guolaiwan.bussiness.admin.po.RolePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LogVO extends AbstractBaseVO<LogVO, LogPO> {

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



	public LogVO setMenu(int menu) {
		this.menu = menu;
		return this;
	}



	public int getOperator() {
		return operator;
	}



	public LogVO setOperator(int operator) {
		this.operator = operator;
		return this;
	}



	public String getOperatName() {
		return operatName;
	}



	public LogVO setOperatName(String operatName) {
		this.operatName = operatName;
		return this;
	}



	public String getMethodName() {
		return methodName;
	}



	public LogVO setMethodName(String methodName) {
		this.methodName = methodName;
		return this;
	}



	public String getClassName() {
		return className;
	}



	public LogVO setClassName(String className) {
		this.className = className;
		return this;
	}



	public String getParams() {
		return params;
	}



	public LogVO setParams(String params) {
		this.params = params;
		return this;
	}



	public String getIp() {
		return ip;
	}



	public LogVO setIp(String ip) {
		this.ip = ip;
		return this;
	}

	
	@Override
	public LogVO set(LogPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setMenu(entity.getMenu())
		.setOperator(entity.getOperator())
		.setOperatName(entity.getOperatName())
		.setMethodName(entity.getClassName())
		.setClassName(entity.getClassName())
		.setParams(entity.getParams())
		.setIp(entity.getIp());
		return this;
	}


}
