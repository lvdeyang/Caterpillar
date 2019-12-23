package com.sumavision.tetris.capacity.bo.task;

/**
 * 任务源通用参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午4:28:48
 */
public class TaskSourceBO {

	/** 输入id */
	private String input_id;
	
	/** 节目号 */
	private Integer program_number;
	
	/** 媒体pid */
	private Integer element_pid;

	public String getInput_id() {
		return input_id;
	}

	public TaskSourceBO setInput_id(String input_id) {
		this.input_id = input_id;
		return this;
	}

	public Integer getProgram_number() {
		return program_number;
	}

	public TaskSourceBO setProgram_number(Integer program_number) {
		this.program_number = program_number;
		return this;
	}

	public Integer getElement_pid() {
		return element_pid;
	}

	public TaskSourceBO setElement_pid(Integer element_pid) {
		this.element_pid = element_pid;
		return this;
	}
}
