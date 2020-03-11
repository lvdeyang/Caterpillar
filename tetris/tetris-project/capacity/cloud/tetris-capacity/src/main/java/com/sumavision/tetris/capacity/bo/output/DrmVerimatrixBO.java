package com.sumavision.tetris.capacity.bo.output;

/**
 * 类型概述<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2020年2月20日 下午4:15:31
 */
public class DrmVerimatrixBO {

	private String resource_id;
	
	private String server_addr;

	public String getResource_id() {
		return resource_id;
	}

	public DrmVerimatrixBO setResource_id(String resource_id) {
		this.resource_id = resource_id;
		return this;
	}

	public String getServer_addr() {
		return server_addr;
	}

	public DrmVerimatrixBO setServer_addr(String server_addr) {
		this.server_addr = server_addr;
		return this;
	}
	
}
