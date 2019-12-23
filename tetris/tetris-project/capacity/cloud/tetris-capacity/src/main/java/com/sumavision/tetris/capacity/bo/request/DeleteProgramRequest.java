package com.sumavision.tetris.capacity.bo.request;

import java.util.List;

/**
 * 删除节目参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 上午11:31:36
 */
public class DeleteProgramRequest {

	private String msg_id;
	
	private List<ProgramRequest> program_array;

	public String getMsg_id() {
		return msg_id;
	}

	public DeleteProgramRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<ProgramRequest> getProgram_array() {
		return program_array;
	}

	public DeleteProgramRequest setProgram_array(List<ProgramRequest> program_array) {
		this.program_array = program_array;
		return this;
	}
	
}
