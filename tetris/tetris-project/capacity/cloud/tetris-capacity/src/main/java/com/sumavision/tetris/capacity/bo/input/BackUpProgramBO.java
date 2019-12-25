package com.sumavision.tetris.capacity.bo.input;

import java.util.List;

/**
 * 备份节目参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 上午11:13:32
 */
public class BackUpProgramBO {

	/** 输入id */
	private String input_id;
	
	/** 节目号 */
	private Integer program_number;
	
	/** 媒体数组 */
	private List<ProgramElementBO> element_array;

	public String getInput_id() {
		return input_id;
	}

	public BackUpProgramBO setInput_id(String input_id) {
		this.input_id = input_id;
		return this;
	}

	public Integer getProgram_number() {
		return program_number;
	}

	public BackUpProgramBO setProgram_number(Integer program_number) {
		this.program_number = program_number;
		return this;
	}

	public List<ProgramElementBO> getElement_array() {
		return element_array;
	}

	public BackUpProgramBO setElement_array(List<ProgramElementBO> element_array) {
		this.element_array = element_array;
		return this;
	}

}
