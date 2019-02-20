package com.guolaiwan.bussiness.chapman.product.dto;

import java.sql.Date;

public class TagMapDTO {

	private Long id;
	
	private String uuid;
	
	private Date updateTime;
	
	private String tagName;

	public Long getId() {
		return id;
	}

	public TagMapDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public TagMapDTO setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public TagMapDTO setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getTagName() {
		return tagName;
	}

	public TagMapDTO setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}
	
}
