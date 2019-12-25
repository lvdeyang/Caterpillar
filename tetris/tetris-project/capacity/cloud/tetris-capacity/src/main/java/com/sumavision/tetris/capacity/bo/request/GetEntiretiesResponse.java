package com.sumavision.tetris.capacity.bo.request;

import java.util.List;

import com.sumavision.tetris.capacity.bo.input.InputBO;
import com.sumavision.tetris.capacity.bo.output.OutputBO;
import com.sumavision.tetris.capacity.bo.task.TaskBO;

/**
 * 所有参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 下午1:59:29
 */
public class GetEntiretiesResponse {

	private String msg_id;
	
	private List<InputBO> input_array;
	
	private List<TaskBO> task_array;
	
	private List<OutputBO> output_array;

	public String getMsg_id() {
		return msg_id;
	}

	public GetEntiretiesResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<InputBO> getInput_array() {
		return input_array;
	}

	public GetEntiretiesResponse setInput_array(List<InputBO> input_array) {
		this.input_array = input_array;
		return this;
	}

	public List<TaskBO> getTask_array() {
		return task_array;
	}

	public GetEntiretiesResponse setTask_array(List<TaskBO> task_array) {
		this.task_array = task_array;
		return this;
	}

	public List<OutputBO> getOutput_array() {
		return output_array;
	}

	public GetEntiretiesResponse setOutput_array(List<OutputBO> output_array) {
		this.output_array = output_array;
		return this;
	}
	
}
