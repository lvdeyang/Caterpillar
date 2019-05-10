package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;


@Entity 
@Table(name="t_sys_livegift")
public class LiveGiftPO extends AbstractBasePO{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private long price;
	
	private long picId;
	
	private String slidepic;
	
	private int sort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getPicId() {
		return picId;
	}

	public void setPicId(long picId) {
		this.picId = picId;
	}

	public String getSlidepic() {
		return slidepic;
	}

	public void setSlidepic(String slidepic) {
		this.slidepic = slidepic;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	

}
