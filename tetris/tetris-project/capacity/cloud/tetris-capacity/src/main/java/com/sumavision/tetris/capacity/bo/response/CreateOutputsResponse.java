package com.sumavision.tetris.capacity.bo.response;

import java.util.List;

/**
 * 创建输出返回<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 上午11:30:05
 */
public class CreateOutputsResponse {

	private String msg_id;
	
	private List<ResultResponse> output_array;

	public String getMsg_id() {
		return msg_id;
	}

	public CreateOutputsResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<ResultResponse> getOutput_array() {
		return output_array;
	}

	public CreateOutputsResponse setOutput_array(List<ResultResponse> output_array) {
		this.output_array = output_array;
		return this;
	}
	
}
