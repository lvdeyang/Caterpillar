package com.sumavision.tetris.capacity.bo.task;

/**
 * 图像增强参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午8:54:35
 */
public class EnhanceBO {

	/** 亮度 -255-255 */
	private Integer brightness;
	
	/** 对比度 -100-100 */
	private Integer contrast;
	
	/** 饱和度 -100-100 */
	private Integer saturation;
	
	/** 去燥 off/gaussian/median/3d */
	private String denosie;
	
	/** 锐化 off/on */
	private String sharpen;
	
	/** 颜色空间 BT709/BT2020 */
	private String colorspace;
	
	/** 传输特质 BT709/BT2020 */
	private String colortransfer;
	
	/** 颜色基准 BT709/BT2020 */
	private String colorprimaries;
	
	/** 色彩范围 limited/full */
	private String colorrange;

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

	public String getDenosie() {
		return denosie;
	}

	public EnhanceBO setDenosie(String denosie) {
		this.denosie = denosie;
		return this;
	}

	public String getSharpen() {
		return sharpen;
	}

	public EnhanceBO setSharpen(String sharpen) {
		this.sharpen = sharpen;
		return this;
	}

	public String getColorspace() {
		return colorspace;
	}

	public EnhanceBO setColorspace(String colorspace) {
		this.colorspace = colorspace;
		return this;
	}

	public String getColortransfer() {
		return colortransfer;
	}

	public EnhanceBO setColortransfer(String colortransfer) {
		this.colortransfer = colortransfer;
		return this;
	}

	public String getColorprimaries() {
		return colorprimaries;
	}

	public EnhanceBO setColorprimaries(String colorprimaries) {
		this.colorprimaries = colorprimaries;
		return this;
	}

	public String getColorrange() {
		return colorrange;
	}

	public EnhanceBO setColorrange(String colorrange) {
		this.colorrange = colorrange;
		return this;
	}
	
}
