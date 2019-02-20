package com.guolaiwan.bussiness.chapman.product.dto;

import java.util.Date;
import java.util.List;

import com.sun.tools.classfile.Opcode.Set;

/**
 * 产品视图
 * lvdeyang 2017年7月3日
 */
public class ProductDTO {

	private Long id;
	
	private Date updateTime;
	
	private String uuid;
	
	private String name;
	
	private String introduction;
	
	private String position;
	
	private Long price;
	
	private byte[] image;

	private String imagePath;
	
	private String title;
	
	private String subTitle;
	
	public Long getId() {
		return id;
	}

	public ProductDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public ProductDTO setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public ProductDTO setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public String getName() {
		return name;
	}

	public ProductDTO setName(String name) {
		this.name = name;
		return this;
	}

	public String getIntroduction() {
		return introduction;
	}

	public ProductDTO setIntroduction(String introduction) {
		this.introduction = introduction;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public ProductDTO setPosition(String position) {
		this.position = position;
		return this;
	}

	public Long getPrice() {
		return price;
	}

	public ProductDTO setPrice(Long price) {
		this.price = price;
		return this;
	}
	
	public byte[] getImage() {
		return image;
	}

	public ProductDTO setImage(byte[] image) {
		this.image = image;
		return this;
	}

	public String getImagePath() {
		return imagePath;
	}

	public ProductDTO setImagePath(String imagePath) {
		this.imagePath = imagePath;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public ProductDTO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public ProductDTO setSubTitle(String subTitle) {
		this.subTitle = subTitle;
		return this;
	}
	
}
