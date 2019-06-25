package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Sort;

import com.guolaiwan.bussiness.admin.enumeration.ModularType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_Sys_Modular")
public class ModularPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	//标识
	private String modularCode;

	// 模块名称
	private String modularName;

	//是否显示
	private int modularIsv;

	// 模块图片
	private String modularPic;

	// 排序
	private long sort;

	// 模块图片
	private long  comId;

	// 模块图片
	private String comName;
	
	// 排序显示
	private long sortindex;
    
	
	public String getModularCode() {
		return modularCode;
		
	}
	
	public long getSortindex() {
		return sortindex;
	}

	public void setSortindex(long sortindex) {
		this.sortindex = sortindex;
	}

	public void setModularCode(String modularCode) {
		this.modularCode = modularCode;
	}

	public String getModularName() {
		return modularName;
	}

	public void setModularName(String modularName) {
		this.modularName = modularName;
	}

	public int getModularIsv() {
		return modularIsv;
	}

	public void setModularIsv(int modularIsv) {
		this.modularIsv = modularIsv;
	}

	public String getModularPic() {
		return modularPic;
	}

	public void setModularPic(String modularPic) {
		this.modularPic = modularPic;
	}

	public long getSort() {
		return sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
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
