package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.BookType;
import com.guolaiwan.bussiness.admin.enumeration.TableStatus;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_sys_tableStatus")
public class TableStatusPO extends AbstractBasePO {
	/*
	 * 桌子中间表
	 */
	private static final long serialVersionUID = 5013010096704514202L;

	private long tableId; //桌号Id
	private long merchantId;   //商户id
	private String date; //时间
	private BookType type;  
	private String  userId; //用户id
	private TableStatus tableStatus; //没用
	
	public long getTableId() {
		return tableId;
	}
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}
	
	@Enumerated(EnumType.STRING)
	public TableStatus getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(TableStatus tableStatus) {
		this.tableStatus = tableStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Enumerated(EnumType.STRING)
	public BookType getType() {
		return type;
	}
	public void setType(BookType type) {
		this.type = type;
	}
	
}
