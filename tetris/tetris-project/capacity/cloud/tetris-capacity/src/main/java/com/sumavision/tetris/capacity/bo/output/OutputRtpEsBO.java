package com.sumavision.tetris.capacity.bo.output;

/**
 * rtp_es输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午9:59:53
 */
public class OutputRtpEsBO {

	private String dst_ip;
	
	private Integer dst_port;
	
	private String local_ip;
	
	private Integer max_bitrate;
	
	private Integer mtu;
	
	private Integer time_scale;
	
	private OutputRtpesMediaBO media;

	public String getDst_ip() {
		return dst_ip;
	}

	public OutputRtpEsBO setDst_ip(String dst_ip) {
		this.dst_ip = dst_ip;
		return this;
	}

	public Integer getDst_port() {
		return dst_port;
	}

	public OutputRtpEsBO setDst_port(Integer dst_port) {
		this.dst_port = dst_port;
		return this;
	}

	public String getLocal_ip() {
		return local_ip;
	}

	public OutputRtpEsBO setLocal_ip(String local_ip) {
		this.local_ip = local_ip;
		return this;
	}

	public Integer getMax_bitrate() {
		return max_bitrate;
	}

	public OutputRtpEsBO setMax_bitrate(Integer max_bitrate) {
		this.max_bitrate = max_bitrate;
		return this;
	}

	public Integer getMtu() {
		return mtu;
	}

	public OutputRtpEsBO setMtu(Integer mtu) {
		this.mtu = mtu;
		return this;
	}

	public Integer getTime_scale() {
		return time_scale;
	}

	public OutputRtpEsBO setTime_scale(Integer time_scale) {
		this.time_scale = time_scale;
		return this;
	}

	public OutputRtpesMediaBO getMedia() {
		return media;
	}

	public OutputRtpEsBO setMedia(OutputRtpesMediaBO media) {
		this.media = media;
		return this;
	}
	
}
