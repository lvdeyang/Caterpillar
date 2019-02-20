package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.guolaiwan.bussiness.admin.po.VPRelPO;
import com.guolaiwan.bussiness.admin.po.VoicePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class VPRelVO extends AbstractBaseVO<VPRelVO, VPRelPO> {
	//操作者
	private Long userId;
	//是否点赞
	private int praise;
	//点赞时间
	private String praiseTime;
	//是否收藏
	private int collection;
	//收藏时间
	private String collectionTime;

	//操作者
	private UserInfoVO user; 


	public Long getUserId() {
		return userId;
	}




	public VPRelVO setUserId(Long userId) {
		this.userId = userId;
		return this;
	}





	public int getPraise() {
		return praise;
	}




	public VPRelVO setPraise(int praise) {
		this.praise = praise;
		return this;
	}




	public int getCollection() {
		return collection;
	}




	public VPRelVO setCollection(int collection) {
		this.collection = collection;
		return this;
	}






	public String getPraiseTime() {
		return praiseTime;
	}




	public VPRelVO setPraiseTime(String praiseTime) {
		this.praiseTime = praiseTime;
		return this;
	}




	public String getCollectionTime() {
		return collectionTime;
	}




	public VPRelVO setCollectionTime(String collectionTime) {
		this.collectionTime = collectionTime;
		return this;
	}
	

	

	public UserInfoVO getUser() {
		return user;
	}




	public void setUser(UserInfoVO user) {
		this.user = user;
	}


	@Override
	public VPRelVO set(VPRelPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String praiseTime = entity.getPraiseTime()==null?df.format(entity.getPraiseTime()):"";
		String collectionTime = entity.getCollectionTime()==null?df.format(entity.getCollectionTime()):"";
		
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setUserId(entity.getUserId())
		.setPraise(entity.getPraise())
		.setCollection(entity.getCollection())
		.setPraiseTime(praiseTime)
		.setCollectionTime(collectionTime);
		return this;
	}

}
