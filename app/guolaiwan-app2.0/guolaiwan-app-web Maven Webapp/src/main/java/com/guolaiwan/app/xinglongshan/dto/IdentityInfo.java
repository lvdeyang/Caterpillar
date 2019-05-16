package com.guolaiwan.app.xinglongshan.dto;



/**
 * 身份信息
 * @author crazy_cabbage
 *
 */
public class IdentityInfo extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8552657960251340462L;
	/**
	 * 企业名称
	 */
	private String corpCode;
	/**
	 * 用户�?
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 私玥
	 */
	private String privateKey;
	
	
	private String appCode;
	
	private String appKey;
	
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

}
