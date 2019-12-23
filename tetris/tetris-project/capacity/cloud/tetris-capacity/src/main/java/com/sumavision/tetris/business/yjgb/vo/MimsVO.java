package com.sumavision.tetris.business.yjgb.vo;

/**
 * 媒资注入信息<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月25日 下午3:08:16
 */
public class MimsVO {

	private boolean needAdd;
	
	private String name;
	
	private String type;
	
	private String httpUrl;
	
	private String ftpUrl;

	public boolean isNeedAdd() {
		return needAdd;
	}

	public MimsVO setNeedAdd(boolean needAdd) {
		this.needAdd = needAdd;
		return this;
	}

	public String getName() {
		return name;
	}

	public MimsVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getType() {
		return type;
	}

	public MimsVO setType(String type) {
		this.type = type;
		return this;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public MimsVO setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
		return this;
	}

	public String getFtpUrl() {
		return ftpUrl;
	}

	public void setFtpUrl(String ftpUrl) {
		this.ftpUrl = ftpUrl;
	}
}
