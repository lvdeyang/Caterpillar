package com.sumavision.tetris.capacity.bo.output;

import java.util.List;

/**
 * hls_record输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月8日 上午10:01:21
 */
public class OutputHlsRecordBO {

	private String name;
	
	private Integer cycle_time_seconds = 0;
	
	private Integer split_folder_seconds = 365*24*3600;
	
	private List<BaseMediaBO> media_array;

	public String getName() {
		return name;
	}

	public OutputHlsRecordBO setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getCycle_time_seconds() {
		return cycle_time_seconds;
	}

	public OutputHlsRecordBO setCycle_time_seconds(Integer cycle_time_seconds) {
		this.cycle_time_seconds = cycle_time_seconds;
		return this;
	}

	public Integer getSplit_folder_seconds() {
		return split_folder_seconds;
	}

	public OutputHlsRecordBO setSplit_folder_seconds(Integer split_folder_seconds) {
		this.split_folder_seconds = split_folder_seconds;
		return this;
	}

	public List<BaseMediaBO> getMedia_array() {
		return media_array;
	}

	public OutputHlsRecordBO setMedia_array(List<BaseMediaBO> media_array) {
		this.media_array = media_array;
		return this;
	}
	
}
