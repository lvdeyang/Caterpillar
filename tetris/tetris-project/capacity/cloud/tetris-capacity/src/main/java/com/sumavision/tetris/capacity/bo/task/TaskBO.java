package com.sumavision.tetris.capacity.bo.task;

import java.util.List;

/**
 * 任务参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午3:59:07
 */
public class TaskBO {

	/** 任务id */
	private String id;
	
	/** 任务类型 video/audio/subtitle/passby */
	private String type;
	
	private TaskSourceBO es_source;
	
	private TaskSourceBO raw_source;
	
	private TaskSourceBO passby_source;
	
	private VideoMixSourceBO video_mix_source;
	
	private AudioMixSourceBO audio_mix_source;
	
	private List<PreProcessingBO> decode_process_array;
	
	private List<EncodeBO> encode_array;

	public String getId() {
		return id;
	}

	public TaskBO setId(String id) {
		this.id = id;
		return this;
	}

	public String getType() {
		return type;
	}

	public TaskBO setType(String type) {
		this.type = type;
		return this;
	}

	public TaskSourceBO getEs_source() {
		return es_source;
	}

	public TaskBO setEs_source(TaskSourceBO es_source) {
		this.es_source = es_source;
		return this;
	}

	public TaskSourceBO getRaw_source() {
		return raw_source;
	}

	public TaskBO setRaw_source(TaskSourceBO raw_source) {
		this.raw_source = raw_source;
		return this;
	}

	public TaskSourceBO getPassby_source() {
		return passby_source;
	}

	public TaskBO setPassby_source(TaskSourceBO passby_source) {
		this.passby_source = passby_source;
		return this;
	}

	public VideoMixSourceBO getVideo_mix_source() {
		return video_mix_source;
	}

	public TaskBO setVideo_mix_source(VideoMixSourceBO video_mix_source) {
		this.video_mix_source = video_mix_source;
		return this;
	}

	public AudioMixSourceBO getAudio_mix_source() {
		return audio_mix_source;
	}

	public TaskBO setAudio_mix_source(AudioMixSourceBO audio_mix_source) {
		this.audio_mix_source = audio_mix_source;
		return this;
	}

	public List<PreProcessingBO> getDecode_process_array() {
		return decode_process_array;
	}

	public TaskBO setDecode_process_array(List<PreProcessingBO> decode_process_array) {
		this.decode_process_array = decode_process_array;
		return this;
	}

	public List<EncodeBO> getEncode_array() {
		return encode_array;
	}

	public TaskBO setEncode_array(List<EncodeBO> encode_array) {
		this.encode_array = encode_array;
		return this;
	}
	
}
