package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 评论表
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_sys_VPComment")
public class VPCommentPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	//评论人
	private Long userId;
	//被评论人
	private Long auserId;
	//评论内容
	private String commentText;
	//关联的图文小视频
	private VideoPicPO videoPic;

	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAuserId() {
		return auserId;
	}
	public void setAuserId(Long auserId) {
		this.auserId = auserId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	@ManyToOne
	@JoinColumn(name = "videoPic_id")
	public VideoPicPO getVideoPic() {
		return videoPic;
	}
	public void setVideoPic(VideoPicPO videoPic) {
		this.videoPic = videoPic;
	}


}
