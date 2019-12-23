package com.sumavision.tetris.capacity.bo.response;

import com.sumavision.tetris.capacity.bo.input.InputBO;
import com.sumavision.tetris.capacity.bo.input.InputBaseBO;

/**
 * 刷源返回结果<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午2:06:29
 */
public class InputResultResponse extends InputBaseBO<InputResultResponse>{

	private Integer result_code;

	public Integer getResult_code() {
		return result_code;
	}

	public InputResultResponse setResult_code(Integer result_code) {
		this.result_code = result_code;
		return this;
	}
	
}
