package com.sumavision.tetris.capacity.bo.response;

/**
 * 任务编码结果参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午5:58:34
 */
public class TaskEncodeResultResponse {

	private String msg_id;
	
	private String task_id;
	
	private String id;
	
	private Integer result_code;
	
	private String result_msg;

	public String getMsg_id() {
		return msg_id;
	}

	public TaskEncodeResultResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public String getTask_id() {
		return task_id;
	}

	public TaskEncodeResultResponse setTask_id(String task_id) {
		this.task_id = task_id;
		return this;
	}

	public String getId() {
		return id;
	}

	public TaskEncodeResultResponse setId(String id) {
		this.id = id;
		return this;
	}

	public Integer getResult_code() {
		return result_code;
	}

	public TaskEncodeResultResponse setResult_code(Integer result_code) {
		this.result_code = result_code;
		return this;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public TaskEncodeResultResponse setResult_msg(String result_msg) {
		this.result_msg = result_msg;
		return this;
	}
	
}
