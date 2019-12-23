package com.sumavision.tetris.capacity.bo.input;

import java.util.List;

/**
 * IGMPV3参数，用于指定组播接收控制<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月28日 下午5:04:30
 */
public class Igmpv3BO {

	/** igmpv3控制模式 include/exclude */
	private String mode;
	
	/** 控制ip列表  */
	private List<String> ip_array;

	public String getMode() {
		return mode;
	}

	public Igmpv3BO setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public List<String> getIp_array() {
		return ip_array;
	}

	public Igmpv3BO setIp_array(List<String> ip_array) {
		this.ip_array = ip_array;
		return this;
	}
	
}
