package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.ModularClassPO;


import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ModularClassVO extends AbstractBaseVO<ModularClassVO, ModularClassPO>{

	//分类标识
	private 	String classCode;

	//绑定模块
	private  String classmodularCode;
	//分类名称
	private String className;
	//是否显示
	private int classIsv;
	//排序
	private int classSort;
	//是否显示
	private long comId;
	//排序
	private String comName;
	public String getClassCode() {
		return classCode;
	}
	public ModularClassVO setClassCode(String classCode) {
		this.classCode = classCode;
		return this;
	}
	public String getClassmodularCode() {
		return classmodularCode;
	}
	public ModularClassVO setClassmodularCode(String classmodularCode) {
		this.classmodularCode = classmodularCode;
		return this;
	}
	public String getClassName() {
		return className;
	}
	public ModularClassVO setClassName(String className) {
		this.className = className;
		return this;
	}
	public int getClassIsv() {
		return classIsv;
	}
	public ModularClassVO setClassIsv(int classIsv) {
		this.classIsv = classIsv;
		return this;
	}
	public int getClassSort() {
		return classSort;
	}
	public ModularClassVO setClassSort(int classSort) {
		this.classSort = classSort;
		return this;
	}
	
	public long getComId() {
		return comId;
	}
	public ModularClassVO setComId(long comId) {
		this.comId = comId;
		return this;
	}
	public String getComName() {
		return comName;
	}
	public ModularClassVO setComName(String comName) {
		this.comName = comName;
		return this;
	}
	
	@Override
	public ModularClassVO set(ModularClassPO entity)throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setClassCode(entity.getClassCode())
		.setClassmodularCode(entity.getClassmodularCode())
		.setClassName(entity.getClassName())
		.setClassIsv(entity.getClassIsv())
		.setClassSort(entity.getClassSort())
		.setComId(entity.getComId())
		.setComName(entity.getComName());
		return this;

	}
	






}
