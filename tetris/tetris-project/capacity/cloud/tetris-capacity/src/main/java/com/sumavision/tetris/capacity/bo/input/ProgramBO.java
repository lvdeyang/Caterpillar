package com.sumavision.tetris.capacity.bo.input;

import java.util.List;

/**
 * 节目参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月28日 下午3:20:54
 */
public class ProgramBO {

	/** 节目号 [1,65535] */
	private Integer program_number;
	
	/** 节目名称 */
	private String name;
	
	/** 节目提供商 */
	private String provider;
	
	/** 节目PMT和PID [0,8191]*/
	private Integer pmt_pid;
	
	private Integer pcr_pid;
	
	private List<ProgramVideoBO> video_array;
	
	private List<ProgramAudioBO> audio_array;
	
	private List<ProgramSubtitleBO> subtitle_array;

	public Integer getProgram_number() {
		return program_number;
	}

	public ProgramBO setProgram_number(Integer program_number) {
		this.program_number = program_number;
		return this;
	}

	public String getName() {
		return name;
	}

	public ProgramBO setName(String name) {
		this.name = name;
		return this;
	}

	public String getProvider() {
		return provider;
	}

	public ProgramBO setProvider(String provider) {
		this.provider = provider;
		return this;
	}

	public Integer getPmt_pid() {
		return pmt_pid;
	}

	public ProgramBO setPmt_pid(Integer pmt_pid) {
		this.pmt_pid = pmt_pid;
		return this;
	}

	public Integer getPcr_pid() {
		return pcr_pid;
	}

	public ProgramBO setPcr_pid(Integer pcr_pid) {
		this.pcr_pid = pcr_pid;
		return this;
	}

	public List<ProgramVideoBO> getVideo_array() {
		return video_array;
	}

	public ProgramBO setVideo_array(List<ProgramVideoBO> video_array) {
		this.video_array = video_array;
		return this;
	}

	public List<ProgramAudioBO> getAudio_array() {
		return audio_array;
	}

	public ProgramBO setAudio_array(List<ProgramAudioBO> audio_array) {
		this.audio_array = audio_array;
		return this;
	}

	public List<ProgramSubtitleBO> getSubtitle_array() {
		return subtitle_array;
	}

	public ProgramBO setSubtitle_array(List<ProgramSubtitleBO> subtitle_array) {
		this.subtitle_array = subtitle_array;
		return this;
	}
	
}
