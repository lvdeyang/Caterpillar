package com.sumavision.tetris.capacity.bo.request;

import java.util.List;

import com.sumavision.tetris.capacity.bo.output.OutputBO;

/**
 * 创建输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 上午11:20:42
 */
public class CreateOutputsRequest {

	private String msg_id;
	
	private List<OutputBO> output_array;

	public String getMsg_id() {
		return msg_id;
	}

	public CreateOutputsRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<OutputBO> getOutput_array() {
		return output_array;
	}

	public CreateOutputsRequest setOutput_array(List<OutputBO> output_array) {
		this.output_array = output_array;
		return this;
	}
	
}
