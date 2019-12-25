package com.sumavision.tetris.capacity.bo.task;

/**
 * X264参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午10:45:40
 */
public class X264BO {

	private Integer keyint_min;
	
	private Integer keyint_max;
	
	/** 质量模式 slow/middle/fast */
	private String refine;
	
	/** 原始数据格式 yuv420/yuv422/yuv420_10bit/yuv422_10bit */
	private String pixel_format;
	
	/** b帧参考模式 None/Strict/Normal */
	private String bframe_reference;
	
	private boolean bframe_adaptive;
	
	/** 运动搜索范围 16-256 */
	private Integer me_range;
	
	private boolean low_latency;

	public Integer getKeyint_min() {
		return keyint_min;
	}

	public X264BO setKeyint_min(Integer keyint_min) {
		this.keyint_min = keyint_min;
		return this;
	}

	public Integer getKeyint_max() {
		return keyint_max;
	}

	public X264BO setKeyint_max(Integer keyint_max) {
		this.keyint_max = keyint_max;
		return this;
	}

	public String getRefine() {
		return refine;
	}

	public X264BO setRefine(String refine) {
		this.refine = refine;
		return this;
	}

	public String getPixel_format() {
		return pixel_format;
	}

	public X264BO setPixel_format(String pixel_format) {
		this.pixel_format = pixel_format;
		return this;
	}

	public String getBframe_reference() {
		return bframe_reference;
	}

	public X264BO setBframe_reference(String bframe_reference) {
		this.bframe_reference = bframe_reference;
		return this;
	}

	public boolean isBframe_adaptive() {
		return bframe_adaptive;
	}

	public X264BO setBframe_adaptive(boolean bframe_adaptive) {
		this.bframe_adaptive = bframe_adaptive;
		return this;
	}

	public Integer getMe_range() {
		return me_range;
	}

	public X264BO setMe_range(Integer me_range) {
		this.me_range = me_range;
		return this;
	}

	public boolean isLow_latency() {
		return low_latency;
	}

	public X264BO setLow_latency(boolean low_latency) {
		this.low_latency = low_latency;
		return this;
	}
	
}
