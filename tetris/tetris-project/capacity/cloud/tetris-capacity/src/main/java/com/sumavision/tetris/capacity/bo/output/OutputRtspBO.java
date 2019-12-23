package com.sumavision.tetris.capacity.bo.output;

import java.util.List;

/**
 * rtsp输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午9:31:32
 */
public class OutputRtspBO {

	/** live/vod */
	private String type;
	
	private String sdp_name;
	
	private String localIP;
	
	private String streamserverIP;
	
	private Integer streamserverPORT;
	
	private Integer mtu;
	
	private String av_sync;
	
	private List<OutputMediaBO> media_array;

	public String getType() {
		return type;
	}

	public OutputRtspBO setType(String type) {
		this.type = type;
		return this;
	}

	public String getSdp_name() {
		return sdp_name;
	}

	public OutputRtspBO setSdp_name(String sdp_name) {
		this.sdp_name = sdp_name;
		return this;
	}

	public String getLocalIP() {
		return localIP;
	}

	public OutputRtspBO setLocalIP(String localIP) {
		this.localIP = localIP;
		return this;
	}

	public String getStreamserverIP() {
		return streamserverIP;
	}

	public OutputRtspBO setStreamserverIP(String streamserverIP) {
		this.streamserverIP = streamserverIP;
		return this;
	}

	public Integer getStreamserverPORT() {
		return streamserverPORT;
	}

	public OutputRtspBO setStreamserverPORT(Integer streamserverPORT) {
		this.streamserverPORT = streamserverPORT;
		return this;
	}

	public Integer getMtu() {
		return mtu;
	}

	public OutputRtspBO setMtu(Integer mtu) {
		this.mtu = mtu;
		return this;
	}

	public String getAv_sync() {
		return av_sync;
	}

	public OutputRtspBO setAv_sync(String av_sync) {
		this.av_sync = av_sync;
		return this;
	}

	public List<OutputMediaBO> getMedia_array() {
		return media_array;
	}

	public OutputRtspBO setMedia_array(List<OutputMediaBO> media_array) {
		this.media_array = media_array;
		return this;
	}
	
}
