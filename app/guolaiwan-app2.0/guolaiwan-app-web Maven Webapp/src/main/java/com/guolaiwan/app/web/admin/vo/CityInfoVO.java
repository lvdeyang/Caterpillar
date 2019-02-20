package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class CityInfoVO extends AbstractBaseVO<CityInfoVO,CityInfoPO>{
	//城市名称 
	private String cityName;
	//域名
	private String cityDomain;
	//标识
	private String cityCode;
	//是否启用
	private int enable;
	public String getCityName() {
		return cityName;
	}
	public CityInfoVO setCityName(String cityName) {
		this.cityName = cityName;
		return this;
	}

	public String getCityDomain() {
		return cityDomain;
	}
	public CityInfoVO setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
		return this;
	}
	public String getCityCode() {
		return cityCode;
	}
	public CityInfoVO setCityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}
	public int getEnable() {
		return enable;
	}
	public CityInfoVO setEnable(int enable) {
		this.enable = enable;
		return this;

	}



	@Override
	public CityInfoVO set(CityInfoPO entity) throws Exception {

		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setCityName(entity.getCityName())
		.setCityDomain(entity.getCityDomain())
		.setCityCode(entity.getCityCode())
		.setEnable(entity.getEnable());


		return this;
	}
}