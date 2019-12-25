package com.sumavision.tetris.capacity.bo.task;

/**
 * uux264编码参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 下午3:20:58
 */
public class Uux264BO {

	/** 原始数据格式 */
	private String pixel_format;
	
	/** 统计复用ID */
	private Integer stastical_id;
	
	private boolean stastical_flag;

	public String getPixel_format() {
		return pixel_format;
	}

	public Uux264BO setPixel_format(String pixel_format) {
		this.pixel_format = pixel_format;
		return this;
	}

	public Integer getStastical_id() {
		return stastical_id;
	}

	public Uux264BO setStastical_id(Integer stastical_id) {
		this.stastical_id = stastical_id;
		return this;
	}

	public boolean isStastical_flag() {
		return stastical_flag;
	}

	public Uux264BO setStastical_flag(boolean stastical_flag) {
		this.stastical_flag = stastical_flag;
		return this;
	}
	
}
