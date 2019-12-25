package com.sumavision.tetris.capacity.bo.response;

/**
 * 节目返回参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 上午11:04:34
 */
public class ProgramResponse {

	private Integer program_number;
	
	private Integer result_code;

	public Integer getProgram_number() {
		return program_number;
	}

	public ProgramResponse setProgram_number(Integer program_number) {
		this.program_number = program_number;
		return this;
	}

	public Integer getResult_code() {
		return result_code;
	}

	public ProgramResponse setResult_code(Integer result_code) {
		this.result_code = result_code;
		return this;
	}
	
}
