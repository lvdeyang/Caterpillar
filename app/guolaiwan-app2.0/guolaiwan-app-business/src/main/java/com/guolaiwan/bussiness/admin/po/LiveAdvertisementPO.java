package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;


@Entity 
@Table(name="t_sys_liveadvertisement")
public class LiveAdvertisementPO extends AbstractBasePO{
	private static final long serialVersionUID = 1L;
	//图片
	private String name;
	//图片Id
	private long picId;
	//图片
	private String slidepic;
	//排序
	private int sort;
	//是否显示
	private int enable;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	

}
