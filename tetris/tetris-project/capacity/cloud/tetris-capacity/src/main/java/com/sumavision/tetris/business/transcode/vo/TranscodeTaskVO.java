package com.sumavision.tetris.business.transcode.vo;

import java.util.List;

import com.sumavision.tetris.capacity.bo.input.InputBO;
import com.sumavision.tetris.capacity.bo.output.OutputBO;
import com.sumavision.tetris.capacity.bo.task.TaskBO;

/**
 * 转码任务数据<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月12日 上午11:37:50
 */
public class TranscodeTaskVO {

	private String task_id;
	
	private String task_name;
	
	private String task_type;
	
	private String device_ip;
	
	private List<InputBO> input_array;
	
	private List<TaskBO> task_array;
	
	private List<OutputBO> output_array;

	public String getTask_id() {
		return task_id;
	}

	public TranscodeTaskVO setTask_id(String task_id) {
		this.task_id = task_id;
		return this;
	}

	public String getTask_name() {
		return task_name;
	}

	public TranscodeTaskVO setTask_name(String task_name) {
		this.task_name = task_name;
		return this;
	}

	public String getTask_type() {
		return task_type;
	}

	public TranscodeTaskVO setTask_type(String task_type) {
		this.task_type = task_type;
		return this;
	}

	public String getDevice_ip() {
		return device_ip;
	}

	public TranscodeTaskVO setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
		return this;
	}

	public List<InputBO> getInput_array() {
		return input_array;
	}

	public TranscodeTaskVO setInput_array(List<InputBO> input_array) {
		this.input_array = input_array;
		return this;
	}

	public List<TaskBO> getTask_array() {
		return task_array;
	}

	public TranscodeTaskVO setTask_array(List<TaskBO> task_array) {
		this.task_array = task_array;
		return this;
	}

	public List<OutputBO> getOutput_array() {
		return output_array;
	}

	public TranscodeTaskVO setOutput_array(List<OutputBO> output_array) {
		this.output_array = output_array;
		return this;
	}
	
}
