package com.sumavision.tetris.capacity.bo.task;

/**
 * 裁剪参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午8:09:11
 */
public class CutBO {
	
	private String plat = "cpu";
	
	private Integer nv_card_idx;

	private Integer width;
	
	private Integer height;
	
	private Integer x;
	
	private Integer y;

	public String getPlat() {
		return plat;
	}

	public CutBO setPlat(String plat) {
		this.plat = plat;
		return this;
	}

	public Integer getNv_card_idx() {
		return nv_card_idx;
	}

	public CutBO setNv_card_idx(Integer nv_card_idx) {
		this.nv_card_idx = nv_card_idx;
		return this;
	}

	public Integer getWidth() {
		return width;
	}

	public CutBO setWidth(Integer width) {
		this.width = width;
		return this;
	}

	public Integer getHeight() {
		return height;
	}

	public CutBO setHeight(Integer height) {
		this.height = height;
		return this;
	}

	public Integer getX() {
		return x;
	}

	public CutBO setX(Integer x) {
		this.x = x;
		return this;
	}

	public Integer getY() {
		return y;
	}

	public CutBO setY(Integer y) {
		this.y = y;
		return this;
	}
	
}
