package com.sumavision.tetris.capacity.bo.request;

import java.util.List;

import com.sumavision.tetris.capacity.bo.input.ProgramBO;

/**
 * 创建节目参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 上午11:00:23
 */
public class CreateProgramsRequest {

	private String msg_id;
	
	private List<ProgramBO> program_array;

	public String getMsg_id() {
		return msg_id;
	}

	public CreateProgramsRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<ProgramBO> getProgram_array() {
		return program_array;
	}

	public CreateProgramsRequest setProgram_array(List<ProgramBO> program_array) {
		this.program_array = program_array;
		return this;
	}
	
}
