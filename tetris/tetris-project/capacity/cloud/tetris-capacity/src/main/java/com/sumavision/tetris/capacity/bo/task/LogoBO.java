package com.sumavision.tetris.capacity.bo.task;

/**
 * 台标参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午8:45:05
 */
@Deprecated
public class LogoBO {

	private String position;
	
	private String zone;
	
	private String url;
	
	/** 内存优先还是CPU优先，内存优先标识解码后放到内存，
	 * 下次不用再解，cpu优先是每次都解码一次  0/1
	 */
	private Integer mode;

	public String getPosition() {
		return position;
	}

	public LogoBO setPosition(String position) {
		this.position = position;
		return this;
	}

	public String getZone() {
		return zone;
	}

	public LogoBO setZone(String zone) {
		this.zone = zone;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public LogoBO setUrl(String url) {
		this.url = url;
		return this;
	}

	public Integer getMode() {
		return mode;
	}

	public LogoBO setMode(Integer mode) {
		this.mode = mode;
		return this;
	}
	
}
