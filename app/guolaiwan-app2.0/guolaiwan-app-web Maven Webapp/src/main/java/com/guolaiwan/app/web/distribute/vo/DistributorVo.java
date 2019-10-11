package com.guolaiwan.app.web.distribute.vo;

import com.guolaiwan.bussiness.distribute.classify.DistributorApplyStatus;
import com.guolaiwan.bussiness.distribute.po.DistributorPo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class DistributorVo extends AbstractBaseVO<DistributorVo, DistributorPo> {

	@Override
	public DistributorVo set(DistributorPo entity) throws Exception {
		// TODO Auto-generated method stub
		this.setAddress(entity.getAddress())
		.setBankNo(entity.getBankNo())
		.setCheckReason(entity.getCheckReason())
		.setContractPicUrl(entity.getContractPicUrl())
		.setContractVideo(entity.getContractVideo())
		.setContractUrl(entity.getContractUrl())
		.setId(entity.getId())
		.setLegalPerson(entity.getLegalPerson())
		.setLicenseUrl(entity.getLicenseUrl())
		.setPhone(entity.getPhone())
		.setStatus(entity.getStatus().getName());
		return this;
	}
	
	
	private String legalPerson;
	private String bankNo;
	private String address;
	private String phone;
	private String licenseUrl;
	private String contractUrl;
	private String contractPicUrl;
	private String contractVideo;
	private String status;
	private String checkReason;
	private String regionName;
	public String getLegalPerson() {
		return legalPerson;
	}
	public DistributorVo setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
		return this;
	}
	public String getBankNo() {
		return bankNo;
	}
	public DistributorVo setBankNo(String bankNo) {
		this.bankNo = bankNo;
		return this;
	}
	public String getAddress() {
		return address;
	}
	public DistributorVo setAddress(String address) {
		this.address = address;
		return this;
	}
	public String getPhone() {
		return phone;
	}
	public DistributorVo setPhone(String phone) {
		this.phone = phone;
		return this;
	}
	public String getLicenseUrl() {
		return licenseUrl;
	}
	public DistributorVo setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
		return this;
	}
	public String getContractUrl() {
		return contractUrl;
	}
	public DistributorVo setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
		return this;
	}
	public String getContractPicUrl() {
		return contractPicUrl;
	}
	public DistributorVo setContractPicUrl(String contractPicUrl) {
		this.contractPicUrl = contractPicUrl;
		return this;
	}
	public String getContractVideo() {
		return contractVideo;
	}
	public DistributorVo setContractVideo(String contractVideo) {
		this.contractVideo = contractVideo;
		return this;
	}
	
	public String getStatus() {
		return status;
	}
	public DistributorVo setStatus(String status) {
		this.status = status;
		return this;
	}
	public String getCheckReason() {
		return checkReason;
	}
	public DistributorVo setCheckReason(String checkReason) {
		this.checkReason = checkReason;
		return this;
	}
	public String getRegionName() {
		return regionName;
	}
	public DistributorVo setRegionName(String regionName) {
		this.regionName = regionName;
		return this;
	}
	
	
	

}
