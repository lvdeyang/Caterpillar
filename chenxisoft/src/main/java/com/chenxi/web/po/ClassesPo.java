package com.chenxi.web.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.chenxi.web.classes.Moudular;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "classes")
public class ClassesPo extends AbstractBasePO{

	private String name;
	private String code;
	private int sindex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getSindex() {
		return sindex;
	}
	public void setSindex(int sindex) {
		this.sindex = sindex;
	}
	
	
	
}
