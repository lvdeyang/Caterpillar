package com.sumavision.tetris.capacity.bo.task;

/**
 * osd参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午8:32:10
 */
public class OsdBO {

	private String track_type = "none";
	
	private Integer track_speed = 0;
	
	private String content;
	
	private Integer x;
	
	private Integer y;
	
	private Integer width;
	
	private Integer height;
	
	private FontBO font;
	
	private boolean has_background;
	
	private String background_color;

	public String getTrack_type() {
		return track_type;
	}

	public OsdBO setTrack_type(String track_type) {
		this.track_type = track_type;
		return this;
	}

	public Integer getTrack_speed() {
		return track_speed;
	}

	public OsdBO setTrack_speed(Integer track_speed) {
		this.track_speed = track_speed;
		return this;
	}

	public String getContent() {
		return content;
	}

	public OsdBO setContent(String content) {
		this.content = content;
		return this;
	}

	public Integer getX() {
		return x;
	}

	public OsdBO setX(Integer x) {
		this.x = x;
		return this;
	}

	public Integer getY() {
		return y;
	}

	public OsdBO setY(Integer y) {
		this.y = y;
		return this;
	}

	public Integer getWidth() {
		return width;
	}

	public OsdBO setWidth(Integer width) {
		this.width = width;
		return this;
	}

	public Integer getHeight() {
		return height;
	}

	public OsdBO setHeight(Integer height) {
		this.height = height;
		return this;
	}

	public FontBO getFont() {
		return font;
	}

	public OsdBO setFont(FontBO font) {
		this.font = font;
		return this;
	}

	public boolean isHas_background() {
		return has_background;
	}

	public OsdBO setHas_background(boolean has_background) {
		this.has_background = has_background;
		return this;
	}

	public String getBackground_color() {
		return background_color;
	}

	public OsdBO setBackground_color(String background_color) {
		this.background_color = background_color;
		return this;
	}
}
