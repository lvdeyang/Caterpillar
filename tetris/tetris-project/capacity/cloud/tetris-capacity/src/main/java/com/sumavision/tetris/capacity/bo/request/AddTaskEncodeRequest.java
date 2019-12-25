package com.sumavision.tetris.capacity.bo.request;

import java.util.List;

import com.sumavision.tetris.capacity.bo.task.EncodeBO;

/**
 * 添加任务编码参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午4:04:23
 */
public class AddTaskEncodeRequest {

	private String msg_id;
	
	private List<EncodeBO> encode_array;

	public String getMsg_id() {
		return msg_id;
	}

	public AddTaskEncodeRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public List<EncodeBO> getEncode_array() {
		return encode_array;
	}

	public AddTaskEncodeRequest setEncode_array(List<EncodeBO> encode_array) {
		this.encode_array = encode_array;
		return this;
	}
	
}
