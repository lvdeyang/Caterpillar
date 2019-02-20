package com.guolaiwan.bussiness.chapman.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import com.guolaiwan.bussiness.chapman.enumeration.CommentType;
import com.guolaiwan.bussiness.chapman.enumeration.TargetType;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 评论
 * lvdeyang 2017年6月22日
 */
@Entity
@Table(name = "t_app_comment")
public class CommentPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//评论
	private String comment;
	
	//评分
	private int score;
	
	//评论类型
	private CommentType type;
	
	//评论目标
	private Long targetId;
	
	//评论目标类型
	private TargetType targetType;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Enumerated(EnumType.STRING)
	public CommentType getType() {
		return type;
	}

	public void setType(CommentType type) {
		this.type = type;
	}

	@Column(name = "target_id")
	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	@Column(name = "target_type")
	@Enumerated(EnumType.STRING)
	public TargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	
}
