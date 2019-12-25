package com.sumavision.tetris.capacity.bo.output;

import java.util.List;

/**
 * 输出节目参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 下午4:52:28
 */
public class OutputProgramBO {

	private Integer program_number;
	
	private String name;
	
	private String provider;
	
	private String character_set;
	
	private Integer pmt_pid;
	
	private Integer pcr_pid;
	
	private List<OutputMediaBO> media_array;

	public Integer getProgram_number() {
		return program_number;
	}

	public OutputProgramBO setProgram_number(Integer program_number) {
		this.program_number = program_number;
		return this;
	}

	public String getName() {
		return name;
	}

	public OutputProgramBO setName(String name) {
		this.name = name;
		return this;
	}

	public String getProvider() {
		return provider;
	}

	public OutputProgramBO setProvider(String provider) {
		this.provider = provider;
		return this;
	}

	public String getCharacter_set() {
		return character_set;
	}

	public OutputProgramBO setCharacter_set(String character_set) {
		this.character_set = character_set;
		return this;
	}

	public Integer getPmt_pid() {
		return pmt_pid;
	}

	public OutputProgramBO setPmt_pid(Integer pmt_pid) {
		this.pmt_pid = pmt_pid;
		return this;
	}

	public Integer getPcr_pid() {
		return pcr_pid;
	}

	public OutputProgramBO setPcr_pid(Integer pcr_pid) {
		this.pcr_pid = pcr_pid;
		return this;
	}

	public List<OutputMediaBO> getMedia_array() {
		return media_array;
	}

	public OutputProgramBO setMedia_array(List<OutputMediaBO> media_array) {
		this.media_array = media_array;
		return this;
	}
	
}
