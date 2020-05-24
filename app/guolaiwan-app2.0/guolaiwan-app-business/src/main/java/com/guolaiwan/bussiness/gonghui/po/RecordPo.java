package com.guolaiwan.bussiness.gonghui.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "readrecord")
public class RecordPo extends AbstractBasePO {
	private String recordUrl;
	private long userId;
	private long articleId;
	private String articleName;
	private String name;
	@Transient
	private int hasLike;//是否点赞，0，未赞，1，已赞
	@Transient
	private int likeCount;
	public String getRecordUrl() {
		return recordUrl;
	}
	public void setRecordUrl(String recordUrl) {
		this.recordUrl = recordUrl;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	
	
	public int getHasLike() {
		return hasLike;
	}
	public void setHasLike(int hasLike) {
		this.hasLike = hasLike;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
