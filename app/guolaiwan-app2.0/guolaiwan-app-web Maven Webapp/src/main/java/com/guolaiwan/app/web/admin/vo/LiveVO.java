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

	//红包池钱数，单位分
	private long amountRed;
	//最大红包额度，单位分
	private long maxRed;

	//是否打开红包
	private int isOpenRed;
	//红包名称
	private String redName;
	
	//直播封面
    private String redCover;
	
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

	
	
	public long getAmountRed() {
		return amountRed;
	}

	public LiveVO setAmountRed(long amountRed) {
		this.amountRed = amountRed;
		return this;
	}

	public long getMaxRed() {
		return maxRed;
	}

	public LiveVO setMaxRed(long maxRed) {
		this.maxRed = maxRed;
		return this;
	}

	
	public int getIsOpenRed() {
		return isOpenRed;
	}

	public LiveVO setIsOpenRed(int isOpenRed) {
		this.isOpenRed = isOpenRed;
		return this;
	}

	public String getRedName() {
		return redName;
	}

	public LiveVO setRedName(String redName) {
		this.redName = redName;
		return this;
	}

	public String getRedCover() {
		return redCover;
	}

	public LiveVO setRedCover(String redCover) {
		this.redCover = redCover;
		return this;
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
		.setAmountRed(entity.getAmountRed())
		.setMaxRed(entity.getMaxRed())
		.setIsOpenRed(entity.getIsOpenRed())
		.setRedName(entity.getRedName())
		.setRedCover(entity.getRedCover())
		.setUserId(entity.getUserId());
		return this;

	}
}
