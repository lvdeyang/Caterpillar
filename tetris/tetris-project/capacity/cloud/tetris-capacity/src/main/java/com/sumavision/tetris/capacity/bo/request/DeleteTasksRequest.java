package com.sumavision.tetris.capacity.bo.request;

import java.util.List;

/**
 * 删除任务请求<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午3:52:01
 */
public class DeleteTasksRequest {

	private String msg_id;
	
	private List<IdRequest> task_array;

	public String getMsg_id() {
		return msg_id;
	}

	public DeleteTasksRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<IdRequest> getTask_array() {
		return task_array;
	}

	public DeleteTasksRequest setTask_array(List<IdRequest> task_array) {
		this.task_array = task_array;
		return this;
	}
	
}
