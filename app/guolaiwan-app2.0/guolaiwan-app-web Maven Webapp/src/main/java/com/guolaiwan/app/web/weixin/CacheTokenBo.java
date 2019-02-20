package com.guolaiwan.app.web.weixin;

import java.util.Date;

public class CacheTokenBo {

	private String token;
	private String ticket;
	private Date fecthTime;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Date getFecthTime() {
		return fecthTime;
	}
	public void setFecthTime(Date fecthTime) {
		this.fecthTime = fecthTime;
	}
	
}
