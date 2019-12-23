package com.sumavision.tetris.capacity.bo.task;

/**
 * aac编码参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 上午9:12:45
 */
public class AacBO extends BaseAudioEncodeBO<AacBO>{
	
	/** 协议中该字段去了，这里留着看映射关系 */
	private Integer channels = 1;

	/** 声道布局，stereo等 */
	private String channel_layout = "mono";
	
	/** mpeg2-aac-lc/mpeg2-he-aac-lcmpeg2-he-aac-v2-lc/mpeg4-aac-lc
	/mpeg4-he-aac-lcmpeg4-he-aac-v2-lc/mpeg4-aac-ld/mpeg4-aac-eld */
	private String type = "mpeg2-aac-lc";
	
	private String sample_fmt;

	public Integer getChannels() {
		return channels;
	}

	public AacBO setChannels(Integer channels) {
		this.channels = channels;
		return this;
	}

	public String getChannel_layout() {
		return channel_layout;
	}

	public AacBO setChannel_layout(String channel_layout) {
		this.channel_layout = channel_layout;
		return this;
	}

	public String getSample_fmt() {
		return sample_fmt;
	}

	public AacBO setSample_fmt(String sample_fmt) {
		this.sample_fmt = sample_fmt;
		return this;
	}

	public String getType() {
		return type;
	}

	public AacBO setType(String type) {
		this.type = type;
		return this;
	}
	
	public AacBO setAac(){
		this.setBitrate(192000)
			.setSample_rate(44100)
			.setSample_fmt("s16")
			.setChannels(1)
			.setChannel_layout("mono")
			.setType("mpeg-aac-lc");
		return this;
	}
	
}
