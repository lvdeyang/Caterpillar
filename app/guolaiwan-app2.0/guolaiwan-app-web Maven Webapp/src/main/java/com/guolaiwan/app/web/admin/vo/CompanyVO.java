package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class CompanyVO extends AbstractBaseVO<CompanyVO, CompanyPO> {

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
	private String cType;
	//公司类型
	private int check;
	//放大倍数
	private Integer multiple;
	//城市
	private List<CityInfoPO> citys;

	private String companyPhone;

	public String getCompanyPhone() {
		return companyPhone;
	}




	public CompanyVO setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
		return this;
	}




	public String getComName() {
		return comName;
	}




	public CompanyVO setComName(String comName) {
		this.comName = comName;
		return this;
	}




	public String getComCode() {
		return comCode;
	}




	public CompanyVO setComCode(String comCode) {
		this.comCode = comCode;
		return this;
	}



	//
	//	public String getDomain() {
	//		return domain;
	//	}
	//
	//
	//
	//
	//	public CompanyVO setDomain(String domain) {
	//		this.domain = domain;
	//		return this;
	//	}




	public int getEnable() {
		return enable;
	}




	public CompanyVO setEnable(int enable) {
		this.enable = enable;
		return this;
	}




	public String getCityName() {
		return cityName;
	}




	public CompanyVO setCityName(String cityName) {
		this.cityName = cityName;
		return this;
	}




	public String getCityCode() {
		return cityCode;
	}




	public CompanyVO setCityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}







	public String getPic() {
		return pic;
	}




	public CompanyVO setPic(String pic) {
		this.pic = pic;
		return this;
	}




	public long getPicId() {
		return picId;
	}




	public CompanyVO setPicId(long picId) {
		this.picId = picId;
		return this;
	}





	public String getAddress() {
		return address;
	}




	public CompanyVO setAddress(String address) {
		this.address = address;
		return this;
	}







	public String getcType() {
		return cType;
	}




	public CompanyVO setcType(String cType) {
		this.cType = cType;
		return this;
	}







	public int getCheck() {
		return check;
	}




	public void setCheck(int check) {
		this.check = check;
	}
	




	public Integer getMultiple() {
		return multiple;
	}




	public CompanyVO setMultiple(Integer multiple) {
		this.multiple = multiple;
		return this;
	}
	
	




	public List<CityInfoPO> getCitys() {
		return citys;
	}




	public CompanyVO setCitys(List<CityInfoPO> citys) {
		this.citys = citys;
		return this;
	}




	@Override
	public CompanyVO set(CompanyPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setEnable(entity.getEnable())
		//		.setDomain(entity.getDomain())
		.setComName(entity.getComName())
		.setCityName(entity.getCityName())
		.setComCode(entity.getComCode())
		.setCityCode(entity.getCityCode())
		.setPic(entity.getPic())
		.setPicId(entity.getPicId())
		.setAddress(entity.getAddress())
		.setCompanyPhone(entity.getCompanyPhone())
		.setcType(entity.getcType().getFiled())
		.setMultiple(entity.getMultiple());
		return this;
	}

}
