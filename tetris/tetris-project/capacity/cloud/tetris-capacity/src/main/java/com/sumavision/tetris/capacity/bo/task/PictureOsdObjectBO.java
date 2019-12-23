package com.sumavision.tetris.capacity.bo.task;

/**
 * 单个图片osd参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月9日 下午3:31:47
 */
public class PictureOsdObjectBO {

	private Integer x = 0;
	
	private Integer y = 0;
	
	private Integer width = 0;
	
	private Integer height = 0;
	
	private boolean auto_scale = true;
	
	private Integer transparent = 100;
	
	private String path;

	public Integer getX() {
		return x;
	}

	public PictureOsdObjectBO setX(Integer x) {
		this.x = x;
		return this;
	}

	public Integer getY() {
		return y;
	}

	public PictureOsdObjectBO setY(Integer y) {
		this.y = y;
		return this;
	}

	public Integer getWidth() {
		return width;
	}

	public PictureOsdObjectBO setWidth(Integer width) {
		this.width = width;
		return this;
	}

	public Integer getHeight() {
		return height;
	}

	public PictureOsdObjectBO setHeight(Integer height) {
		this.height = height;
		return this;
	}

	public boolean isAuto_scale() {
		return auto_scale;
	}

	public PictureOsdObjectBO setAuto_scale(boolean auto_scale) {
		this.auto_scale = auto_scale;
		return this;
	}

	public Integer getTransparent() {
		return transparent;
	}

	public PictureOsdObjectBO setTransparent(Integer transparent) {
		this.transparent = transparent;
		return this;
	}

	public String getPath() {
		return path;
	}

	public PictureOsdObjectBO setPath(String path) {
		this.path = path;
		return this;
	}
	
}
