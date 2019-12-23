package com.sumavision.tetris.capacity.bo.output;

/**
 * audio输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午8:52:06
 */
public class OutputAudioBO {

	private String task_id;
	
	private String encode_id;
	
	private String codec;
	
	private Integer bitrate;

	public String getTask_id() {
		return task_id;
	}

	public OutputAudioBO setTask_id(String task_id) {
		this.task_id = task_id;
		return this;
	}

	public String getEncode_id() {
		return encode_id;
	}

	public OutputAudioBO setEncode_id(String encode_id) {
		this.encode_id = encode_id;
		return this;
	}

	public String getCodec() {
		return codec;
	}

	public OutputAudioBO setCodec(String codec) {
		this.codec = codec;
		return this;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	public OutputAudioBO setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
		return this;
	}
	
}
