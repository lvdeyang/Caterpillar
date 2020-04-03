package com.sumavision.tetris.capacity.bo.task;

/**
 * HDR参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2020年2月12日 下午4:10:00
 */
public class HdrBO {
	
	private String plat = "cpu";
	
	private Integer nv_card_idx;

	private String colorspace;
	
	private String colortransfer;
	
	private String colorprimaries;
	
	private String colorrange;

	public String getPlat() {
		return plat;
	}

	public HdrBO setPlat(String plat) {
		this.plat = plat;
		return this;
	}

	public Integer getNv_card_idx() {
		return nv_card_idx;
	}

	public HdrBO setNv_card_idx(Integer nv_card_idx) {
		this.nv_card_idx = nv_card_idx;
		return this;
	}

	public String getColorspace() {
		return colorspace;
	}

	public HdrBO setColorspace(String colorspace) {
		this.colorspace = colorspace;
		return this;
	}

	public String getColortransfer() {
		return colortransfer;
	}

	public HdrBO setColortransfer(String colortransfer) {
		this.colortransfer = colortransfer;
		return this;
	}

	public String getColorprimaries() {
		return colorprimaries;
	}

	public HdrBO setColorprimaries(String colorprimaries) {
		this.colorprimaries = colorprimaries;
		return this;
	}

	public String getColorrange() {
		return colorrange;
	}

	public HdrBO setColorrange(String colorrange) {
		this.colorrange = colorrange;
		return this;
	}
	
}