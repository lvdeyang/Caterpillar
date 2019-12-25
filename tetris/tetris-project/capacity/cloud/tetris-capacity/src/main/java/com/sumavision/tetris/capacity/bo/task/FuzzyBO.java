package com.sumavision.tetris.capacity.bo.task;

/**
 * 区域模糊参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午8:47:54
 */
public class FuzzyBO {

	private String position;
	
	private String zone;
	
	/** 模糊效果 fuzzy/mosaic */
	private String fuzzy_effect;
	
	/** 范围 */
	private Integer mosaic_radius;

	public String getPosition() {
		return position;
	}

	public FuzzyBO setPosition(String position) {
		this.position = position;
		return this;
	}

	public String getZone() {
		return zone;
	}

	public FuzzyBO setZone(String zone) {
		this.zone = zone;
		return this;
	}

	public String getFuzzy_effect() {
		return fuzzy_effect;
	}

	public FuzzyBO setFuzzy_effect(String fuzzy_effect) {
		this.fuzzy_effect = fuzzy_effect;
		return this;
	}

	public Integer getMosaic_radius() {
		return mosaic_radius;
	}

	public FuzzyBO setMosaic_radius(Integer mosaic_radius) {
		this.mosaic_radius = mosaic_radius;
		return this;
	}
	
}
