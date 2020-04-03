package com.sumavision.tetris.capacity.bo.task;

/**
 * 帧率变换参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月18日 下午1:50:26
 */
public class FpsConvertBO {
	
	private String plat = "cpu";
	
	private Integer nv_card_idx;

	private String fps;
	
	private String mode;

	public String getPlat() {
		return plat;
	}

	public FpsConvertBO setPlat(String plat) {
		this.plat = plat;
		return this;
	}

	public Integer getNv_card_idx() {
		return nv_card_idx;
	}

	public FpsConvertBO setNv_card_idx(Integer nv_card_idx) {
		this.nv_card_idx = nv_card_idx;
		return this;
	}

	public String getFps() {
		return fps;
	}

	public FpsConvertBO setFps(String fps) {
		this.fps = fps;
		return this;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
