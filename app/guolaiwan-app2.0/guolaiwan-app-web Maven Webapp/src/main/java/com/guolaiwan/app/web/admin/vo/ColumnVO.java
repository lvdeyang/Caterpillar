package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.enumeration.ColumnType;
import com.guolaiwan.bussiness.admin.po.ColumnPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ColumnVO extends AbstractBaseVO<ColumnVO, ColumnPO> {

	private long merchantId;
	
	private ColumnType type;
	
	private String code;

	public String getCode() {
		return code;
	}

	public ColumnVO setCode(String code) {
		this.code = code;
		return this;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public ColumnVO setMerchantId(long merchantId) {
		this.merchantId = merchantId;
		return this;
	}

	public ColumnType getType() {
		return type;
	}

	public ColumnVO setType(ColumnType type) {
		this.type = type;
		return this;
	}

	@Override
	public ColumnVO set(ColumnPO entity) throws Exception {
		this.setId(entity.getId()).setUuid(entity.getUuid()).setUpdateTime(entity.getUpdateTime())
				.setMerchantId(entity.getMerchantId()).setType(entity.getType()).setCode(entity.getCode());
		return this;
	}

}