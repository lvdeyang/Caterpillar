package com.sumavision.tetris.capacity.bo.response;

/**
 * 修改指定参数返回<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 上午10:33:25
 */
public class PutInputResponse {

	private String msg_id;
	
	private Integer result_code;

	public String getMsg_id() {
		return msg_id;
	}

	public PutInputResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public Integer getResult_code() {
		return result_code;
	}

	public PutInputResponse setResult_code(Integer result_code) {
		this.result_code = result_code;
		return this;
	}
	
}
