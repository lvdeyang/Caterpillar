package com.chenxi.web.stock.po;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "stock")
public class StockPo extends AbstractBasePO {
	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
