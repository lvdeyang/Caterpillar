package com.sumavision.tetris.capacity.bo.response;

import java.util.List;

import com.sumavision.tetris.capacity.bo.output.OutputBO;

/**
 * 输出返回参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 上午10:41:57
 */
public class GetOutputsResponse {

	private String msg_id;
	
	private List<OutputBO> output_array;

	public String getMsg_id() {
		return msg_id;
	}

	public GetOutputsResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<OutputBO> getOutput_array() {
		return output_array;
	}

	public GetOutputsResponse setOutput_array(List<OutputBO> output_array) {
		this.output_array = output_array;
		return this;
	}
	
}
