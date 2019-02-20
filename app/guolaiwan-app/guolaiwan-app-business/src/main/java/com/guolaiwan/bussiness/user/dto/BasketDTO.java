package com.guolaiwan.bussiness.user.dto;

import java.util.Date;

public class BasketDTO {

	//购物车条目id
	private Long id;
	
	//购物车条目编辑时间
	private Date updateTime;
	
	//商品数量
	private int num;
	
	//商品描述
	private String introduction;
	
	//商品单价
	private Long price;
	
	//当前购物车条目总价
	private Long count;
	
	//当前商品缩略图
	private byte[] image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
