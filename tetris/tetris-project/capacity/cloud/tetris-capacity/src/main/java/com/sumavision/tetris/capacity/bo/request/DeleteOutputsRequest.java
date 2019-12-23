package com.sumavision.tetris.capacity.bo.request;

import java.util.List;

/**
 * 删除输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 上午11:39:17
 */
public class DeleteOutputsRequest {

	private String msg_id;
	
	private List<IdRequest> output_array;

	public String getMsg_id() {
		return msg_id;
	}

	public DeleteOutputsRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<IdRequest> getOutput_array() {
		return output_array;
	}

	public DeleteOutputsRequest setOutput_array(List<IdRequest> output_array) {
		this.output_array = output_array;
		return this;
	}
	
}
