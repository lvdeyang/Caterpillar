package com.guolaiwan.bussiness.gonghui.dto;

public class PeopleVoteDto {
	private long id;
	private String name;
	private String article;
	private String org;
	private String articleFrom;
	private String voice;
	private String headerimg;
	private int rank;
	private Integer count;
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
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
