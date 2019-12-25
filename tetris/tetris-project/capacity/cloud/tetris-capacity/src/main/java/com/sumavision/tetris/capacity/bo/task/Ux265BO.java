package com.sumavision.tetris.capacity.bo.task;

/**
 * ux265编码参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 下午4:05:02
 */
public class Ux265BO {

	private String pixel_format;
	
	private Integer max_cu_size;
	
	private Integer max_tu_size;
	
	private Integer tu_depth;
	
	private Integer cu_depth;
	
	private Integer frame_num_threads;

	public String getPixel_format() {
		return pixel_format;
	}

	public Ux265BO setPixel_format(String pixel_format) {
		this.pixel_format = pixel_format;
		return this;
	}

	public Integer getMax_cu_size() {
		return max_cu_size;
	}

	public Ux265BO setMax_cu_size(Integer max_cu_size) {
		this.max_cu_size = max_cu_size;
		return this;
	}

	public Integer getMax_tu_size() {
		return max_tu_size;
	}

	public Ux265BO setMax_tu_size(Integer max_tu_size) {
		this.max_tu_size = max_tu_size;
		return this;
	}

	public Integer getTu_depth() {
		return tu_depth;
	}

	public Ux265BO setTu_depth(Integer tu_depth) {
		this.tu_depth = tu_depth;
		return this;
	}

	public Integer getCu_depth() {
		return cu_depth;
	}

	public Ux265BO setCu_depth(Integer cu_depth) {
		this.cu_depth = cu_depth;
		return this;
	}

	public Integer getFrame_num_threads() {
		return frame_num_threads;
	}

	public Ux265BO setFrame_num_threads(Integer frame_num_threads) {
		this.frame_num_threads = frame_num_threads;
		return this;
	}
	
}
