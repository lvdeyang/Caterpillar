package com.sumavision.tetris.capacity.bo.task;

/**
 * 字幕参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午8:33:19
 */
@Deprecated
public class SubtitleBO {

	/** 滚动方向 left/right */
	private String track_type;
	
	/** 字幕内容 */
	private String text_content;
	
	/** 字体类型 */
	private String font_type;
	
	/** 起始位置 */
	private String position;
	
	/** 区域 */
	private String zone;
	
	private String font_color;
	
	/** 滚动速度 1-300 */
	private Integer track_speed;
	
	/** 字体大小 10-150 */
	private Integer font_size;
	
	private boolean show_bkg;
	
	private boolean show_outline;
	
	private String back_color;
	
	/** 边框颜色 */
	private String outline_color;

	public String getTrack_type() {
		return track_type;
	}

	public SubtitleBO setTrack_type(String track_type) {
		this.track_type = track_type;
		return this;
	}

	public String getText_content() {
		return text_content;
	}

	public SubtitleBO setText_content(String text_content) {
		this.text_content = text_content;
		return this;
	}

	public String getFont_type() {
		return font_type;
	}

	public SubtitleBO setFont_type(String font_type) {
		this.font_type = font_type;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public SubtitleBO setPosition(String position) {
		this.position = position;
		return this;
	}

	public String getZone() {
		return zone;
	}

	public SubtitleBO setZone(String zone) {
		this.zone = zone;
		return this;
	}

	public String getFont_color() {
		return font_color;
	}

	public SubtitleBO setFont_color(String font_color) {
		this.font_color = font_color;
		return this;
	}

	public Integer getTrack_speed() {
		return track_speed;
	}

	public SubtitleBO setTrack_speed(Integer track_speed) {
		this.track_speed = track_speed;
		return this;
	}

	public Integer getFont_size() {
		return font_size;
	}

	public SubtitleBO setFont_size(Integer font_size) {
		this.font_size = font_size;
		return this;
	}

	public boolean isShow_bkg() {
		return show_bkg;
	}

	public SubtitleBO setShow_bkg(boolean show_bkg) {
		this.show_bkg = show_bkg;
		return this;
	}

	public boolean isShow_outline() {
		return show_outline;
	}

	public SubtitleBO setShow_outline(boolean show_outline) {
		this.show_outline = show_outline;
		return this;
	}

	public String getBack_color() {
		return back_color;
	}

	public SubtitleBO setBack_color(String back_color) {
		this.back_color = back_color;
		return this;
	}

	public String getOutline_color() {
		return outline_color;
	}

	public SubtitleBO setOutline_color(String outline_color) {
		this.outline_color = outline_color;
		return this;
	}
	
}
