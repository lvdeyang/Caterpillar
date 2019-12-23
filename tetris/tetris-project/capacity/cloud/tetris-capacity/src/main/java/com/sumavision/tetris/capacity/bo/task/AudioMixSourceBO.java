package com.sumavision.tetris.capacity.bo.task;

import java.util.List;

/**
 * 混音参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午5:11:15
 */
public class AudioMixSourceBO {

	/** 混音增益。100为保持不变，0为设置静音，按照百分比进行增益 */
	private Integer gain;
	
	private List<TaskSourceBO> source_array;

	public Integer getGain() {
		return gain;
	}

	public AudioMixSourceBO setGain(Integer gain) {
		this.gain = gain;
		return this;
	}

	public List<TaskSourceBO> getSource_array() {
		return source_array;
	}

	public AudioMixSourceBO setSource_array(List<TaskSourceBO> source_array) {
		this.source_array = source_array;
		return this;
	}
	
}
