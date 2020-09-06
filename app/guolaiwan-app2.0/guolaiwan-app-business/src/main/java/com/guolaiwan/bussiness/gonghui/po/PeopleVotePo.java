package com.guolaiwan.bussiness.gonghui.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "peoplevote")
public class PeopleVotePo extends AbstractBasePO {

	private String name;
	private String article;
	private String org;
	private String articleFrom;
	private String voice;
	private String headerimg;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getArticleFrom() {
		return articleFrom;
	}
	public void setArticleFrom(String articleFrom) {
		this.articleFrom = articleFrom;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public String getHeaderimg() {
		return headerimg;
	}
	public void setHeaderimg(String headerimg) {
		this.headerimg = headerimg;
	}
	
	
}
