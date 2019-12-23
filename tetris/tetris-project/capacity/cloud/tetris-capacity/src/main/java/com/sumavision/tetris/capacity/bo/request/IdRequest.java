package com.sumavision.tetris.capacity.bo.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 输入id请求参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 上午8:59:52
 */
public class IdRequest {

	private String id;

	public String getId() {
		return id;
	}

	public IdRequest setId(String id) {
		this.id = id;
		return this;
	}
	
}
