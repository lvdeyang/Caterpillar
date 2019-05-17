package com.guolaiwan.app.xinglongshan.dto;

import java.util.List;



public class TicketOrder extends BaseOrderDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1734130761217901629L;
	/**门票对应的第三方订单号**/
	private String ticketThirdCode;
	  /**
	    * 退票数量   <br />
	    * 允许为空  Y <br />
	    * 数据长度 5<br />
	    */
	   private Integer returnNum; 
	   /**
	    * 已检票量   <br />
	    * 允许为空  Y <br />
	    * 数据长度 0<br />
	    */
	   private Integer alreadyCheckNum;
	   
	   /**
	    *实名认证 信息 
	    */
	   private List<Credential> credentials;
	   /**
	    * 座位信息
	    */
	   private String seatInfo;
	   
	   private List<SeatDto> seatDtos;
	   
	public List<SeatDto> getSeatDtos() {
		return seatDtos;
	}
	public void setSeatDtos(List<SeatDto> seatDtos) {
		this.seatDtos = seatDtos;
	}
	public List<Credential> getCredentials() {
		return credentials;
	}
	public void setCredentials(List<Credential> credentials) {
		this.credentials = credentials;
	}
	public String getTicketThirdCode() {
		return ticketThirdCode;
	}
	public void setTicketThirdCode(String ticketThirdCode) {
		this.ticketThirdCode = ticketThirdCode;
	}
	public Integer getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}
	public Integer getAlreadyCheckNum() {
		return alreadyCheckNum;
	}
	public void setAlreadyCheckNum(Integer alreadyCheckNum) {
		this.alreadyCheckNum = alreadyCheckNum;
	}
	public String getSeatInfo() {
		return seatInfo;
	}
	public void setSeatInfo(String seatInfo) {
		this.seatInfo = seatInfo;
	}
	
}
