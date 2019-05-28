package com.guolaiwan.app.xinglongshan.dto;

import java.io.Serializable;
/**
 * 实名认证身份信息
 * @author xtt
 *
 */
public class Credential implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1464077207414868183L;
	/**
	 * 游客姓名
	 */
	private String name;
	/**
	 * 姓名对应身份证
	 */
	private String id;
	/**
	 * 头像地址
	 */
	private String faceUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFaceUrl() {
		return faceUrl;
	}

	public void setFaceUrl(String faceUrl) {
		this.faceUrl = faceUrl;
	}
	
}
