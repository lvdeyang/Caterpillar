package com.sumavision.tetris.capacity.bo.output;

/**
 * media输出通用参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午10:10:30
 */
public class OutputBaseMediaBO<V extends OutputBaseMediaBO> {

	private String task_id;
	
	private String encode_id;

	public String getTask_id() {
		return task_id;
	}

	public V setTask_id(String task_id) {
		this.task_id = task_id;
		return (V)this;
	}

	public String getEncode_id() {
		return encode_id;
	}

	public V setEncode_id(String encode_id) {
		this.encode_id = encode_id;
		return (V)this;
	}
	
}
