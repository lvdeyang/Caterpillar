package com.guolaiwan.bussiness.gonghui.po;

import javax.persistence.Entity;
import javax.persistence.Table;


import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "classes")
public class ClassesPo extends AbstractBasePO{

	private String name;
	private String code;
	private int sindex;
	private String pic;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	
	
}
