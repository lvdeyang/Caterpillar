package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.LiveRebroadcastPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LiveRebroadcastVO extends AbstractBaseVO<LiveRebroadcastVO,LiveRebroadcastPO>{


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




		public String getUpdate() {
			return update;
		}




		public void setUpdate(String update) {
			this.update = update;
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




		@Override
		public LiveRebroadcastVO set(LiveRebroadcastPO entity) throws Exception {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			this.setId(entity.getId());
			this.setUuid(entity.getUuid());
			this.setUpdate(df.format(entity.getUpdateTime()));
			this.setOldName(entity.getOldName());
			this.setNewName(entity.getNewName());
			this.setFolde(entity.getFolde());
			this.setWebUrl(entity.getWebUrl());
			this.setFileSize(entity.getFileSize());
			this.setIf_valid(entity.getIf_valid());
			this.setIntroduce(entity.getIntroduce());
			this.setComId(entity.getComId());
			this.setComName(entity.getComName());
			return this;
		}

		
		
	}

