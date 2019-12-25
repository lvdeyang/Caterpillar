package com.sumavision.tetris.capacity.bo.request;

import com.sumavision.tetris.capacity.bo.output.OutputBO;

/**
 * 修改指定输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 下午1:38:39
 */
public class PutOutputRequest {

	private String msg_id;
	
	private OutputBO output;

	public String getMsg_id() {
		return msg_id;
	}

	public PutOutputRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public OutputBO getOutput() {
		return output;
	}

	public PutOutputRequest setOutput(OutputBO output) {
		this.output = output;
		return this;
	}
	
}
