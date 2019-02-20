package com.guolaiwan.bussiness.chapman.source.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.chapman.enumeration.TargetType;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceMapType;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 资源归属映射
 * lvdeyang 2017年6月21日
 */
@Entity
@Table(name = "t_app_source_map")
public class SourceMapPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//资源
	private SourcePO source;
	
	//对映射描述
	private SourceMapType type;
	
	//资源归属
	private Long targetId;
	
	//资源归属描述
	private TargetType targetType;

	@ManyToOne
	@JoinColumn(name = "source_id")
	public SourcePO getSource() {
		return source;
	}

	public void setSource(SourcePO source) {
		this.source = source;
	}

	@Enumerated(EnumType.STRING)
	public SourceMapType getType() {
		return type;
	}

	public void setType(SourceMapType type) {
		this.type = type;
	}

	@Column(name = "target_id")
	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	@Column(name = "target_type")
	@Enumerated(EnumType.STRING)
	public TargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	
}
