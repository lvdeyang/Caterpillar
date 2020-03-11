package com.sumavision.tetris.capacity.bo.task;

public class CudaBO {

	private String pixel_format;
	
	private Integer nv_card_idx;
	
	private String tier;

	public String getPixel_format() {
		return pixel_format;
	}

	public CudaBO setPixel_format(String pixel_format) {
		this.pixel_format = pixel_format;
		return this;
	}

	public Integer getNv_card_idx() {
		return nv_card_idx;
	}

	public CudaBO setNv_card_idx(Integer nv_card_idx) {
		this.nv_card_idx = nv_card_idx;
		return this;
	}

	public String getTier() {
		return tier;
	}

	public CudaBO setTier(String tier) {
		this.tier = tier;
		return this;
	}
	
}
