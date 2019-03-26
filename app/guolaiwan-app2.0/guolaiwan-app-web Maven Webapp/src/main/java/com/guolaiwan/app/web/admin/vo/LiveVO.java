package com.guolaiwan.app.web.admin.vo;

import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.enumeration.LiveType;
import com.guolaiwan.bussiness.admin.po.LivePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LiveVO extends AbstractBaseVO<LiveVO, LivePO> {
	
	//直播名称
	private String liveName;
	//用户
	private Long userId; 
	//商家
	private Long merchantId;
	//
	private String pubName;
	//直播状态（开播，停播，封号）
	private String liveStatusType;
	//直播类型（商户、用户）
	private String liveType;
	//直播封面
	private String cover;
	
	//用户(显示)
	private UserInfoVO user;
	
	private String leshiyunId;

	
	
	public String getLeshiyunId() {
		return leshiyunId;
	}

	public LiveVO setLeshiyunId(String leshiyunId) {
		this.leshiyunId = leshiyunId;
		return this;
	}

	public String getLiveName() {
		return liveName;
	}

	public LiveVO setLiveName(String liveName) {
		this.liveName = liveName;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public LiveVO setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public LiveVO setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
		return this;
	}

	public String getPubName() {
		return pubName;
	}

	public LiveVO setPubName(String pubName) {
		this.pubName = pubName;
		return this;
	}

	public String getCover() {
		return cover;
	}

	public LiveVO setCover(String cover) {
		this.cover = cover;
		return this;
	}
	
	public String getLiveStatusType() {
		return liveStatusType;
	}

	public LiveVO setLiveStatusType(String liveStatusType) {
		this.liveStatusType = liveStatusType;
		return this;
	}

	public String getLiveType() {
		return liveType;
	}

	public LiveVO setLiveType(String liveType) {
		this.liveType = liveType;
		return this;
	}

	public UserInfoVO getUser() {
		return user;
	}

	public void setUser(UserInfoVO user) {
		this.user = user;
	}

	@Override
	public LiveVO set(LivePO entity) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(formatter.format(entity.getUpdateTime()))
		.setCover(entity.getCover())
		.setLiveName(entity.getLiveName())
		.setLiveStatusType(entity.getLiveStatusType().getFiled())
		.setLiveType(entity.getLiveType().getFiled())
		.setPubName(entity.getPubName())
		.setMerchantId(entity.getMerchantId())
		.setLeshiyunId(entity.getLeshiyunId())
		.setUserId(entity.getUserId());
		return this;

	}
}
