package com.sumavision.tetris.capacity.bo.response;

/**
 * 任务结果返回<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午3:18:43
 */
public class ResultResponse {

	private String id;
	
	private Integer result_code;
	
	private String result_msg;

	public String getId() {
		return id;
	}

	public ResultResponse setId(String id) {
		this.id = id;
		return this;
	}

	public Integer getResult_code() {
		return result_code;
	}

	public ResultResponse setResult_code(Integer result_code) {
		this.result_code = result_code;
		return this;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public ResultResponse setResult_msg(String result_msg) {
		this.result_msg = result_msg;
		return this;
	}
	
}
