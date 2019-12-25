package com.sumavision.tetris.capacity.bo.output;

/**
 * subtitle输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午8:58:02
 */
public class OutputSubtitleBO {

	private String task_id;
	
	private String encode_id;
	
	private String codec;
	
	private String language;

	public String getTask_id() {
		return task_id;
	}

	public OutputSubtitleBO setTask_id(String task_id) {
		this.task_id = task_id;
		return this;
	}

	public String getEncode_id() {
		return encode_id;
	}

	public OutputSubtitleBO setEncode_id(String encode_id) {
		this.encode_id = encode_id;
		return this;
	}

	public String getCodec() {
		return codec;
	}

	public OutputSubtitleBO setCodec(String codec) {
		this.codec = codec;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public OutputSubtitleBO setLanguage(String language) {
		this.language = language;
		return this;
	}
	
}
