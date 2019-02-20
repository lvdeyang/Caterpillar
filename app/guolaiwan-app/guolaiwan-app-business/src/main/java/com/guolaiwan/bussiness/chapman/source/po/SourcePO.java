package com.guolaiwan.bussiness.chapman.source.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.chapman.source.enumeration.SourceType;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 资源
 * lvdeyang 2017年6月21日
 */
@Entity
@Table(name = "t_app_source")
public class SourcePO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//资源类型
	private SourceType type;
	
	//资源路径
	private String path;
	
	//资源简介
	private String introduction;
	
	//资源大小
	private String size;
	
	//隶属文件夹
	private SourceFolderPO sourceFolder;
	
	//关联文件
	private FilePO file;
	
	//资源归属映射
	private Set<SourceMapPO> sourceMaps;

	@Enumerated(EnumType.STRING)
	public SourceType getType() {
		return type;
	}

	public void setType(SourceType type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@ManyToOne
	@JoinColumn(name = "source_folder_id")
	public SourceFolderPO getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(SourceFolderPO sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	@OneToOne
	@JoinColumn(name = "file_id")
	public FilePO getFile() {
		return file;
	}

	public void setFile(FilePO file) {
		this.file = file;
	}

	@OneToMany(mappedBy = "source", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SourceMapPO> getSourceMaps() {
		return sourceMaps;
	}

	public void setSourceMaps(Set<SourceMapPO> sourceMaps) {
		this.sourceMaps = sourceMaps;
	}
	
}
