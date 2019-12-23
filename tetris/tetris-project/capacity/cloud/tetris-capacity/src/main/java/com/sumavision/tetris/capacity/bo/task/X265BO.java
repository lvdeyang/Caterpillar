package com.sumavision.tetris.capacity.bo.task;

/**
 * X265编码参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 下午3:57:02
 */
public class X265BO {

	private String preset;
	
	private String refine;
	
	private String pixel_format;
	
	private String bframe_reference;

	public String getPreset() {
		return preset;
	}

	public X265BO setPreset(String preset) {
		this.preset = preset;
		return this;
	}

	public String getRefine() {
		return refine;
	}

	public X265BO setRefine(String refine) {
		this.refine = refine;
		return this;
	}

	public String getPixel_format() {
		return pixel_format;
	}

	public X265BO setPixel_format(String pixel_format) {
		this.pixel_format = pixel_format;
		return this;
	}

	public String getBframe_reference() {
		return bframe_reference;
	}

	public X265BO setBframe_reference(String bframe_reference) {
		this.bframe_reference = bframe_reference;
		return this;
	}
	
}
