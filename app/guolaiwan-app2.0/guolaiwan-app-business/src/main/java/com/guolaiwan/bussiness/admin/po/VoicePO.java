package com.guolaiwan.bussiness.admin.po;



import javax.persistence.Entity;

import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_voice")
public class VoicePO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	//旧文件名
	private String oldName;
	//新文件名
	private String newName;
	//文件夹
	private String folde; 
	//项目路径
	private String webUrl;
	//文件大小
	private String fileSize;
	//上传结果
	private int if_valid;
	//上传说明
	private String introduce;
	//公司
	private long comId;
	//上传说明
	private String comName;


	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getFolde() {
		return folde;
	}
	public void setFolde(String folde) {
		this.folde = folde;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public int getIf_valid() {
		return if_valid;
	}
	public void setIf_valid(int if_valid) {
		this.if_valid = if_valid;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public long getComId() {
		return comId;
	}
	public void setComId(long comId) {
		this.comId = comId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	
}
