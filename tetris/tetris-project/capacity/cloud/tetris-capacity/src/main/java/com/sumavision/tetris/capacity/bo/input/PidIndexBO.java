package com.sumavision.tetris.capacity.bo.input;

/**
 * 节目切换数组参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午2:50:02
 */
public class PidIndexBO {

	/** 备份节目中对应媒体索引，无对应媒体用null表示 */
	private Integer pid_index;

	public Integer getPid_index() {
		return pid_index;
	}

	public PidIndexBO setPid_index(Integer pid_index) {
		this.pid_index = pid_index;
		return this;
	}
	
}
