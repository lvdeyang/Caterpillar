package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.ColumnType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_column")
public class ColumnPO extends AbstractBasePO {

	private static final long serialVersionUID = 8303338361362266412L;

	private long merchantId;//商品id

	private ColumnType type;//推荐类型
	
	private String code;//模块编码
    
	private long sortindex;// 排序显示
	
	
	public long getSortindex() {
		return sortindex;
	}

	public void setSortindex(long sortindex) {
		this.sortindex = sortindex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	@Enumerated(EnumType.STRING)
	public ColumnType getType() {
		return type;
	}

	public void setType(ColumnType type) {
		this.type = type;
	}

}