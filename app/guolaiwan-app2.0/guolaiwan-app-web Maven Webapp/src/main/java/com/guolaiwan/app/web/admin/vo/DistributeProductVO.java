package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.distribute.po.DistributeProduct;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class DistributeProductVO extends AbstractBaseVO<DistributeProductVO, DistributeProduct> {

	private long disProId;

	private long distributorId;
	
	private String distributorName;


	private int online;

	private double price;

	private int proleft;

	private long regionId;


	private int product_id;
	
	private String productName;
	
	private String regionName;
    
	
	
	
	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

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
				.setOnline(entity.getOnline()).setPrice(entity.getPrice()).setProleft(entity.getProleft())
				.setRegionId(entity.getRegionId());
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	
}