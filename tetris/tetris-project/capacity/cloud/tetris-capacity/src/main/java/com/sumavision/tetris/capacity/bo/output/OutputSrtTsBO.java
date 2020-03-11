package com.sumavision.tetris.capacity.bo.output;

/**
 * srt_ts输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 下午5:29:43
 */
public class OutputSrtTsBO extends BaseTsOutputBO<OutputSrtTsBO>{

	private String mode;
	
	private Integer latency;
	
	private Integer connect_timeout;
	
	private Integer send_timeout;
	
	private Integer maxbw;
	
	private String passphrase;
	
	private String key_len;

	public String getMode() {
		return mode;
	}

	public OutputSrtTsBO setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public Integer getLatency() {
		return latency;
	}

	public OutputSrtTsBO setLatency(Integer latency) {
		this.latency = latency;
		return this;
	}

	public Integer getConnect_timeout() {
		return connect_timeout;
	}

	public OutputSrtTsBO setConnect_timeout(Integer connect_timeout) {
		this.connect_timeout = connect_timeout;
		return this;
	}

	public Integer getSend_timeout() {
		return send_timeout;
	}

	public OutputSrtTsBO setSend_timeout(Integer send_timeout) {
		this.send_timeout = send_timeout;
		return this;
	}

	public Integer getMaxbw() {
		return maxbw;
	}

	public OutputSrtTsBO setMaxbw(Integer maxbw) {
		this.maxbw = maxbw;
		return this;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public OutputSrtTsBO setPassphrase(String passphrase) {
		this.passphrase = passphrase;
		return this;
	}

	public String getKey_len() {
		return key_len;
	}

	public OutputSrtTsBO setKey_len(String key_len) {
		this.key_len = key_len;
		return this;
	}
	
}
