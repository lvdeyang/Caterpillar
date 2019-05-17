package com.guolaiwan.app.xinglongshan.dto;

import java.util.Date;



/**
 * 请求�?
 * @author crazy_cabbage
 *
 */
public class Header extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5084856327619884122L;
	/**
	 * 接口类型�?
	 */
	private String application;
	/**
	 * 请求时间
	 */
	private Date requestTime;
	
	private String version;
	
	private String hardwareCode;
	
	
	
	public String getHardwareCode() {
		return hardwareCode;
	}
	public void setHardwareCode(String hardwareCode) {
		this.hardwareCode = hardwareCode;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	
	

}
