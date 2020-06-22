package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;


@Entity 
@Table(name="t_sys_livetipgift")
public class LiveTipGiftPO extends AbstractBasePO{
	private static final long serialVersionUID = 1L;
	
	private long userId;
	
	private String username;
	
	private int price;
	
	private long giftId;
	
	private String giftname;
	
	private long giftnumber;
	
	private long liveid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public int getPrice() {
		return price;
	}

	public void setPrice(int l) {
		this.price = l;
	}

	public long getGiftId() {
		return giftId;
	}

	public void setGiftId(long giftId) {
		this.giftId = giftId;
	}

	public long getGiftnumber() {
		return giftnumber;
	}

	public void setGiftnumber(long giftnumber) {
		this.giftnumber = giftnumber;
	}

	public long getLiveid() {
		return liveid;
	}

	public void setLiveid(long liveid) {
		this.liveid = liveid;
	}

	public String getGiftname() {
		return giftname;
	}

	public void setGiftname(String giftname) {
		this.giftname = giftname;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	

	
	

}
