package com.chenxi.web.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "articlecontent")
public class ArticleContentPo extends AbstractBasePO{

	private long articleId;
	private String myimage;
	private String mycontent;
	private int myindex;
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	public String getMyimage() {
		return myimage;
	}
	public void setMyimage(String myimage) {
		this.myimage = myimage;
	}
	public String getMycontent() {
		return mycontent;
	}
	public void setMycontent(String mycontent) {
		this.mycontent = mycontent;
	}
	public int getMyindex() {
		return myindex;
	}
	public void setMyindex(int myindex) {
		this.myindex = myindex;
	}
	
	
	
	
}
