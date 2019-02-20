package com.guolaiwan.bussiness.admin.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_phoneCode")
public class PhoneCodePO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	//手机号
	private String  phoneNum;
	//验证码
	private String code;
	//类型(1是注册模板，2是重置密码模板)
	private Integer type;
	//来源(1是APP，2是网页)
	private Integer source;
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	
	
	



}
