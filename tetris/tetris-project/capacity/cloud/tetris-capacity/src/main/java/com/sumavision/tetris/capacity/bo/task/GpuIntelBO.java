package com.sumavision.tetris.capacity.bo.task;

/**
 * GPU-Intel编码参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月13日 下午1:17:26
 */
public class GpuIntelBO {

	private String preset = "fast";

	public String getPreset() {
		return preset;
	}

	public GpuIntelBO setPreset(String preset) {
		this.preset = preset;
		return this;
	}
	
}
