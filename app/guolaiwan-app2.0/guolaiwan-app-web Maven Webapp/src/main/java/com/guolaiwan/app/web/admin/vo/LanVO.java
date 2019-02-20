package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.LanPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LanVO extends AbstractBaseVO<LanVO, LanPO> {
	
	private String name;

	private long merchantId;
	
	public long getMerchantId() {
		return merchantId;
	}

	public LanVO setMerchantId(long merchantId) {
		this.merchantId = merchantId;
		return this;
	}

	public String getName() {
		return name;
	}

	public LanVO setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public LanVO set(LanPO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setMerchantId(entity.getMerchantId())
		.setName(entity.getName());
		return this;
	}
	
}