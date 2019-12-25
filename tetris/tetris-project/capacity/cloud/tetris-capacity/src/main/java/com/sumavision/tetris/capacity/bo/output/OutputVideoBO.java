package com.sumavision.tetris.capacity.bo.output;

/**
 * video输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午8:46:30
 */
public class OutputVideoBO {

	private String task_id;
	
	private String encode_id;
	
	private String codec;
	
	private String resolution;
	
	private Integer bitrate;

	public String getTask_id() {
		return task_id;
	}

	public OutputVideoBO setTask_id(String task_id) {
		this.task_id = task_id;
		return this;
	}

	public String getEncode_id() {
		return encode_id;
	}

	public OutputVideoBO setEncode_id(String encode_id) {
		this.encode_id = encode_id;
		return this;
	}

	public String getCodec() {
		return codec;
	}

	public OutputVideoBO setCodec(String codec) {
		this.codec = codec;
		return this;
	}

	public String getResolution() {
		return resolution;
	}

	public OutputVideoBO setResolution(String resolution) {
		this.resolution = resolution;
		return this;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	public OutputVideoBO setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
		return this;
	}
	
}
