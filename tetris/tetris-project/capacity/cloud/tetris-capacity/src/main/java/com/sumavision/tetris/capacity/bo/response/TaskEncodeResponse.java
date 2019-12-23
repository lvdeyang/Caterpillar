package com.sumavision.tetris.capacity.bo.response;

import java.util.List;

/**
 * 任务编码返回参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午4:21:03
 */
public class TaskEncodeResponse {

	private String msg_id;
	
	private String task_id;
	
	private List<ResultResponse> encode_array;

	public String getMsg_id() {
		return msg_id;
	}

	public TaskEncodeResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public String getTask_id() {
		return task_id;
	}

	public TaskEncodeResponse setTask_id(String task_id) {
		this.task_id = task_id;
		return this;
	}

	public List<ResultResponse> getEncode_array() {
		return encode_array;
	}

	public TaskEncodeResponse setEncode_array(List<ResultResponse> encode_array) {
		this.encode_array = encode_array;
		return this;
	}
	
}
