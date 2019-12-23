package com.sumavision.tetris.capacity.bo.task;

/**
 * G711参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 上午10:09:43
 */
public class G711BO extends BaseAudioEncodeBO<G711BO>{
	
	private Integer channels;

	private String ch_layout;
	
	/** 10-50 */
	private Integer ptime;

	public Integer getChannels() {
		return channels;
	}

	public G711BO setChannels(Integer channels) {
		this.channels = channels;
		return this;
	}

	public String getCh_layout() {
		return ch_layout;
	}

	public G711BO setCh_layout(String ch_layout) {
		this.ch_layout = ch_layout;
		return this;
	}

	public Integer getPtime() {
		return ptime;
	}

	public G711BO setPtime(Integer ptime) {
		this.ptime = ptime;
		return this;
	}
	
}
