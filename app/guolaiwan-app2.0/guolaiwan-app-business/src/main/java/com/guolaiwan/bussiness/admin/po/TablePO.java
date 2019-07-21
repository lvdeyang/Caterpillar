package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.TableStatus;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_sys_table")
public class TablePO extends AbstractBasePO {
	/*
	 * 订桌表
	 */
	private static final long serialVersionUID = -7285694156749845123L;

	private long merchantId; //商家id
	private long tableNo;  //  
	private long size; //  房间人数
	private String name; // 名称
	
	private long bookprice; //定桌钱数
	
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public long getTableNo() {
		return tableNo;
	}
	public void setTableNo(long tableNo) {
		this.tableNo = tableNo;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getBookprice() {
		return bookprice;
	}
	public void setBookprice(long bookprice) {
		this.bookprice = bookprice;
	}
	
}
