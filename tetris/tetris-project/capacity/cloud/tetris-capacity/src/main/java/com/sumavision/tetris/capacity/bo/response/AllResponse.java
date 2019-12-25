package com.sumavision.tetris.capacity.bo.response;

import java.util.List;

public class AllResponse {

	private String msg_id;
	
	private List<ResultResponse> input_array;
	
	private List<ResultResponse> task_array;
	
	private List<ResultResponse> output_array;

	public String getMsg_id() {
		return msg_id;
	}
	
	public AllResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<ResultResponse> getInput_array() {
		return input_array;
	}

	public AllResponse setInput_array(List<ResultResponse> input_array) {
		this.input_array = input_array;
		return this;
	}

	public List<ResultResponse> getTask_array() {
		return task_array;
	}

	public AllResponse setTask_array(List<ResultResponse> task_array) {
		this.task_array = task_array;
		return this;
	}

	public List<ResultResponse> getOutput_array() {
		return output_array;
	}

	public AllResponse setOutput_array(List<ResultResponse> output_array) {
		this.output_array = output_array;
		return this;
	}
	
}
