package com.sumavision.tetris.capacity.bo.request;

/**
 * 结果返参<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 下午1:46:31
 */
public class ResultCodeResponse {

	private String msg_id;
	
	private Integer result_code;

	public String getMsg_id() {
		return msg_id;
	}

	public ResultCodeResponse setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public Integer getResult_code() {
		return result_code;
	}

	public ResultCodeResponse setResult_code(Integer result_code) {
		this.result_code = result_code;
		return this;
	}
	
}
