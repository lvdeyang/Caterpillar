package com.sumavision.tetris.capacity.bo.request;

import java.util.List;

import com.sumavision.tetris.capacity.bo.task.TaskBO;

/**
 * 创建任务请求<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午3:04:11
 */
public class CreateTaskRequest {

	private String msg_id;
	
	private List<TaskBO> task_array;

	public String getMsg_id() {
		return msg_id;
	}

	public CreateTaskRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<TaskBO> getTask_array() {
		return task_array;
	}

	public CreateTaskRequest setTask_array(List<TaskBO> task_array) {
		this.task_array = task_array;
		return this;
	}
	
}
