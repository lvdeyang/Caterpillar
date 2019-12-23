package com.sumavision.tetris.capacity.bo.output;

import java.util.List;

/**
 * 媒体分组BO<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午9:10:16
 */
public class OutputMediaGroupBO {

	private Integer bandwidth;
	
	private Integer video;
	
	private List<OutputIndexBO> audio;
	
	private List<OutputIndexBO> subtitle;

	public Integer getBandwidth() {
		return bandwidth;
	}

	public OutputMediaGroupBO setBandwidth(Integer bandwidth) {
		this.bandwidth = bandwidth;
		return this;
	}

	public Integer getVideo() {
		return video;
	}

	public OutputMediaGroupBO setVideo(Integer video) {
		this.video = video;
		return this;
	}

	public List<OutputIndexBO> getAudio() {
		return audio;
	}

	public OutputMediaGroupBO setAudio(List<OutputIndexBO> audio) {
		this.audio = audio;
		return this;
	}

	public List<OutputIndexBO> getSubtitle() {
		return subtitle;
	}

	public OutputMediaGroupBO setSubtitle(List<OutputIndexBO> subtitle) {
		this.subtitle = subtitle;
		return this;
	}
	
}
