package com.guolaiwan.app.web.admin.vo;

import java.util.Date;

import com.guolaiwan.bussiness.admin.po.LiveGiftPO;
import com.guolaiwan.bussiness.admin.po.LiveTipGiftPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LiveTipGiftVO extends AbstractBaseVO<LiveTipGiftVO,LiveTipGiftPO>{

	private String username;
	
	private int price;
	
	private String giftname;
	
	private long giftId;
	
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


	public void setPrice(int price) {
		this.price = price;
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


	@Override
	public LiveTipGiftVO set(LiveTipGiftPO entity) throws Exception {
		this.setGiftId(entity.getGiftId());
		this.setGiftnumber(entity.getGiftnumber());
		this.setId(entity.getId());
		this.setLiveid(entity.getLiveid());
		this.setPrice(entity.getPrice());
		this.setUpdateTime(new Date());
		this.setUsername(entity.getUsername());
		this.setUuid(entity.getUuid());
		this.setGiftname(entity.getGiftname());
		return this;
	}


	



	



}