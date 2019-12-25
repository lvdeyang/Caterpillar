package com.sumavision.tetris.capacity.bo.response;

/**
 * 基础任务返回<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 上午9:22:33
 */
public class TaskResponse <V extends TaskResponse>{

	private String msg_id;
	
	private String task_id;
	
	private Integer result_code;
	
	private String result_msg;

	public String getMsg_id() {
		return msg_id;
	}

	public V setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return (V)this;
	}

	public String getTask_id() {
		return task_id;
	}

	public V setTask_id(String task_id) {
		this.task_id = task_id;
		return (V)this;
	}

	public Integer getResult_code() {
		return result_code;
	}

	public V setResult_code(Integer result_code) {
		this.result_code = result_code;
		return (V)this;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public V setResult_msg(String result_msg) {
		this.result_msg = result_msg;
		return (V)this;
	}
	
}
