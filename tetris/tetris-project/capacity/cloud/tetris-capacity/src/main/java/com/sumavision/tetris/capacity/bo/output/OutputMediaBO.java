package com.sumavision.tetris.capacity.bo.output;

/**
 * 输出节目媒体<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 下午4:54:43
 */
public class OutputMediaBO {

	private String task_id;
	
	private String encode_id;
	
	private String type;
	
	private Integer pid;

	public String getTask_id() {
		return task_id;
	}

	public OutputMediaBO setTask_id(String task_id) {
		this.task_id = task_id;
		return this;
	}

	public String getEncode_id() {
		return encode_id;
	}

	public OutputMediaBO setEncode_id(String encode_id) {
		this.encode_id = encode_id;
		return this;
	}

	public String getType() {
		return type;
	}

	public OutputMediaBO setType(String type) {
		this.type = type;
		return this;
	}

	public Integer getPid() {
		return pid;
	}

	public OutputMediaBO setPid(Integer pid) {
		this.pid = pid;
		return this;
	}
	
}
