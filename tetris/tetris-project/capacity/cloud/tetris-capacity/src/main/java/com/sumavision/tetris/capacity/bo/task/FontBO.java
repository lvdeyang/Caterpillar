package com.sumavision.tetris.capacity.bo.task;

/**
 * 字体参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月9日 下午3:07:34
 */
public class FontBO {

	private String color;
	
	private Integer size;
	
	private String family;
	
	private boolean has_border;
	
	private String border_color;

	public String getColor() {
		return color;
	}

	public FontBO setColor(String color) {
		this.color = color;
		return this;
	}

	public Integer getSize() {
		return size;
	}

	public FontBO setSize(Integer size) {
		this.size = size;
		return this;
	}

	public String getFamily() {
		return family;
	}

	public FontBO setFamily(String family) {
		this.family = family;
		return this;
	}

	public boolean isHas_border() {
		return has_border;
	}

	public FontBO setHas_border(boolean has_border) {
		this.has_border = has_border;
		return this;
		
	}

	public String getBorder_color() {
		return border_color;
	}

	public FontBO setBorder_color(String border_color) {
		this.border_color = border_color;
		return this;
	}
	
}
