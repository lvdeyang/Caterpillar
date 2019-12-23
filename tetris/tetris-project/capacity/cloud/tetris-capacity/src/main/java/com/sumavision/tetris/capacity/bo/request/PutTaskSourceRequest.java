package com.sumavision.tetris.capacity.bo.request;

import com.sumavision.tetris.capacity.bo.task.AudioMixSourceBO;
import com.sumavision.tetris.capacity.bo.task.TaskSourceBO;
import com.sumavision.tetris.capacity.bo.task.VideoMixSourceBO;

/**
 * 任务源参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 上午9:46:08
 */
public class PutTaskSourceRequest {

	private String msg_id;
	
	private TaskSourceBO es_source;
	
	private TaskSourceBO raw_source;
	
	private TaskSourceBO passby_source;
	
	private VideoMixSourceBO video_mix_source;
	
	private AudioMixSourceBO audio_mix_source;

	public String getMsg_id() {
		return msg_id;
	}

	public PutTaskSourceRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public TaskSourceBO getEs_source() {
		return es_source;
	}

	public PutTaskSourceRequest setEs_source(TaskSourceBO es_source) {
		this.es_source = es_source;
		return this;
	}

	public TaskSourceBO getRaw_source() {
		return raw_source;
	}

	public PutTaskSourceRequest setRaw_source(TaskSourceBO raw_source) {
		this.raw_source = raw_source;
		return this;
	}

	public TaskSourceBO getPassby_source() {
		return passby_source;
	}

	public PutTaskSourceRequest setPassby_source(TaskSourceBO passby_source) {
		this.passby_source = passby_source;
		return this;
	}

	public VideoMixSourceBO getVideo_mix_source() {
		return video_mix_source;
	}

	public PutTaskSourceRequest setVideo_mix_source(VideoMixSourceBO video_mix_source) {
		this.video_mix_source = video_mix_source;
		return this;
	}

	public AudioMixSourceBO getAudio_mix_source() {
		return audio_mix_source;
	}

	public PutTaskSourceRequest setAudio_mix_source(AudioMixSourceBO audio_mix_source) {
		this.audio_mix_source = audio_mix_source;
		return this;
	}
	
}
