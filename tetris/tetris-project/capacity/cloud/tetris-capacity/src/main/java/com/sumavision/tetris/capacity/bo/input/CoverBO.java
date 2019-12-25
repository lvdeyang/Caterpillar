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
	
	private List<FileBO> file_array;
	
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

	public List<FileBO> getFile_array() {
		return file_array;
	}

	public CoverBO setFile_array(List<FileBO> file_array) {
		this.file_array = file_array;
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
