package com.sumavision.tetris.capacity.bo.request;

import com.sumavision.tetris.capacity.bo.task.EncodeBO;

/**
 * 修改任务编码参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午5:49:06
 */
public class PutTaskEncodeRequest {

	private String msg_id;
	
	private EncodeBO encode_param;

	public String getMsg_id() {
		return msg_id;
	}

	public PutTaskEncodeRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public EncodeBO getEncode_param() {
		return encode_param;
	}

	public PutTaskEncodeRequest setEncode_param(EncodeBO encode_param) {
		this.encode_param = encode_param;
		return this;
	}
	
}
