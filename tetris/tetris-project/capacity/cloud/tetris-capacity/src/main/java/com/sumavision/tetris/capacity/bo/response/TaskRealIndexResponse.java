package com.sumavision.tetris.capacity.bo.response;

/**
 * 源节目切换返回<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 上午10:16:58
 */
public class TaskRealIndexResponse extends TaskResponse<TaskRealIndexResponse>{

	private Integer real_index;

	public Integer getReal_index() {
		return real_index;
	}

	public TaskRealIndexResponse setReal_index(Integer real_index) {
		this.real_index = real_index;
		return this;
	}
	
}
