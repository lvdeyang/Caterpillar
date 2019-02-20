package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.guolaiwan.bussiness.admin.po.VideoPicPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class VideoPicVO extends AbstractBaseVO<VideoPicVO, VideoPicPO> {

	//小视频、图片名称
	private String name;
	//小视频、图片(多图)路径
	private String fUrl;
	//文字内容
	private String content;
	//类型
	private String type;
	//访问量
	private long visitNum;
	//用户Id(发表者的id)
	private long userId;
	//显示图片
	private String headPic;
	//MP3路径
	private String mrul;

	public String getMrul() {
		return mrul;
	}



	public VideoPicVO setMrul(String mrul) {
		this.mrul = mrul;
		return this;
	}



	public String getHeadPic() {
		return headPic;
	}



	public VideoPicVO setHeadPic(String headPic) {
		this.headPic = headPic;
		return this;
	}



	//上传用户
	private UserInfoVO user; 

	//评论
	private List<VPCommentVO> comments;
	
	//评论数
	private int commentCount;

	//点赞
	private List<VPRelVO> praises;
	
	//点赞数
	private int praiseCount;
	
    //是否可以删除 0是不可以1是可以
    private int isDelete;
    
    //是否点赞0没点赞1点赞
    private int isPraise;



	public String getName() {
		return name;
	}



	public VideoPicVO setName(String name) {
		this.name = name;
		return this;
	}






	public String getfUrl() {
		return fUrl;
	}



	public VideoPicVO setfUrl(String fUrl) {
		this.fUrl = fUrl;
		return this;
	}



	public String getContent() {
		return content;
	}



	public VideoPicVO setContent(String content) {
		this.content = content;
		return this;
	}




	public String getType() {
		return type;
	}



	public VideoPicVO setType(String type) {
		this.type = type;
		return this;
	}



	public long getVisitNum() {
		return visitNum;
	}



	public VideoPicVO setVisitNum(long visitNum) {
		this.visitNum = visitNum;
		return this;
	}



	public long getUserId() {
		return userId;
	}



	public VideoPicVO setUserId(long userId) {
		this.userId = userId;
		return this;
	}





	public UserInfoVO getUser() {
		return user;
	}



	public void setUser(UserInfoVO user) {
		this.user = user;
	}
	
	





	public List<VPCommentVO> getComments() {
		return comments;
	}



	public void setComments(List<VPCommentVO> comments) {
		this.comments = comments;
	}



	public int getPraiseCount() {
		return praiseCount;
	}



	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}



	public List<VPRelVO> getPraises() {
		return praises;
	}



	public void setPraises(List<VPRelVO> praises) {
		this.praises = praises;
	}
	
	
	



	public int getCommentCount() {
		return commentCount;
	}



	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	



	public int getIsDelete() {
		return isDelete;
	}



	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	


	public int getIsPraise() {
		return isPraise;
	}



	public void setIsPraise(int isPraise) {
		this.isPraise = isPraise;
	}



	@Override
	public VideoPicVO set(VideoPicPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setName(entity.getName())
		.setType(entity.getType().getName())
		.setfUrl(entity.getfUrl())
		.setContent(entity.getContent())
		.setVisitNum(entity.getVisitNum())
		.setUserId(entity.getUserId())
		.setHeadPic(entity.getHeadPic())
		.setMrul(entity.getMrul())
		.setUpdateTime(DateUtil.showWebDate(entity.getUpdateTime()));
		return this;
	}

}
