package com.chenxi.web.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "articlecontent")
public class ArticleContentPo extends AbstractBasePO{

	private long articleId;
	private String image;
	private String content;
	private int index;
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
