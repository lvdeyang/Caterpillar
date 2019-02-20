package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.distribute.classify.DistributorType;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class DistributeProductVO extends AbstractBaseVO<DistributeProductVO, DistributeProduct> {

	private long disProId;

	private long distributorId;

	private DistributorType distributorType;

	private int minAmount;

	private int online;

	private double price;

	private int proleft;

	private long regionId;

	private double sellPrice;

	private int product_id;

	public long getDisProId() {
		return disProId;
	}

	public DistributeProductVO setDisProId(long disProId) {
		this.disProId = disProId;
		return this;
	}

	public long getDistributorId() {
		return distributorId;
	}

	public DistributeProductVO setDistributorId(long distributorId) {
		this.distributorId = distributorId;
		return this;
	}

	public DistributorType getDistributorType() {
		return distributorType;
	}

	public DistributeProductVO setDistributorType(DistributorType distributorType) {
		this.distributorType = distributorType;
		return this;
	}

	public int getMinAmount() {
		return minAmount;
	}

	public DistributeProductVO setMinAmount(int minAmount) {
		this.minAmount = minAmount;
		return this;
	}

	public int getOnline() {
		return online;
	}

	public DistributeProductVO setOnline(int online) {
		this.online = online;
		return this;
	}

	public double getPrice() {
		return price;
	}

	public DistributeProductVO setPrice(double price) {
		this.price = price;
		return this;
	}

	public int getProleft() {
		return proleft;
	}

	public DistributeProductVO setProleft(int proleft) {
		this.proleft = proleft;
		return this;
	}

	public long getRegionId() {
		return regionId;
	}

	public DistributeProductVO setRegionId(long regionId) {
		this.regionId = regionId;
		return this;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public DistributeProductVO setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
		return this;
	}

	public int getProduct_id() {
		return product_id;
	}

	public DistributeProductVO setProduct_id(int product_id) {
		this.product_id = product_id;
		return this;
	}

	@Override
	public DistributeProductVO set(DistributeProduct entity) throws Exception {
		this.setId(entity.getId()).setUuid(entity.getUuid()).setUpdateTime(entity.getUpdateTime())
				.setDisProId(entity.getProduct().getId()).setDistributorId(entity.getDistributorId())
				.setDistributorType(entity.getDistributorType()).setMinAmount(entity.getMinAmount())
				.setOnline(entity.getOnline()).setPrice(entity.getPrice()).setProleft(entity.getProleft())
				.setRegionId(entity.getRegionId()).setSellPrice(entity.getSellPrice());
		return this;
	}

}