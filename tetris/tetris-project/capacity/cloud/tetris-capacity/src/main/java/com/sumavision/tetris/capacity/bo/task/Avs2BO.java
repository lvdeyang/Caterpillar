package com.sumavision.tetris.capacity.bo.task;

/**
 * avs2参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2020年1月10日 下午2:49:50
 */
public class Avs2BO extends BaseEncodeBO<Avs2BO>{

	private String pixel_format;
	
	private Integer refine;
	
	private String profile;
	
	private Boolean bframe_adaptive;
	
	private String bframe_reference;
	
	private Integer ref_frames;
	
	private Integer vbv_buffer_size;

	public String getPixel_format() {
		return pixel_format;
	}

	public Avs2BO setPixel_format(String pixel_format) {
		this.pixel_format = pixel_format;
		return this;
	}

	public Integer getRefine() {
		return refine;
	}

	public Avs2BO setRefine(Integer refine) {
		this.refine = refine;
		return this;
	}

	public String getProfile() {
		return profile;
	}

	public Avs2BO setProfile(String profile) {
		this.profile = profile;
		return this;
	}

	public Boolean getBframe_adaptive() {
		return bframe_adaptive;
	}

	public Avs2BO setBframe_adaptive(Boolean bframe_adaptive) {
		this.bframe_adaptive = bframe_adaptive;
		return this;
	}

	public String getBframe_reference() {
		return bframe_reference;
	}

	public Avs2BO setBframe_reference(String bframe_reference) {
		this.bframe_reference = bframe_reference;
		return this;
	}

	public Integer getRef_frames() {
		return ref_frames;
	}

	public Avs2BO setRef_frames(Integer ref_frames) {
		this.ref_frames = ref_frames;
		return this;
	}

	public Integer getVbv_buffer_size() {
		return vbv_buffer_size;
	}

	public Avs2BO setVbv_buffer_size(Integer vbv_buffer_size) {
		this.vbv_buffer_size = vbv_buffer_size;
		return this;
	}
	
}
