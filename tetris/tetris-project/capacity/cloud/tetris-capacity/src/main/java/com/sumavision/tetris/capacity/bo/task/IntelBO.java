package com.sumavision.tetris.capacity.bo.task;

/**
 * intel编码参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 下午3:26:17
 */
public class IntelBO {

	private boolean bframe_adaptive;
	
	private boolean bframe_reference;
	
	/** 分像素精度 1-11 */
	private Integer refine;
	
	private Integer max_bframe;
	
	private Integer quality;

	public boolean isBframe_adaptive() {
		return bframe_adaptive;
	}

	public IntelBO setBframe_adaptive(boolean bframe_adaptive) {
		this.bframe_adaptive = bframe_adaptive;
		return this;
	}

	public boolean isBframe_reference() {
		return bframe_reference;
	}

	public IntelBO setBframe_reference(boolean bframe_reference) {
		this.bframe_reference = bframe_reference;
		return this;
	}

	public Integer getRefine() {
		return refine;
	}

	public IntelBO setRefine(Integer refine) {
		this.refine = refine;
		return this;
	}

	public Integer getMax_bframe() {
		return max_bframe;
	}

	public IntelBO setMax_bframe(Integer max_bframe) {
		this.max_bframe = max_bframe;
		return this;
	}

	public Integer getQuality() {
		return quality;
	}

	public IntelBO setQuality(Integer quality) {
		this.quality = quality;
		return this;
	}
	
}
