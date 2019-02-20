package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.MerchantBusinessPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class MerchantBusinessVO extends AbstractBaseVO<MerchantBusinessVO, MerchantBusinessPO> {

	private long merchantId;
	
	private long userId;
	
	public long getMerchantId() {
		return merchantId;
	}

	public MerchantBusinessVO setMerchantId(long merchantId) {
		this.merchantId = merchantId;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public MerchantBusinessVO setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public MerchantBusinessVO set(MerchantBusinessPO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setMerchantId(entity.getMerchantId())
		.setUserId(entity.getUserId());
		return this;
	}
	
}