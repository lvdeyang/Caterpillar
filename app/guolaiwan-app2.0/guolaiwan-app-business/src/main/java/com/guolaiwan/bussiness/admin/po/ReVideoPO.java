package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity 
@Table(name="t_sys_revideo")
public class ReVideoPO extends AbstractBasePO{
	private static final long serialVersionUID = 1L;
	//名称
	private String name;
	//url地址
	private String slideurl;
	//排序
	private int sort;
	//是否显示
	private int enable;
	//显示公司Id
	private long comId;
	//显示的公司
	private String comName;



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSlideurl() {
		return slideurl;
	}
	public void setSlideurl(String slideurl) {
		this.slideurl = slideurl;
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
