package com.sumavision.tetris.capacity.bo.task;

/**
 * 音频编码通用参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 上午9:55:59
 */
public class BaseAudioEncodeBO <V extends BaseAudioEncodeBO>{

	private Integer bitrate = 192000;
	
	private Integer sample_rate = 44100;
	
	private Integer sample_byte = 2;
	
	public Integer getBitrate() {
		return bitrate;
	}

	public V setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
		return (V)this;
	}

	public Integer getSample_rate() {
		return sample_rate;
	}

	public V setSample_rate(Integer sample_rate) {
		this.sample_rate = sample_rate;
		return (V)this;
	}

	public Integer getSample_byte() {
		return sample_byte;
	}

	public V setSample_byte(Integer sample_byte) {
		this.sample_byte = sample_byte;
		return (V)this;
	}
	
}
