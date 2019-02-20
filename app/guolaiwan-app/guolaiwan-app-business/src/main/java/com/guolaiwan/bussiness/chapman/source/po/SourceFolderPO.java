package com.guolaiwan.bussiness.chapman.source.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceFolderType;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 资源文件夹
 * lvdeyang 2017年6月21日
 */
@Entity
@Table(name = "t_app_source_folder")
public class SourceFolderPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	//文件夹名称
	private String foldername;

	//隶属商户
	private ChapmanPO chapman;
	
	//文件夹类型
	private SourceFolderType type;
	
	//父文件夹
	private SourceFolderPO parent;
	
	//子文件夹
	private Set<SourceFolderPO> children;
	
	//资源列表
	private Set<SourcePO> sources;
	
	public String getFoldername() {
		return foldername;
	}

	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}

	@ManyToOne
	@JoinColumn(name = "chapman_id")
	public ChapmanPO getChapman() {
		return chapman;
	}

	public void setChapman(ChapmanPO chapman) {
		this.chapman = chapman;
	}
	
	@Enumerated(EnumType.STRING)
	public SourceFolderType getType() {
		return type;
	}

	public void setType(SourceFolderType type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name = "source_folder_id")
	public SourceFolderPO getParent() {
		return parent;
	}

	public void setParent(SourceFolderPO parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SourceFolderPO> getChildren() {
		return children;
	}

	public void setChildren(Set<SourceFolderPO> children) {
		this.children = children;
	}

	@OneToMany(mappedBy = "sourceFolder", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SourcePO> getSources() {
		return sources;
	}

	public void setSources(Set<SourcePO> sources) {
		this.sources = sources;
	}
	
}
