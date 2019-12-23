package com.sumavision.tetris.capacity.bo.input;

import java.util.List;

public class BackUpPassByBO {

	/** 模式 higher_first/floating/manual */
	private String mode;
	
	/** 选中索引 */
	private String select_index = "0";
	
	/** 输入id */
	private String input_id;
	
	/** 备份节目数组 */
	private List<ProgramBO> program_array;

	public String getMode() {
		return mode;
	}

	public BackUpPassByBO setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public String getSelect_index() {
		return select_index;
	}

	public BackUpPassByBO setSelect_index(String select_index) {
		this.select_index = select_index;
		return this;
	}

	public String getInput_id() {
		return input_id;
	}

	public BackUpPassByBO setInput_id(String input_id) {
		this.input_id = input_id;
		return this;
	}

	public List<ProgramBO> getProgram_array() {
		return program_array;
	}

	public BackUpPassByBO setProgram_array(List<ProgramBO> program_array) {
		this.program_array = program_array;
		return this;
	}
	
}
