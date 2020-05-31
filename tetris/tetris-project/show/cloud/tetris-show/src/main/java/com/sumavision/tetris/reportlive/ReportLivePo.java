package com.sumavision.tetris.reportlive;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.sumavision.tetris.orm.po.AbstractBasePO;

@Entity
@Table(name = "TETRIS_REPORTLIVE")
public class ReportLivePo extends AbstractBasePO{

	private String name;
	private String moniterIds;
	private int cinterval;
	private int isOld=0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoniterIds() {
		return moniterIds;
	}
	public void setMoniterIds(String moniterIds) {
		this.moniterIds = moniterIds;
	}
	public int getCinterval() {
		return cinterval;
	}
	public void setCinterval(int cinterval) {
		this.cinterval = cinterval;
	}
	public int getIsOld() {
		return isOld;
	}
	public void setIsOld(int isOld) {
		this.isOld = isOld;
	}
	
	
	
	
}
