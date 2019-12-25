package com.sumavision.tetris.business.common.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.sumavision.tetris.business.common.enumeration.BusinessType;
import com.sumavision.tetris.orm.po.AbstractBasePO;

@Entity
@Table(name = "TETRIS_CAPACITY_TASK_INPUT", uniqueConstraints = {@UniqueConstraint(columnNames={"uniq"})})
public class TaskInputPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	private Long version;
	
	private String taskUuid;
	
	private String input;
	
	/** 保证源的唯一性，yjgb: ip@port; 直播: url; 收录: url */
	private String uniq;
	
	/** 计数 */
	private Integer count = 1;
	
	private BusinessType type;

	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getTaskUuid() {
		return taskUuid;
	}

	public void setTaskUuid(String taskUuid) {
		this.taskUuid = taskUuid;
	}

	@Column(name = "INPUT", columnDefinition = "longtext")
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getUniq() {
		return uniq;
	}

	public void setUniq(String uniq) {
		this.uniq = uniq;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Enumerated(EnumType.STRING)
	public BusinessType getType() {
		return type;
	}

	public void setType(BusinessType type) {
		this.type = type;
	}
	
}
