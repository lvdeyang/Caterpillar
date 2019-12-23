package com.sumavision.tetris.capacity.bo.response;

import java.util.List;

/**
 * 创建节目返回参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 上午11:07:11
 */
public class CreateProgramResponse {

	private String msg_id;
	
	private List<ProgramResponse> program_array;

	public String getMsg_id() {
		return msg_id;
	}

	public CreateProgramResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<ProgramResponse> getProgram_array() {
		return program_array;
	}

	public CreateProgramResponse setProgram_array(List<ProgramResponse> program_array) {
		this.program_array = program_array;
		return this;
	}
	
}
