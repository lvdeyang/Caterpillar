package com.sumavision.tetris.capacity.bo.task;

import java.util.List;

/**
 * 区域模糊参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午8:47:54
 */
public class FuzzyBO {

	private List<FuzzyObjectBO> fuzzys;
	
	/** 范围 */
	private Integer mosaic_radius;

	public List<FuzzyObjectBO> getFuzzys() {
		return fuzzys;
	}

	public FuzzyBO setFuzzys(List<FuzzyObjectBO> fuzzys) {
		this.fuzzys = fuzzys;
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
