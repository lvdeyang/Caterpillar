package com.sumavision.tetris.capacity.bo.output;

/**
 * 输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 下午2:22:05
 */
public class OutputBO {

	private String id;
	
	private CommonTsOutputBO udp_ts;
	
	private CommonTsOutputBO rtp_ts;
	
	private CommonTsOutputBO http_ts;
	
	private CommonTsOutputBO srt_ts;
	
	private OutputHlsBO hls;
	
	private OutputDashBO dash;
	
	private OutputRtspBO rtsp;
	
	private OutputRtpEsBO rtp_es;
	
	private OutputPassbyBO udp_passby;
	
	private OutputPassbyBO rtp_ts_passby;
	
	private OutputHttpTsPassbyBO http_ts_passby;
	
	private OutputSrtTsPassbyBO srt_ts_passby;
	
	private OutputRtmpBO rtmp;
	
	private OutputRtmpBO http_flv;
	
	private OutputHlsRecordBO hls_record;

	public String getId() {
		return id;
	}

	public OutputBO setId(String id) {
		this.id = id;
		return this;
	}

	public CommonTsOutputBO getUdp_ts() {
		return udp_ts;
	}

	public OutputBO setUdp_ts(CommonTsOutputBO udp_ts) {
		this.udp_ts = udp_ts;
		return this;
	}

	public CommonTsOutputBO getRtp_ts() {
		return rtp_ts;
	}

	public OutputBO setRtp_ts(CommonTsOutputBO rtp_ts) {
		this.rtp_ts = rtp_ts;
		return this;
	}

	public CommonTsOutputBO getHttp_ts() {
		return http_ts;
	}

	public OutputBO setHttp_ts(CommonTsOutputBO http_ts) {
		this.http_ts = http_ts;
		return this;
	}

	public CommonTsOutputBO getSrt_ts() {
		return srt_ts;
	}

	public OutputBO setSrt_ts(CommonTsOutputBO srt_ts) {
		this.srt_ts = srt_ts;
		return this;
	}

	public OutputHlsBO getHls() {
		return hls;
	}

	public OutputBO setHls(OutputHlsBO hls) {
		this.hls = hls;
		return this;
	}

	public OutputDashBO getDash() {
		return dash;
	}

	public OutputBO setDash(OutputDashBO dash) {
		this.dash = dash;
		return this;
	}

	public OutputRtspBO getRtsp() {
		return rtsp;
	}

	public OutputBO setRtsp(OutputRtspBO rtsp) {
		this.rtsp = rtsp;
		return this;
	}

	public OutputRtpEsBO getRtp_es() {
		return rtp_es;
	}

	public OutputBO setRtp_es(OutputRtpEsBO rtp_es) {
		this.rtp_es = rtp_es;
		return this;
	}

	public OutputPassbyBO getUdp_passby() {
		return udp_passby;
	}

	public OutputBO setUdp_passby(OutputPassbyBO udp_passby) {
		this.udp_passby = udp_passby;
		return this;
	}

	public OutputPassbyBO getRtp_ts_passby() {
		return rtp_ts_passby;
	}

	public OutputBO setRtp_ts_passby(OutputPassbyBO rtp_ts_passby) {
		this.rtp_ts_passby = rtp_ts_passby;
		return this;
	}

	public OutputHttpTsPassbyBO getHttp_ts_passby() {
		return http_ts_passby;
	}

	public OutputBO setHttp_ts_passby(OutputHttpTsPassbyBO http_ts_passby) {
		this.http_ts_passby = http_ts_passby;
		return this;
	}

	public OutputSrtTsPassbyBO getSrt_ts_passby() {
		return srt_ts_passby;
	}

	public OutputBO setSrt_ts_passby(OutputSrtTsPassbyBO srt_ts_passby) {
		this.srt_ts_passby = srt_ts_passby;
		return this;
	}

	public OutputRtmpBO getRtmp() {
		return rtmp;
	}

	public OutputBO setRtmp(OutputRtmpBO rtmp) {
		this.rtmp = rtmp;
		return this;
	}

	public OutputRtmpBO getHttp_flv() {
		return http_flv;
	}

	public OutputBO setHttp_flv(OutputRtmpBO http_flv) {
		this.http_flv = http_flv;
		return this;
	}

	public OutputHlsRecordBO getHls_record() {
		return hls_record;
	}

	public OutputBO setHls_record(OutputHlsRecordBO hls_record) {
		this.hls_record = hls_record;
		return this;
	}
	
}
