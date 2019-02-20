package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.BalancePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class BalanceVO extends AbstractBaseVO<BalanceVO, BalancePO> {

	private long merchantId;
	private String merchantName;
	private String bankNo;
	private String amount;
	private String accrued;
	private String settleDate;

	public long getMerchantId() {
		return merchantId;
	}

	public BalanceVO setMerchantId(long merchantId) {
		this.merchantId = merchantId;
		return this;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public BalanceVO setMerchantName(String merchantName) {
		this.merchantName = merchantName;
		return this;
	}

	public String getBankNo() {
		return bankNo;
	}

	public BalanceVO setBankNo(String bankNo) {
		this.bankNo = bankNo;
		return this;
	}

	public String getAmount() {
		return amount;
	}

	public BalanceVO setAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public String getAccrued() {
		return accrued;
	}

	public BalanceVO setAccrued(String accrued) {
		this.accrued = accrued;
		return this;
	}
	
	public String getSettleDate() {
		return settleDate;
	}

	public BalanceVO setSettleDate(String settleDate) {
		this.settleDate = settleDate;
		return this;
	}

	@Override
	public BalanceVO set(BalancePO entity) throws Exception {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setAmount(new DecimalFormat("0.00").format(((double)entity.getAmount()/100)))
		.setBankNo(entity.getBankNo())
		.setMerchantId(entity.getMerchantId())
		.setMerchantName(entity.getMerchantName())
		.setAccrued(new DecimalFormat("0.00").format(((double)entity.getAccrued()/100)))
		.setSettleDate(df.format(entity.getSettleDate()));
		
		return this;
	}

}
