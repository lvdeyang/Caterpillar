package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_sys_olchatmessage")
public class OlChatMessagePO extends AbstractBasePO {

	private static final long serialVersionUID = 1968580983725678041L;
	//商家id
	private long merchantId;
	//交流信息
	private String message;
	//to用户id
	private long touserId;
	//来自用户id
	private long fromuserId;
	//来自用户姓名（昵称）
	private String fromuser;
	// 是否发送
	private boolean flag;
	//用户头像
	private String userheadimg;
	
	public String getUserheadimg() {
		return userheadimg;
	}
	public void setUserheadimg(String userheadimg) {
		this.userheadimg = userheadimg;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTouserId() {
		return touserId;
	}
	public void setTouserId(long touserId) {
		this.touserId = touserId;
	}
	public long getFromuserId() {
		return fromuserId;
	}
	public void setFromuserId(long fromuserId) {
		this.fromuserId = fromuserId;
	}
	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
