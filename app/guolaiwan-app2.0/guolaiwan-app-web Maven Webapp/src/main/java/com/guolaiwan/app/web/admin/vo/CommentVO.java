package com.guolaiwan.app.web.admin.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.sun.jna.platform.win32.Netapi32Util.UserInfo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class CommentVO extends AbstractBaseVO<CommentVO,CommentPO>{

	//商品Id
	private long proId;
	//评论内容
	private String content;
	//评论时间
	private String userDate;
	//星级
	private int start;
	//商家回复
	private String merContent;
	//回复时间
	private String merDate;
	//用户名称
	private String userName;
	//用户头像
	private String userHeadimg;
	//评论图片
	private String commentMPic;


	public long getProId() {
		return proId;
	}




	public CommentVO setProId(long proId) {
		this.proId = proId;
		return this;
	}




	public String getContent() {
		return content;
	}




	public CommentVO setContent(String content) {
		this.content = content;
		return this;
	}




	public String  getUserDate() {
		return userDate;
	}




	public CommentVO setUserDate(String userDate) {
		this.userDate = userDate;
		return this;
	}




	public int getStart() {
		return start;
	}




	public CommentVO setStart(int start) {
		this.start = start;
		return this;
	}




	public String getMerContent() {
		return merContent;
	}




	public CommentVO setMerContent(String merContent) {
		this.merContent = merContent;
		return this;
	}




	public String getMerDate() {
		return merDate;
	}




	public CommentVO setMerDate(String merDate) {
		this.merDate = merDate;
		return this;
	}



	public String getUserName() {
		return userName;
	}




	public CommentVO setUserName(String userName) {
		this.userName = userName;
		return this;
	}




	public String getUserHeadimg() {
		return userHeadimg;
	}




	public CommentVO setUserHeadimg(String userHeadimg) {
		this.userHeadimg = userHeadimg;
		return this;
	}
	




	public String getCommentMPic() {
		return commentMPic;
	}




	public CommentVO setCommentMPic(String commentMPic) {
		this.commentMPic = commentMPic;
		return this;
	}




	@Override
	public CommentVO set(CommentPO entity)throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updateTime = entity.getUpdateTime()==null?"":sdf.format(entity.getUpdateTime());
		String merDate = entity.getMerDate()==null?"":sdf.format(entity.getMerDate());
		String userDate = entity.getUserDate()==null?"":sdf.format(entity.getUserDate());
		
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(updateTime)
		.setProId(entity.getProId())
		.setContent(entity.getContent())
		.setMerContent(entity.getMerContent())
		.setMerDate(merDate)
		.setStart(entity.getStart())
		.setUserDate(userDate)
		.setCommentMPic(entity.getCommentMPic());

		UserInfoPO userInfoPO = entity.getUser();
		if(userInfoPO!=null){
			this.setUserName(userInfoPO.getUserPhone()==null?userInfoPO.getUserNickname():userInfoPO.getUserPhone());
			this.setUserHeadimg(userInfoPO.getUserHeadimg());
		}
		return this;

	}
}