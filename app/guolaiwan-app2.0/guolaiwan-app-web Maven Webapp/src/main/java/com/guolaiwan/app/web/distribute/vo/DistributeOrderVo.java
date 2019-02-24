package com.guolaiwan.app.web.distribute.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.stat.TableStat.Name;
import com.guolaiwan.app.web.admin.vo.BalanceVO;
import com.guolaiwan.bussiness.distribute.classify.DistributorOrderStatus;
import com.guolaiwan.bussiness.distribute.classify.DistributorType;
import com.guolaiwan.bussiness.distribute.po.DistributePolicy;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;
import com.guolaiwan.bussiness.distribute.po.DistributorOrder;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class DistributeOrderVo extends AbstractBaseVO<DistributeOrderVo, DistributorOrder> {
    private String name;
    private String pic;
	private String type;
	private double price;
	private int count;
	private String status;
	private long productId;
	private long regionId;
	private String region;
	private String rejectReason;
	private String contractPicUrl;
	private String contractVideoUrl;
	private String applicationDate;
	@Override
	public DistributeOrderVo set(DistributorOrder entity) throws Exception {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		this.setId(entity.getId())
		.setCount(entity.getCount())
		.setStatus(entity.getStatus().toString())
		.setType(entity.getType()==null?null:entity.getType().toString())
		.setProductId(entity.getProductId())
		.setRegionId(entity.getRegion())
		.setContractPicUrl(entity.getContractPicUrl())
		.setContractVideoUrl(entity.getContractVideoUrl())
		.setRejectReason(entity.getRejectReason())
		.setPrice(entity.getPrice())
		.setApplicationDate(entity.getApplicationDate()!=null?df.format(entity.getApplicationDate()):"");

		return this;
	}




	public String getContractPicUrl() {
		return contractPicUrl;
	}




	public DistributeOrderVo setContractPicUrl(String contractPicUrl) {
		this.contractPicUrl = contractPicUrl;
		return this;
	}




	public String getContractVideoUrl() {
		return contractVideoUrl;
	}




	public DistributeOrderVo setContractVideoUrl(String contractVideoUrl) {
		this.contractVideoUrl = contractVideoUrl;
		return this;
	}




	public String getName() {
		return name;
	}

	public DistributeOrderVo setName(String name) {
		this.name = name;
		return this;
	}

	public String getPic() {
		return pic;
	}


	public DistributeOrderVo setPic(String pic) {
		this.pic = pic;
		return this;
	}

	public String getType() {
		return type;
	}


	public DistributeOrderVo setType(String type) {
		this.type = type;
		return this;
	}

	public double getPrice() {
		return price;
	}

	public DistributeOrderVo setPrice(double price) {
		this.price = price;
		return this;
	}

	public int getCount() {
		return count;
	}

	public DistributeOrderVo setCount(int count) {
		this.count = count;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public DistributeOrderVo setStatus(String status) {
		this.status = status;
		return this;
	}

	public long getProductId() {
		return productId;
	}

	public DistributeOrderVo setProductId(long productId) {
		this.productId = productId;
		return this;
	}




	public long getRegionId() {
		return regionId;
	}




	public DistributeOrderVo setRegionId(long regionId) {
		this.regionId = regionId;
		return this;
	}




	public String getRegion() {
		return region;
	}




	public void setRegion(String region) {
		this.region = region;
	}




	public String getRejectReason() {
		return rejectReason;
	}




	public DistributeOrderVo setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
		return this;
	}

	
	public String getApplicationDate() {
		return applicationDate;
	}

	public DistributeOrderVo setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
		return this;
	}
}
