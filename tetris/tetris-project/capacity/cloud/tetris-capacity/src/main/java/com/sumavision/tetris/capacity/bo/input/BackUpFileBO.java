package com.sumavision.tetris.capacity.bo.input;

/**
 * backup中file参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 下午2:32:36
 */
public class BackUpFileBO {

	private String url;
	
	private String select_index;
	
	public String getUrl() {
		return url;
	}

	public BackUpFileBO setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getSelect_index() {
		return select_index;
	}

	public BackUpFileBO setSelect_index(String select_index) {
		this.select_index = select_index;
		return this;
	}

}
