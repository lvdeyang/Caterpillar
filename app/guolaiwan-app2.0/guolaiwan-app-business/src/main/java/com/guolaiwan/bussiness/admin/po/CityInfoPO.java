package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_Sys_CityInfo")
public class CityInfoPO extends AbstractBasePO{
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
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityDomain() {
		return cityDomain;
	}
	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
    
}
