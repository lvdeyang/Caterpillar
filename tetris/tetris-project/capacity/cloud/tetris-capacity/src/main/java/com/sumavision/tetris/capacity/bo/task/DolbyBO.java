package com.sumavision.tetris.capacity.bo.task;

/**
 * dolby参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 上午9:17:14
 */
public class DolbyBO extends BaseAudioEncodeBO<DolbyBO>{

	private Integer channels = 2;
	
	private String ch_layout = "stereo";
	
	/** ac3/eac3 */
	private String type = "ac3";	

	public Integer getChannels() {
		return channels;
	}

	public DolbyBO setChannels(Integer channels) {
		this.channels = channels;
		return this;
	}

	public String getCh_layout() {
		return ch_layout;
	}

	public void setCh_layout(String ch_layout) {
		this.ch_layout = ch_layout;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
