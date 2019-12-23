package com.sumavision.tetris.capacity.bo.request;

import com.sumavision.tetris.capacity.bo.input.InputBO;

/**
 * 修改输入参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 上午10:04:50
 */
public class PutInputsRequest {

	private String msg_id;
	
	private InputBO input;

	public String getMsg_id() {
		return msg_id;
	}

	public PutInputsRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public InputBO getInput() {
		return input;
	}

	public PutInputsRequest setInput(InputBO input) {
		this.input = input;
		return this;
	}
	
}
