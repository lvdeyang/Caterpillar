package com.chenxi.web.yueba.admin.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.chenxi.web.classes.WorkerStatus;
import com.chenxi.web.classes.Moudular;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "comment")
public class CommentPo extends AbstractBasePO{
	private String content;
	private int score;
	private String answer;
	private long userId;
	private String userName;
	private String userPhoto;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
