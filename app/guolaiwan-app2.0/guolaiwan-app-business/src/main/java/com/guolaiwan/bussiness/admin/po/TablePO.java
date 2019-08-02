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
	private String tableNo;  //  桌号
	private String tier;  //  层
	private long size; //  房间人数
	private int room; // 是否是包间
	private String Tablename; // 名称
	private long bookprice; //定桌钱数
/*	private String tableMenu; //菜品
	private String userName; //用户名称
	private String userPhone; //手机号
	private String menuTime; //订桌时间
	private String type; //午晚    */
	private String  tableState;  // 0无法预订  1可预订  2已预订
	
	
	
	
	public String getTableState() {
		return tableState;
	}
	public void setTableState(String tableState) {
		this.tableState = tableState;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
    
	
	public String getTableNo() {
		return tableNo;
	}
	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}
	public String getTablename() {
		return Tablename;
	}
	public void setTablename(String tablename) {
		Tablename = tablename;
	}
	public long getBookprice() {
		return bookprice;
	}
	public void setBookprice(long bookprice) {
		this.bookprice = bookprice;
	}
	
}
