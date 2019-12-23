package com.sumavision.tetris.capacity.bo.input;

/**
 * 字幕参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月28日 下午4:06:06
 */
public class ProgramSubtitleBO {

	/** 字幕pid 0~8191 */
	private Integer pid;
	
	/** 字幕编码格式 dvd_subtitle/dvb_subtitle/text/dvb_teletext/srt/webvtt */
	private String type;
	
	/** 字幕语言 */
	private String language;
	
	/** 解码方式 cpu */
	private String decode_mode;

	public Integer getPid() {
		return pid;
	}

	public ProgramSubtitleBO setPid(Integer pid) {
		this.pid = pid;
		return this;
	}

	public String getType() {
		return type;
	}

	public ProgramSubtitleBO setType(String type) {
		this.type = type;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public ProgramSubtitleBO setLanguage(String language) {
		this.language = language;
		return this;
	}

	public String getDecode_mode() {
		return decode_mode;
	}

	public ProgramSubtitleBO setDecode_mode(String decode_mode) {
		this.decode_mode = decode_mode;
		return this;
	}
	
}
