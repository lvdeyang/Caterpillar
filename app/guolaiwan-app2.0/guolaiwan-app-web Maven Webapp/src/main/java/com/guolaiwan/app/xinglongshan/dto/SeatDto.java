package com.guolaiwan.app.xinglongshan.dto;

import java.io.Serializable;

public class SeatDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6407835620109305128L;

	/**
	 * 区域编码
	 */
	private String areaCode;
	
	/**
	 * 区域名称
	 */
	private String areaName;
	
	/**
	 * 列
	 */
	private Long col;
	/**
	 * 行
	 */
	private Long row;
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getCol() {
		return col;
	}
	public void setCol(Long col) {
		this.col = col;
	}
	public Long getRow() {
		return row;
	}
	public void setRow(Long row) {
		this.row = row;
	}
	
	
	
}
