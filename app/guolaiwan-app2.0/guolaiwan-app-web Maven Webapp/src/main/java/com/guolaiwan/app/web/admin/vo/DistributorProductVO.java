package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.DistributorProductPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;
import pub.caterpillar.mvc.converter.VOConverter;

public class DistributorProductVO extends AbstractBaseVO<DistributorProductVO, DistributorProductPO> {


	private long distributorId;
	private String distributorPrice;
	private String sellPrice;
	private long productId;
	private String productName;
	// 商城现价
	private String productPrice;
	//库存
	private long productStock;


	public long getProductId() {
		return productId;
	}
	public DistributorProductVO setProductId(long productId) {
		this.productId = productId;
		return this;
	}

	public String getProductName() {
		return productName;
	}
	public DistributorProductVO setProductName(String productName) {
		this.productName = productName;
		return this;
	}
	public String getDistributorPrice() {
		return distributorPrice;
	}
	public DistributorProductVO setDistributorPrice(String distributorPrice) {
		this.distributorPrice = distributorPrice;
		return this;
	}
	public String getSellPrice() {
		return sellPrice;
	}
	public DistributorProductVO setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
		return this;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public DistributorProductVO setProductPrice(String productPrice) {
		this.productPrice = productPrice;
		return this;
	}
	public long getProductStock() {
		return productStock;
	}
	public DistributorProductVO setProductStock(long productStock) {
		this.productStock = productStock;
		return this;
	}
	public long getDistributorId() {
		return distributorId;
	}
	public DistributorProductVO setDistributorId(long distributorId) {
		this.distributorId = distributorId;
		return this;
	}
	@Override
	public DistributorProductVO set(DistributorProductPO entity) throws Exception {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat df = new DecimalFormat("0.00");  
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setDistributorId(entity.getDistributorId())
		.setDistributorPrice(df.format((double)entity.getDistributorPrice()/100))
		.setSellPrice(df.format((double)entity.getSellPrice()/100));
		return this;
	}




}
