package com.sumavision.tetris.business.common.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.sumavision.tetris.business.common.enumeration.BusinessType;
import com.sumavision.tetris.orm.po.AbstractBasePO;

@Entity
@Table(name = "TETRIS_CAPACITY_TASK_OUTPUT")
public class TaskOutputPO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;

	private String taskUuid;
	
	private Long inputId;
	
	private String task;
	
	private String output;
	
	private BusinessType type;
	
	/** **************
	 *    yjgb使用          *
	 ** ************ */
	/** 是否录制 */
	private boolean isRecord = false;
	
	/** 录制地址 */
	private String recordAddress;
	
	/** 媒体类型 */
	private String mediaType;
	
	/** 录制完成回调地址 */
	private String recordCallbackUrl;
	
	public String getTaskUuid() {
		return taskUuid;
	}

	public void setTaskUuid(String taskUuid) {
		this.taskUuid = taskUuid;
	}

	public Long getInputId() {
		return inputId;
	}

	public void setInputId(Long inputId) {
		this.inputId = inputId;
	}

	@Column(name = "TASK", columnDefinition = "longtext")
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Column(name = "OUTPUT", columnDefinition = "longtext")
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public boolean isRecord() {
		return isRecord;
	}

	public void setRecord(boolean isRecord) {
		this.isRecord = isRecord;
	}

	public String getRecordAddress() {
		return recordAddress;
	}

	public void setRecordAddress(String recordAddress) {
		this.recordAddress = recordAddress;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	public String getRecordCallbackUrl() {
		return recordCallbackUrl;
	}

	public void setRecordCallbackUrl(String recordCallbackUrl) {
		this.recordCallbackUrl = recordCallbackUrl;
	}

	@Enumerated(EnumType.STRING)
	public BusinessType getType() {
		return type;
	}

	public void setType(BusinessType type) {
		this.type = type;
	}
	
}
