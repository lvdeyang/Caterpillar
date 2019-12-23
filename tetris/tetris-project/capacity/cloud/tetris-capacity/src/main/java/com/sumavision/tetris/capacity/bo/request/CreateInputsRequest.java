package com.sumavision.tetris.capacity.bo.request;

import java.util.List;

import com.sumavision.tetris.capacity.bo.input.InputBO;

/**
 * 创建输入请求<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午11:48:09
 */
public class CreateInputsRequest {

	private String msg_id;
	
	private List<InputBO> input_array;

	public String getMsg_id() {
		return msg_id;
	}

	public CreateInputsRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<InputBO> getInput_array() {
		return input_array;
	}

	public CreateInputsRequest setInput_array(List<InputBO> input_array) {
		this.input_array = input_array;
		return this;
	}
	
}
