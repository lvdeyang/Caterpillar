package com.sumavision.tetris.capacity.bo.request;

/**
 * 源节目切换参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 上午10:11:46
 */
public class PutRealIndexRequest {

	private String msg_id;
	
	private Integer index;

	public String getMsg_id() {
		return msg_id;
	}

	public PutRealIndexRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public Integer getIndex() {
		return index;
	}

	public PutRealIndexRequest setIndex(Integer index) {
		this.index = index;
		return this;
	}
	
}
