package com.sumavision.tetris.capacity.bo.output;

/**
 * http_ts_passby输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午10:23:18
 */
public class OutputHttpTsPassbyBO extends OutputBaseMediaBO<OutputHttpTsPassbyBO>{

	/** local/remote */
	private String dst_type;
	
	private String name;

	public String getDst_type() {
		return dst_type;
	}

	public OutputHttpTsPassbyBO setDst_type(String dst_type) {
		this.dst_type = dst_type;
		return this;
	}

	public String getName() {
		return name;
	}

	public OutputHttpTsPassbyBO setName(String name) {
		this.name = name;
		return this;
	}
	
}
