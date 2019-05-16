package com.guolaiwan.app.xinglongshan.dto;

import java.util.Date;


/**
 * 手机接口景区
 * @author tom
 *
 */
public class SubOrderCheckRecord extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5979494009645674566L;
	private int checkNum;
	private Date checkTime;
	public int getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	
	
	
}
