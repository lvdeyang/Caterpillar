package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_config")
public class SysConfigPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	 	
	//本地路径
	private String folderUrl; 
	//项目路径
	private String webUrl;
	
	
	public String getFolderUrl() {
		return folderUrl;
	}
	public void setFolderUrl(String folderUrl) {
		this.folderUrl = folderUrl;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
}
