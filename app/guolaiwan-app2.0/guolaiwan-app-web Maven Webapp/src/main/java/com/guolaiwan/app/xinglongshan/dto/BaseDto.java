package com.guolaiwan.app.xinglongshan.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 数据传输实体基类
 */
public abstract class BaseDto implements java.io.Serializable {

	private static final long serialVersionUID = -4734655069106045342L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
