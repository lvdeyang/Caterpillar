package com.sumavision.tetris.capacity.bo.input;

/**
 * srt_ts参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 上午9:08:57
 */
public class SrtTsBO {

	/** 源ip */
	private String source_ip;
	
	/** 源端口 */
	private Integer source_port;
	
	/** srt模式选择 caller/listener/rendezvous */
	private String mode;
	
	/** srt接收延时(ms) */
	private Integer latency;
	
	/** srt连接超时时间(ms) */
	private Integer connect_timeout;
	
	/** srt接收超时时间(ms) */
	private Integer recv_timeout;
	
	/** srt连接 可用的最大带宽(bps) */
	private Integer maxbw;
	
	/** srt接收缓冲区大小(字节) */
	private Integer recv_buffsize;
	
	/** srt加密密码 */
	private String passphrase;
	
	/** srt加密密钥长度 */
	private String key_len;

	public String getSource_ip() {
		return source_ip;
	}

	public SrtTsBO setSource_ip(String source_ip) {
		this.source_ip = source_ip;
		return this;
	}

	public Integer getSource_port() {
		return source_port;
	}

	public SrtTsBO setSource_port(Integer source_port) {
		this.source_port = source_port;
		return this;
	}

	public String getMode() {
		return mode;
	}

	public SrtTsBO setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public Integer getLatency() {
		return latency;
	}

	public SrtTsBO setLatency(Integer latency) {
		this.latency = latency;
		return this;
	}

	public Integer getConnect_timeout() {
		return connect_timeout;
	}

	public SrtTsBO setConnect_timeout(Integer connect_timeout) {
		this.connect_timeout = connect_timeout;
		return this;
	}

	public Integer getRecv_timeout() {
		return recv_timeout;
	}

	public SrtTsBO setRecv_timeout(Integer recv_timeout) {
		this.recv_timeout = recv_timeout;
		return this;
	}

	public Integer getMaxbw() {
		return maxbw;
	}

	public SrtTsBO setMaxbw(Integer maxbw) {
		this.maxbw = maxbw;
		return this;
	}

	public Integer getRecv_buffsize() {
		return recv_buffsize;
	}

	public SrtTsBO setRecv_buffsize(Integer recv_buffsize) {
		this.recv_buffsize = recv_buffsize;
		return this;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public SrtTsBO setPassphrase(String passphrase) {
		this.passphrase = passphrase;
		return this;
	}

	public String getKey_len() {
		return key_len;
	}

	public SrtTsBO setKey_len(String key_len) {
		this.key_len = key_len;
		return this;
	}
	
}
