package com.sumavision.tetris.capacity.bo.input;

import java.util.List;

/**
 * cover参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午3:03:26
 */
public class CoverBO {

	/** 模式 auto/manual */
	private String mode;
	
	/** video/audio/all/image,all表示音视频全盖，image的时候，音频盖静音 */
	private String type;
	
	private String start_time;
	
	private String end_time;
	
	private String url;
	
	private String file_start;
	
	private String file_end;
	
	private List<BackUpProgramBO> program_array;

	public String getMode() {
		return mode;
	}

	public CoverBO setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public String getType() {
		return type;
	}

	public CoverBO setType(String type) {
		this.type = type;
		return this;
	}

	public String getStart_time() {
		return start_time;
	}

	public CoverBO setStart_time(String start_time) {
		this.start_time = start_time;
		return this;
	}

	public String getEnd_time() {
		return end_time;
	}

	public CoverBO setEnd_time(String end_time) {
		this.end_time = end_time;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public CoverBO setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getFile_start() {
		return file_start;
	}

	public CoverBO setFile_start(String file_start) {
		this.file_start = file_start;
		return this;
	}

	public String getFile_end() {
		return file_end;
	}

	public CoverBO setFile_end(String file_end) {
		this.file_end = file_end;
		return this;
	}

	public List<BackUpProgramBO> getProgram_array() {
		return program_array;
	}

	public CoverBO setProgram_array(List<BackUpProgramBO> program_array) {
		this.program_array = program_array;
		return this;
	}
}
