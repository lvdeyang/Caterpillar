package com.sumavision.tetris.capacity.bo.response;

import java.util.List;

/**
 * 创建任务返回<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午3:22:28
 */
public class CreateTaskResponse {

	private String msg_id;
	
	private List<ResultResponse> task_array;

	public String getMsg_id() {
		return msg_id;
	}

	public CreateTaskResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<ResultResponse> getTask_array() {
		return task_array;
	}

	public CreateTaskResponse setTask_array(List<ResultResponse> task_array) {
		this.task_array = task_array;
		return this;
	}
	
}
