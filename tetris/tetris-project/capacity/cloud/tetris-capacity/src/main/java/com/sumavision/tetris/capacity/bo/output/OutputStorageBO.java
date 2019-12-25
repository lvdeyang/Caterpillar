package com.sumavision.tetris.capacity.bo.output;

/**
 * storage输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午9:00:44
 */
public class OutputStorageBO {

	private String url;
	
	private Integer max_upload_bps;
	
	private Integer can_del;

	public String getUrl() {
		return url;
	}

	public OutputStorageBO setUrl(String url) {
		this.url = url;
		return this;
	}

	public Integer getMax_upload_bps() {
		return max_upload_bps;
	}

	public OutputStorageBO setMax_upload_bps(Integer max_upload_bps) {
		this.max_upload_bps = max_upload_bps;
		return this;
	}

	public Integer getCan_del() {
		return can_del;
	}

	public OutputStorageBO setCan_del(Integer can_del) {
		this.can_del = can_del;
		return this;
	}
	
}
