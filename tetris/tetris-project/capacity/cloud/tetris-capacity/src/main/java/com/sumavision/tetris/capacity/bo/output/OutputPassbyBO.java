package com.sumavision.tetris.capacity.bo.output;

/**
 * passby输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午10:16:46
 */
public class OutputPassbyBO {

	private String ip;
	
	private Integer port;
	
	private String local_ip;
	
	private BaseMediaBO media;

	public String getIp() {
		return ip;
	}

	public OutputPassbyBO setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public Integer getPort() {
		return port;
	}

	public OutputPassbyBO setPort(Integer port) {
		this.port = port;
		return this;
	}

	public String getLocal_ip() {
		return local_ip;
	}

	public OutputPassbyBO setLocal_ip(String local_ip) {
		this.local_ip = local_ip;
		return this;
	}

	public BaseMediaBO getMedia() {
		return media;
	}

	public OutputPassbyBO setMedia(BaseMediaBO media) {
		this.media = media;
		return this;
	}
	
}
