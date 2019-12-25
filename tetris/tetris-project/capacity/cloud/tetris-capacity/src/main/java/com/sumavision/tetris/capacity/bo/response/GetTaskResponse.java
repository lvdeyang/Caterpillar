package com.sumavision.tetris.capacity.bo.response;

import java.util.List;

import com.sumavision.tetris.capacity.bo.task.TaskBO;

/**
 * 任务返回<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午2:49:47
 */
public class GetTaskResponse {

	private String msg_id;
	
	private Integer result_code;
	
	private String result_msg;
	
	private List<TaskBO> task_array;

	public String getMsg_id() {
		return msg_id;
	}

	public GetTaskResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public Integer getResult_code() {
		return result_code;
	}

	public GetTaskResponse setResult_code(Integer result_code) {
		this.result_code = result_code;
		return this;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public GetTaskResponse setResult_msg(String result_msg) {
		this.result_msg = result_msg;
		return this;
	}

	public List<TaskBO> getTask_array() {
		return task_array;
	}

	public GetTaskResponse setTask_array(List<TaskBO> task_array) {
		this.task_array = task_array;
		return this;
	}
	
}
