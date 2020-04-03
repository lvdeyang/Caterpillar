package com.sumavision.tetris.capacity.bo.output;

/**
 * storage输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午9:00:44
 */
public class OutputStorageBO {

	private String url;
	
	private String dir_name;
	
	private String can_del;

	public String getUrl() {
		return url;
	}

	public OutputStorageBO setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getDir_name() {
		return dir_name;
	}

	public OutputStorageBO setDir_name(String dir_name) {
		this.dir_name = dir_name;
		return this;
	}

	public String getCan_del() {
		return can_del;
	}

	public OutputStorageBO setCan_del(String can_del) {
		this.can_del = can_del;
		return this;
	}
	
}
