package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_addtheroom")
public class AddTheRoomPO extends AbstractBasePO {

	private static final long serialVersionUID = -1441300123135426426L;

	private String merchantId; //商家
	
	private String name; //名称
	
	private int  state; //状态   0下架  1 为上架 
	
	private String tier;  // 层
	
	private int  price;	 //价格
	
	private String identity; //规格
	
	private String roomdetails; //房间详情
	
	private String roomimg; //房间图片
	
	private int isreception;  //全天前台
	
	private int iswifi;	//wifi
	
	private int iskettle; //热水壶
	
	private int istoilet;//卫生间
	
	private int istv; //电视
	
	private int isfan; //空调
	
	
	

	public int getIsreception() {
		return isreception;
	}

	public void setIsreception(int isreception) {
		this.isreception = isreception;
	}

	public int getIswifi() {
		return iswifi;
	}

	public void setIswifi(int iswifi) {
		this.iswifi = iswifi;
	}

	public int getIskettle() {
		return iskettle;
	}

	public void setIskettle(int iskettle) {
		this.iskettle = iskettle;
	}

	public int getIstoilet() {
		return istoilet;
	}

	public void setIstoilet(int istoilet) {
		this.istoilet = istoilet;
	}

	public int getIstv() {
		return istv;
	}

	public void setIstv(int istv) {
		this.istv = istv;
	}

	public int getIsfan() {
		return isfan;
	}

	public void setIsfan(int isfan) {
		this.isfan = isfan;
	}

	public String getRoomimg() {
		return roomimg;
	}

	public void setRoomimg(String roomimg) {
		this.roomimg = roomimg;
	}

	public String getRoomdetails() {
		return roomdetails;
	}

	public void setRoomdetails(String roomdetails) {
		this.roomdetails = roomdetails;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
