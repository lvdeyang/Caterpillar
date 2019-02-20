package com.guolaiwan.bussiness.chapman.source.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 文件二进制内容
 * lvdeyang 2017年6月27日
 */
@Entity
@Table(name = "t_app_file")
public class FilePO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	//资源内容--存二进制
	private byte[] content;
	
	//关联资源
	private SourcePO source;

	@Column(columnDefinition="LONGBLOB", nullable=true)
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@OneToOne(mappedBy="file", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public SourcePO getSource() {
		return source;
	}

	public void setSource(SourcePO source) {
		this.source = source;
	}
	
}
