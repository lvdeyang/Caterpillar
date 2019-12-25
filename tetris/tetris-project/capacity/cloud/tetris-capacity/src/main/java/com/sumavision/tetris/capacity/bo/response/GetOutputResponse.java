package com.sumavision.tetris.capacity.bo.response;

import com.sumavision.tetris.capacity.bo.output.OutputBO;

/**
 * 指定输出返回参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 下午1:10:42
 */
public class GetOutputResponse {

	private String msg_id;
	
	private OutputBO output;

	public String getMsg_id() {
		return msg_id;
	}

	public GetOutputResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public OutputBO getOutput() {
		return output;
	}

	public GetOutputResponse setOutput(OutputBO output) {
		this.output = output;
		return this;
	}
	
}
