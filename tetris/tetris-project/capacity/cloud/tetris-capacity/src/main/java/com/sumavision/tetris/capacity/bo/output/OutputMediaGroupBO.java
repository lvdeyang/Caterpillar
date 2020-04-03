package com.sumavision.tetris.capacity.bo.output;

import java.util.List;

/**
 * 媒体分组BO<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午9:10:16
 */
public class OutputMediaGroupBO {
	
	private String seg_format;

	private Integer bandwidth;
	
	private String video_task_id;
	
	private String video_encode_id;
	
	private Integer video_bitrate;
	
	private List<OutputAudioBO> audio_array;
	
	private List<OutputSubtitleBO> subtitle_array;

	public String getSeg_format() {
		return seg_format;
	}

	public OutputMediaGroupBO setSeg_format(String seg_format) {
		this.seg_format = seg_format;
		return this;
	}

	public Integer getBandwidth() {
		return bandwidth;
	}

	public OutputMediaGroupBO setBandwidth(Integer bandwidth) {
		this.bandwidth = bandwidth;
		return this;
	}

	public String getVideo_task_id() {
		return video_task_id;
	}

	public OutputMediaGroupBO setVideo_task_id(String video_task_id) {
		this.video_task_id = video_task_id;
		return this;
	}

	public String getVideo_encode_id() {
		return video_encode_id;
	}

	public OutputMediaGroupBO setVideo_encode_id(String video_encode_id) {
		this.video_encode_id = video_encode_id;
		return this;
	}

	public Integer getVideo_bitrate() {
		return video_bitrate;
	}

	public OutputMediaGroupBO setVideo_bitrate(Integer video_bitrate) {
		this.video_bitrate = video_bitrate;
		return this;
	}

	public List<OutputAudioBO> getAudio_array() {
		return audio_array;
	}

	public OutputMediaGroupBO setAudio_array(List<OutputAudioBO> audio_array) {
		this.audio_array = audio_array;
		return this;
	}

	public List<OutputSubtitleBO> getSubtitle_array() {
		return subtitle_array;
	}

	public OutputMediaGroupBO setSubtitle_array(List<OutputSubtitleBO> subtitle_array) {
		this.subtitle_array = subtitle_array;
		return this;
	}
	
}
