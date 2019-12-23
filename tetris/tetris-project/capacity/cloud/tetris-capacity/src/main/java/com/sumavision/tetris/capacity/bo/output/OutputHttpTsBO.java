package com.sumavision.tetris.capacity.bo.output;

/**
 * http_ts输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 下午5:26:02
 */
public class OutputHttpTsBO extends BaseTsOutputBO<OutputHttpTsBO>{

	private String dst_type;
	
	private String name;

	public String getDst_type() {
		return dst_type;
	}

	public OutputHttpTsBO setDst_type(String dst_type) {
		this.dst_type = dst_type;
		return this;
	}

	public String getName() {
		return name;
	}

	public OutputHttpTsBO setName(String name) {
		this.name = name;
		return this;
	}
	
}
