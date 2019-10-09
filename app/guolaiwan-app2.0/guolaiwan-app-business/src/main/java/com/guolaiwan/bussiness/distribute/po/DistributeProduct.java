package com.guolaiwan.bussiness.distribute.po;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.distribute.classify.RecommendType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "distribute_product")
public class DistributeProduct extends AbstractBasePO {

	private ProductPO product;
	private long distributorId;
	private int proleft; //库存
	private double price;//分
	private Set<DistributePolicy> distributePolicies=new HashSet<DistributePolicy>();
	private RecommendType recommendType;
	private int online;
	private long regionId;//如果是0的时候表示商户的分销商品。
	private long store; // 店铺上下架
	private double retailPrice; // 零售价钱
	private long retailRepertory; // 零售库存
	
	
	
	public double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public long getRetailRepertory() {
		return retailRepertory;
	}
	public void setRetailRepertory(long retailRepertory) {
		this.retailRepertory = retailRepertory;
	}
	public long getStore() {
		return store;
	}
	public void setStore(long store) {
		this.store = store;
	}
	public long getRegionId() {
		return regionId;
	}
	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}
	@ManyToOne
	@JoinColumn(name = "product_id")
	public ProductPO getProduct() {
		return product;
	}
	public void setProduct(ProductPO product) {
		this.product = product;
	}
	public int getProleft() {
		return proleft;
	}
	public void setProleft(int left) {
		this.proleft = left;
	}
	public long getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(long distributorId) {
		this.distributorId = distributorId;
	}
	
	
	@Enumerated(EnumType.STRING)
	public RecommendType getRecommendType() {
		return recommendType;
	}
	public void setRecommendType(RecommendType recommendType) {
		this.recommendType = recommendType;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@OneToMany(mappedBy = "distributeProduct", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public Set<DistributePolicy> getDistributePolicies() {
		return distributePolicies;
	}
	public void setDistributePolicies(Set<DistributePolicy> distributePolicies) {
		this.distributePolicies = distributePolicies;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	
	 
}
