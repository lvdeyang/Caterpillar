package com.guolaiwan.bussiness.admin.po;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.ActivityType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_comment")
public class CommentPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	//商品Id
	private long proId;
	//评论内容
	private String content;
	//评论时间
	private Date userDate;
	//星级
	private int start;
	//商家回复
	private String merContent;
	//回复时间
	private Date merDate;
	//用户信息
	private UserInfoPO user;
	//评论图片
	private String commentMPic;
	
	
	
	public long getProId() {
		return proId;
	}
	public void setProId(long proId) {
		this.proId = proId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getUserDate() {
		return userDate;
	}
	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getMerContent() {
		return merContent;
	}
	public void setMerContent(String merContent) {
		this.merContent = merContent;
	}
	public Date getMerDate() {
		return merDate;
	}
	public void setMerDate(Date merDate) {
		this.merDate = merDate;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	public UserInfoPO getUser() {
		return user;
	}
	public void setUser(UserInfoPO user) {
		this.user = user;
	}
	public String getCommentMPic() {
		return commentMPic;
	}
	public void setCommentMPic(String commentMPic) {
		this.commentMPic = commentMPic;
	}
	
	
	
	
	
	

}
