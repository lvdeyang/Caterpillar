package com.sumavision.tetris.capacity.bo.input;

/**
 * udp_pcm参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 下午2:49:43
 */
public class UdpPcmBO {

	private String source_ip;
	
	private Integer source_port;
	
	private String local_ip;
	
	private Igmpv3BO igmpv3;
	
	private Integer sample_rate;
	
	private String sample_fmt;
	
	private String channel_layout;

	public String getSource_ip() {
		return source_ip;
	}

	public UdpPcmBO setSource_ip(String source_ip) {
		this.source_ip = source_ip;
		return this;
	}

	public Integer getSource_port() {
		return source_port;
	}

	public UdpPcmBO setSource_port(Integer source_port) {
		this.source_port = source_port;
		return this;
	}

	public String getLocal_ip() {
		return local_ip;
	}

	public UdpPcmBO setLocal_ip(String local_ip) {
		this.local_ip = local_ip;
		return this;
	}

	public Igmpv3BO getIgmpv3() {
		return igmpv3;
	}

	public UdpPcmBO setIgmpv3(Igmpv3BO igmpv3) {
		this.igmpv3 = igmpv3;
		return this;
	}

	public Integer getSample_rate() {
		return sample_rate;
	}

	public UdpPcmBO setSample_rate(Integer sample_rate) {
		this.sample_rate = sample_rate;
		return this;
	}

	public String getSample_fmt() {
		return sample_fmt;
	}

	public UdpPcmBO setSample_fmt(String sample_fmt) {
		this.sample_fmt = sample_fmt;
		return this;
	}

	public String getChannel_layout() {
		return channel_layout;
	}

	public UdpPcmBO setChannel_layout(String channel_layout) {
		this.channel_layout = channel_layout;
		return this;
	}
	
}
