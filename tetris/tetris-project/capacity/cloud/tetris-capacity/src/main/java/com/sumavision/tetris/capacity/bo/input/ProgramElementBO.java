package com.sumavision.tetris.capacity.bo.input;

import java.util.List;

/**
 * 媒体参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 上午11:21:51
 */
public class ProgramElementBO {
	
	private String type;

	/** 媒体的pid 0~8191 */
	private Integer pid;
	
	/** 节目切换数组，数组成员数量与顺序应与program_array相同 */
	private List<PidIndexBO> program_switch_array;

	public String getType() {
		return type;
	}

	public ProgramElementBO setType(String type) {
		this.type = type;
		return this;
	}

	public Integer getPid() {
		return pid;
	}

	public ProgramElementBO setPid(Integer pid) {
		this.pid = pid;
		return this;
	}

	public List<PidIndexBO> getProgram_switch_array() {
		return program_switch_array;
	}

	public ProgramElementBO setProgram_switch_array(List<PidIndexBO> program_switch_array) {
		this.program_switch_array = program_switch_array;
		return this;
	}
	
}
