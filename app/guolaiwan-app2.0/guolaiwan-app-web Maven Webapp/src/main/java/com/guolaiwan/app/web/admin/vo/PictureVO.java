package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.guolaiwan.bussiness.admin.po.RolePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class PictureVO extends AbstractBaseVO<PictureVO, PicturePO> {

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
	//更新日期
	private String update;
	//上传说明
	private long comId;
	//更新日期
	private String comName;
	//类型
	private String type;
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOldName() {
		return oldName;
	}

	public PictureVO setOldName(String oldName) {
		this.oldName = oldName;
		return this;
	}

	public String getNewName() {
		return newName;
	}

	public PictureVO setNewName(String newName) {
		this.newName = newName;
		return this;
	}

	public String getFolde() {
		return folde;
	}

	public PictureVO setFolde(String folde) {
		this.folde = folde;
		return this;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public PictureVO setWebUrl(String webUrl) {
		this.webUrl = webUrl;
		return this;
	}

	public String getFileSize() {
		return fileSize;
	}

	public PictureVO setFileSize(String fileSize) {
		this.fileSize = fileSize;
		return this;
	}

	public int getIf_valid() {
		return if_valid;
	}

	public PictureVO setIf_valid(int if_valid) {
		this.if_valid = if_valid;
		return this;
	}

	public String getIntroduce() {
		return introduce;
	}

	public PictureVO setIntroduce(String introduce) {
		this.introduce = introduce;
		return this;
	}

	public String getUpdate() {
		return update;
	}

	public PictureVO setUpdate(String update) {
		this.update = update;
		return this;
	}
	

	

	public long getComId() {
		return comId;
	}

	public PictureVO setComId(long comId) {
		this.comId = comId;
		return this;
	}

	public String getComName() {
		return comName;
	}

	public PictureVO setComName(String comName) {
		this.comName = comName;
		return this;
	}

	@Override
	public PictureVO set(PicturePO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdate(df.format(entity.getUpdateTime()))
		.setOldName(entity.getOldName())
		.setNewName(entity.getNewName())
		.setFolde(entity.getFolde())
		.setWebUrl(entity.getWebUrl())
		.setFileSize(entity.getFileSize())
		.setIf_valid(entity.getIf_valid())
		.setIntroduce(entity.getIntroduce())
		.setComId(entity.getComId())
		.setComName(entity.getComName());
		return this;
	}
	
}
