package com.sumavision.tetris.capacity.bo.response;

import java.util.List;

/**
 * 创建输入返回<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 下午2:23:28
 */
public class CreateInputsResponse {

	private String msg_id;
	
	private List<ResultResponse> input_array;

	public String getMsg_id() {
		return msg_id;
	}

	public CreateInputsResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<ResultResponse> getInput_array() {
		return input_array;
	}

	public CreateInputsResponse setInput_array(List<ResultResponse> input_array) {
		this.input_array = input_array;
		return this;
	}
	
}
