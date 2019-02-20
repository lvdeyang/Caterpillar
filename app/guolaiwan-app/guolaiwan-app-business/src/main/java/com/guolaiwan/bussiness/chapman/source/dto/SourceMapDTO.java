package com.guolaiwan.bussiness.chapman.source.dto;

import java.util.Date;

import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.chapman.source.po.SourceMapPO;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;

public class SourceMapDTO {

	private Long id;
	
	private Date updateTime;
	
	private String uuid;
	
	private String mapType;
	
	private String introduction;
	
	private String path;
	
	private String size;
	
	private String sourceType;
	
	private Long fileid;
	
	private Long folderid;
	
	private String foldername;
	
	private byte[] image;

	public Long getId() {
		return id;
	}

	public SourceMapDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public SourceMapDTO setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public SourceMapDTO setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public String getMapType() {
		return mapType;
	}

	public SourceMapDTO setMapType(String mapType) {
		this.mapType = mapType;
		return this;
	}

	public String getIntroduction() {
		return introduction;
	}

	public SourceMapDTO setIntroduction(String introduction) {
		this.introduction = introduction;
		return this;
	}

	public String getPath() {
		return path;
	}

	public SourceMapDTO setPath(String path) {
		this.path = path;
		return this;
	}

	public String getSize() {
		return size;
	}

	public SourceMapDTO setSize(String size) {
		this.size = size;
		return this;
	}

	public String getSourceType() {
		return sourceType;
	}

	public SourceMapDTO setSourceType(String sourceType) {
		this.sourceType = sourceType;
		return this;
	}

	public Long getFileid() {
		return fileid;
	}

	public SourceMapDTO setFileid(Long fileid) {
		this.fileid = fileid;
		return this;
	}

	public Long getFolderid() {
		return folderid;
	}

	public SourceMapDTO setFolderid(Long folderid) {
		this.folderid = folderid;
		return this;
	}

	public String getFoldername() {
		return foldername;
	}

	public SourceMapDTO setFoldername(String foldername) {
		this.foldername = foldername;
		return this;
	}
	
	public byte[] getImage() {
		return image;
	}

	public SourceMapDTO setImage(byte[] image) {
		this.image = image;
		return this;
	}

	public SourceMapDTO set(SourceMapPO entity){
		
		SourcePO source = entity.getSource();
		
		SourceFolderPO folder = source.getSourceFolder();
		
		this.setId(entity.getId())
			.setUpdateTime(entity.getUpdateTime())
			.setUuid(entity.getUuid())
			.setMapType(entity.getType().toString())
			.setIntroduction(source.getIntroduction())
			.setPath(source.getPath())
			.setSize(source.getSize())
			.setSourceType(source.getType().toString())
			.setFileid(source.getFile().getId())
			.setFolderid(folder.getId())
			.setFoldername(folder.getFoldername());
		
		return this;
	}
	
}
