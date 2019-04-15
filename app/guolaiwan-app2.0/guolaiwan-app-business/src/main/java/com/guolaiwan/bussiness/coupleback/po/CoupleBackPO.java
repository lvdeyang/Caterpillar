package com.guolaiwan.bussiness.coupleback.po;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_coupleback_table")
public class CoupleBackPO extends AbstractBasePO {
	// 用户id
	private	long userId;
	// 分公司id
	private int  filialeId;
	// 用户头像
	private String headportrait;
	// 用户名称
	private String username;
	// 反馈日期
	private String date;
	// 反馈内容
	private String content;
	//回复内容
	private String replycontent;
	//状态   0未回复  1已回复
	private int state;
	
	
	
	
	
	public long getUserId() {
		return userId;
		
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Column(nullable=false)
	public int getFilialeId() {
		return filialeId;
	}
	public void setFilialeId(int filialeId) {
		this.filialeId = filialeId;
	}
	public String getHeadportrait() {
		return headportrait;
	}
	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Lob
	@Column(columnDefinition="LONGTEXT")
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	@Lob
	@Column(columnDefinition="LONGTEXT")
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}














}
