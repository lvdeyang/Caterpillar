package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "Message")
public class MessagePO extends AbstractBasePO {
	private String name;// 姓名
	private String number;// 身份证号
	private String base;// 图片base64的编码
	private String oderId;// 订单id
	private String merchantid;// 商户id
	private String state;// 支付状态 0代表未支付，1代表已支付，2代表以验单

	//用户id
	private Long userId;
	//电话
	private String phone;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOderId() {
		return oderId;
	}

	public void setOderId(String oderId) {
		this.oderId = oderId;
	}

	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	@Override
	public String toString() {
		return "MessagePO [name=" + name + ", number=" + number + ", base=" + base + ", oderId=" + oderId
				+ ", merchantid=" + merchantid + ", state=" + state + "]";
	}

}
