package com.sumavision.tetris.capacity.bo.input;

/**
 * 音频参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月28日 下午3:54:00
 */
public class ProgramAudioBO {

	/** 音频pid 0~8191 */
	private Integer pid;
	
	/** 音频编码格式 mp1/mp2/mp3/aac/heaac/ac3/eac3
	 * /pcm_alaw/pac_mulaw/g729a/g729/adpcm */
	private String type;
	
	private Integer bitrate;
	
	/** 解码方式 cpu */
	private String decode_mode;
	
	private Integer nv_card_idx;

	public Integer getPid() {
		return pid;
	}

	public ProgramAudioBO setPid(Integer pid) {
		this.pid = pid;
		return this;
	}

	public String getType() {
		return type;
	}

	public ProgramAudioBO setType(String type) {
		this.type = type;
		return this;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	public ProgramAudioBO setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
		return this;
	}

	public String getDecode_mode() {
		return decode_mode;
	}

	public ProgramAudioBO setDecode_mode(String decode_mode) {
		this.decode_mode = decode_mode;
		return this;
	}

	public Integer getNv_card_idx() {
		return nv_card_idx;
	}

	public ProgramAudioBO setNv_card_idx(Integer nv_card_idx) {
		this.nv_card_idx = nv_card_idx;
		return this;
	}
	
}
