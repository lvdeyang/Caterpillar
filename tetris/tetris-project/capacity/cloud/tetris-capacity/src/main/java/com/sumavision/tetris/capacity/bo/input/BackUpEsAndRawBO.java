package com.sumavision.tetris.capacity.bo.input;

import java.util.List;

/**
 * 备份es和raw的参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午2:58:51
 */
public class BackUpEsAndRawBO {

	/** 模式 higher_first/floating/manual */
	private String mode = "higher_first";
	
	/** 选中索引 */
	private String select_index;
	
	/** 备份节目数组 */
	private List<BackUpProgramBO> program_array;
	
	private ProgramOutputBO output_program;
	
	private BackUpFileBO file;

	public String getMode() {
		return mode;
	}

	public BackUpEsAndRawBO setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public String getSelect_index() {
		return select_index;
	}

	public BackUpEsAndRawBO setSelect_index(String select_index) {
		this.select_index = select_index;
		return this;
	}

	public List<BackUpProgramBO> getProgram_array() {
		return program_array;
	}

	public BackUpEsAndRawBO setProgram_array(List<BackUpProgramBO> program_array) {
		this.program_array = program_array;
		return this;
	}

	public ProgramOutputBO getOutput_program() {
		return output_program;
	}

	public BackUpEsAndRawBO setOutput_program(ProgramOutputBO output_program) {
		this.output_program = output_program;
		return this;
	}

	public BackUpFileBO getFile() {
		return file;
	}

	public BackUpEsAndRawBO setFile(BackUpFileBO file) {
		this.file = file;
		return this;
	}
	
}
