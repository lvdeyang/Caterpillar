package com.sumavision.tetris.capacity.bo.task;

/**
 * 重采样参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午9:27:46
 */
public class ResampleBO {

	/** 采样率 16-96000 */
	private Integer sample_rate = 44100;
	
	/** 协议中该字段去了，这里留着看映射关系 */
	private Integer channels = 1;
	
	/** 声道布局  mono/stereo */
	private String channel_layout = "mono";
	
	/** 采样位数 u8/u8_planner/s16/s16_planner/s32/s32_planner
	 *  /flt/flt_planner/dbl/dbl_palnner
	 */
	private String format = "s16";

	public Integer getSample_rate() {
		return sample_rate;
	}

	public ResampleBO setSample_rate(Integer sample_rate) {
		this.sample_rate = sample_rate;
		return this;
	}

	public Integer getChannels() {
		return channels;
	}

	public ResampleBO setChannels(Integer channels) {
		this.channels = channels;
		return this;
	}

	public String getChannel_layout() {
		return channel_layout;
	}

	public ResampleBO setChannel_layout(String channel_layout) {
		this.channel_layout = channel_layout;
		return this;
	}

	public String getFormat() {
		return format;
	}

	public ResampleBO setFormat(String format) {
		this.format = format;
		return this;
	}
	
}
