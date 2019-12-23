package com.sumavision.tetris.capacity.bo.request;

import java.util.List;

/**
 * 删除输入请求<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 上午9:45:50
 */
public class DeleteInputsRequest {
	
	private String msg_id;
	
	private List<IdRequest> input_array;

	public String getMsg_id() {
		return msg_id;
	}

	public DeleteInputsRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<IdRequest> getInput_array() {
		return input_array;
	}

	public DeleteInputsRequest setInput_array(List<IdRequest> input_array) {
		this.input_array = input_array;
		return this;
	}
	
}
