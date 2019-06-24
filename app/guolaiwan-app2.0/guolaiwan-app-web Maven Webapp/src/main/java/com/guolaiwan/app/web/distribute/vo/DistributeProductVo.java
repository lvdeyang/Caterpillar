package com.guolaiwan.app.web.distribute.vo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.stat.TableStat.Name;
import com.guolaiwan.bussiness.distribute.po.DistributePolicy;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class DistributeProductVo extends AbstractBaseVO<DistributeProductVo, DistributeProduct> {
    private String name;
    private String pic;
    private double price;
    private long left;
    private double sellPrice;
    private List<DistributePolicyVo> policys;
    private String address;
    private String openTime;
    private String tel;
    private int proleft; //库存
    private long store; // 店铺上下架
	private double retailPrice; // 零售价钱
	private long retailRepertory; // 零售库存
    
	
	public long getStore() {
		return store;
	}



	public DistributeProductVo setStore(long store) {
		this.store = store;
		return this;
	}



	public double getRetailPrice() {
		return retailPrice;
	}



	public DistributeProductVo setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
		return this;
	}



	public long getRetailRepertory() {
		return retailRepertory;
	}



	public DistributeProductVo setRetailRepertory(long retailRepertory) {
		this.retailRepertory = retailRepertory;
		return this;
	}



	public int getProleft() {
		return proleft;
	}



	public DistributeProductVo setProleft(int proleft) {
		this.proleft = proleft;
		return this;
	}



	public String getAddress() {
		return address;
	}



	public DistributeProductVo setAddress(String address) {
		this.address = address;
		return this;
	}



	public String getOpenTime() {
		return openTime;
	}



	public DistributeProductVo setOpenTime(String openTime) {
		this.openTime = openTime;
		return this;
	}



	public String getTel() {
		return tel;
	}



	public DistributeProductVo setTel(String tel) {
		this.tel = tel;
		return this;
	}



	public String getName() {
		return name;
	}



	public DistributeProductVo setName(String name) {
		this.name = name;
		return this;
	}



	public String getPic() {
		return pic;
	}



	public DistributeProductVo setPic(String pic) {
		this.pic = pic;
		return this;
	}



	public double getPrice() {
		return price;
	}



	public DistributeProductVo setPrice(double price) {
		this.price = price;
		return this;
	}



	public long getLeft() {
		return left;
	}



	public DistributeProductVo setLeft(long left) {
		this.left = left;
		return this;
	}



	@Override
	public DistributeProductVo set(DistributeProduct entity) throws Exception {
		// TODO Auto-generated method stub
		this.setId(entity.getId())
		.setName(entity.getProduct().getProductName())
		.setPic(entity.getProduct().getProductShowPic())
		.setLeft(entity.getProleft())
		.setSellPrice(entity.getSellPrice())
		.setAddress(entity.getProduct().getProductCityName())
		.setTel("")
		.setStore(entity.getStore())
		.setRetailPrice(entity.getRetailPrice())
		.setRetailRepertory(entity.getRetailRepertory())
		.setProleft(entity.getProleft())
		.setOpenTime(DateUtil.format(entity.getProduct().getProductEctivedate()))
		.setPrice(entity.getPrice());
		
		this.setPolicys(new ArrayList<DistributePolicyVo>());
		for (DistributePolicy distributePolicy : entity.getDistributePolicies()) {
			DistributePolicyVo vo=new DistributePolicyVo();
			vo.setPrice(distributePolicy.getPrice());
			vo.setCount(distributePolicy.getCount());
			this.getPolicys().add(vo);
		}
		
		
		return this;
	}



	public double getSellPrice() {
		return sellPrice;
	}



	public DistributeProductVo setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
		return this;
	}



	public List<DistributePolicyVo> getPolicys() {
		return policys;
	}



	public void setPolicys(List<DistributePolicyVo> policys) {
		this.policys = policys;
	}

}
