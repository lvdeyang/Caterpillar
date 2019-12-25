package com.sumavision.tetris.capacity.bo.response;

import java.util.List;

import com.sumavision.tetris.capacity.bo.input.InputBO;

/**
 * 获取输入返回参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午11:21:25
 */
public class GetInputsResponse {

	private String msg_id;
	
	private List<InputBO> input_array;

	public String getMsg_id() {
		return msg_id;
	}

	public GetInputsResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<InputBO> getInput_array() {
		return input_array;
	}

	public GetInputsResponse setInput_array(List<InputBO> input_array) {
		this.input_array = input_array;
		return this;
	}
	
}
