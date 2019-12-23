package com.sumavision.tetris.capacity.bo.input;

/**
 * rtp_es参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 上午10:46:26
 */
public class RtpEsBO {

	/** 本地接收数据地址 0~65535 */
	private Integer local_port;
	
	/** 数据类型 video/audio */
	private String type;
	
	/** 媒体格式。如果为auto，则通过rfc3550标准自动根据payloadtype识别。
	      如果payloadtype >=96，则必须设置该字段，来告知媒体格式 */
	private String codec;

	public Integer getLocal_port() {
		return local_port;
	}

	public RtpEsBO setLocal_port(Integer local_port) {
		this.local_port = local_port;
		return this;
	}

	public String getType() {
		return type;
	}

	public RtpEsBO setType(String type) {
		this.type = type;
		return this;
	}

	public String getCodec() {
		return codec;
	}

	public RtpEsBO setCodec(String codec) {
		this.codec = codec;
		return this;
	}
	
}
