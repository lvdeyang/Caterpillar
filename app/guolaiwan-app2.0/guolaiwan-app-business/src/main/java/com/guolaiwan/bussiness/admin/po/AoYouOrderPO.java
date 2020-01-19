package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_Sys_AoYouOrder")
public class AoYouOrderPO extends AbstractBasePO{
	private static final long serialVersionUID = 1L;

	// 过来玩订单号
	private String glwOrderNO;
	// 世园会订单号
	private String orderno;
	// 过来玩发给遨游订单号
	private String trade_no;
	// 遨游订单号
	private String saleorder_no;
	// 下单人手机号
	private String mobile_no;
	// 订单确认码
	private String confirm_code;
	
	public String getGlwOrderNO() {
		return glwOrderNO;
	}
	public void setGlwOrderNO(String glwOrderNO) {
		this.glwOrderNO = glwOrderNO;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getSaleorder_no() {
		return saleorder_no;
	}
	public void setSaleorder_no(String saleorder_no) {
		this.saleorder_no = saleorder_no;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getConfirm_code() {
		return confirm_code;
	}
	public void setConfirm_code(String confirm_code) {
		this.confirm_code = confirm_code;
	}
	
}
