package com.guolaiwan.app.web.website.vo;

import com.guolaiwan.bussiness.chapman.source.dto.SourceMapDTO;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceMapType;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceType;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class PictureVO extends AbstractBaseVO<PictureVO, SourceMapDTO> {

	//名称
	private String name;
	
	//路径
	private String path;
	
	//尺寸
	private String size;
	
	//映射类型
	private String mapType;
	
	//资源类型
	private String sourceType;
	
	//文件id
	private Long fileid;
	
	//文件夹id
	private Long folderid;
	
	//文件夹名称
	private String foldername;
	
	public String getName() {
		return name;
	}

	public PictureVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getPath() {
		return path;
	}

	public PictureVO setPath(String path) {
		this.path = path;
		return this;
	}

	public String getSize() {
		return size;
	}

	public PictureVO setSize(String size) {
		this.size = size;
		return this;
	}

	public String getMapType() {
		return mapType;
	}

	public PictureVO setMapType(String mapType) {
		this.mapType = mapType;
		return this;
	}

	public String getSourceType() {
		return sourceType;
	}

	public PictureVO setSourceType(String sourceType) {
		this.sourceType = sourceType;
		return this;
	}

	public Long getFileid() {
		return fileid;
	}

	public PictureVO setFileid(Long fileid) {
		this.fileid = fileid;
		return this;
	}

	public Long getFolderid() {
		return folderid;
	}

	public PictureVO setFolderid(Long folderid) {
		this.folderid = folderid;
		return this;
	}

	public String getFoldername() {
		return foldername;
	}

	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}

	@Override
	public PictureVO set(SourceMapDTO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getIntroduction())
			.setPath(entity.getPath())
			.setSize(entity.getSize())
			.setMapType(SourceMapType.fromString(entity.getMapType()).getName())
			.setSourceType(SourceType.fromString(entity.getSourceType()).getName())
			.setFileid(entity.getFileid())
			.setFolderid(entity.getFolderid())
			.setFoldername(entity.getFoldername());
		return this;
	}

	
	
}
