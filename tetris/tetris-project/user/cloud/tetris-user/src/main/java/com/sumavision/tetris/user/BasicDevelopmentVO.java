package com.sumavision.tetris.user;

import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

public class BasicDevelopmentVO extends AbstractBaseVO<BasicDevelopmentVO, BasicDevelopmentPO>{

	private String appId;
	
	private String appSecret;
	
	private Long userId;
	
	public String getAppId() {
		return appId;
	}

	public BasicDevelopmentVO setAppId(String appId) {
		this.appId = appId;
		return this;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public BasicDevelopmentVO setAppSecret(String appSecret) {
		this.appSecret = appSecret;
		return this;
	}
	
	public Long getUserId() {
		return userId;
	}

	public BasicDevelopmentVO setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public BasicDevelopmentVO set(BasicDevelopmentPO entity) throws Exception {
		this.setAppId(entity.getAppId());
		return this;
	}
	
}
