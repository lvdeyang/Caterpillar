package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.VPCommentPO;
import com.guolaiwan.bussiness.admin.po.VPRelPO;
import com.guolaiwan.bussiness.admin.po.VideoPicPO;
import com.mysql.jdbc.TimeUtil;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class VPCommentVO extends AbstractBaseVO<VPCommentVO, VPCommentPO> {
	//评论人
	private Long userId;
	//被评论人
	private long auserId;
	//评论内容
	private String commentText;
	//private 
	private UserInfoVO user;




	public Long getUserId() {
		return userId;
	}




	public VPCommentVO setUserId(Long userId) {
		this.userId = userId;
		return this;
	}




	




	public long getAuserId() {
		return auserId;
	}




	public VPCommentVO setAuserId(long auserId) {
		this.auserId = auserId;
		return this;
	}




	public String getCommentText() {
		return commentText;
	}




	public VPCommentVO setCommentText(String commentText) {
		this.commentText = commentText;
		return this;
	}


	




	public UserInfoVO getUser() {
		return user;
	}




	public void setUser(UserInfoVO user) {
		this.user = user;
	}




	@Override
	public VPCommentVO set(VPCommentPO entity) throws Exception {
		if(entity.getAuserId()==null) this.setAuserId(0);
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(DateUtil.showWebDate(entity.getUpdateTime()))
		.setUserId(entity.getUserId())
		.setCommentText(entity.getCommentText());
		return this;
	}

}
