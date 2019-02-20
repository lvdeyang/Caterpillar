package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.CompanyType;

import pub.caterpillar.orm.po.AbstractBasePO;


@Entity
@Table(name = "t_sys_company")
public class CompanyPO extends AbstractBasePO {
	private static final long serialVersionUID = 1L;
	//名称
	private String comName;
	//公司编码
	private String comCode;
	//	//域名
	//	private String domain;
	//是否启用
	private int enable;
	//绑定城市名称
	private String cityName;
	//绑定城市编号
	private String cityCode;
	//图片
	private String pic;
	//图Id
	private long picId;
	//公司地址
	private String address;
	//公司类型
	private CompanyType cType;
	//放大倍数
	private Integer multiple;

	private String companyPhone;

	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public long getPicId() {
		return picId;
	}
	public void setPicId(long picId) {
		this.picId = picId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CompanyType getcType() {
		return cType;
	}
	public void setcType(CompanyType cType) {
		this.cType = cType;
	}
	public Integer getMultiple() {
		return multiple;
	}
	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}
	



}
