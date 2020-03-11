package com.sumavision.tetris.capacity.bo.task;

/**
 * 图像增强参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午8:54:35
 */
public class EnhanceBO {
	
	private String plat = "cpu";
	
	private Integer nv_card_idx;

	/** 亮度 -255-255 */
	private Integer brightness;
	
	/** 色度 */
	private Integer chrome;
	
	/** 对比度 -100-100 */
	private Integer contrast;
	
	/** 饱和度 -100-100 */
	private Integer saturation;
	
	/** Gamma变换 */
	private Integer gamma;

	public String getPlat() {
		return plat;
	}

	public EnhanceBO setPlat(String plat) {
		this.plat = plat;
		return this;
	}

	public Integer getNv_card_idx() {
		return nv_card_idx;
	}

	public EnhanceBO setNv_card_idx(Integer nv_card_idx) {
		this.nv_card_idx = nv_card_idx;
		return this;
	}

	public Integer getBrightness() {
		return brightness;
	}

	public EnhanceBO setBrightness(Integer brightness) {
		this.brightness = brightness;
		return this;
	}

	public Integer getContrast() {
		return contrast;
	}

	public EnhanceBO setContrast(Integer contrast) {
		this.contrast = contrast;
		return this;
	}

	public Integer getSaturation() {
		return saturation;
	}

	public EnhanceBO setSaturation(Integer saturation) {
		this.saturation = saturation;
		return this;
	}

	public Integer getChrome() {
		return chrome;
	}

	public EnhanceBO setChrome(Integer chrome) {
		this.chrome = chrome;
		return this;
	}

	public Integer getGamma() {
		return gamma;
	}

	public EnhanceBO setGamma(Integer gamma) {
		this.gamma = gamma;
		return this;
	}
	
}
