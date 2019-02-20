package com.guolaiwan.app.web.admin.vo;

public class TableVo {

	private long tableId;
	private String tableStatus;
	private long bill;
	private long tableNo;
	private long size;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public long getTableId() {
		return tableId;
	}
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}
	
	
	
	public String getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}
	public long getBill() {
		return bill;
	}
	public void setBill(long bill) {
		this.bill = bill;
	}
	public long getTableNo() {
		return tableNo;
	}
	public void setTableNo(long tableNo) {
		this.tableNo = tableNo;
	}
	
}
