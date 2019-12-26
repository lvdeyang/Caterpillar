package com.sumavision.tetris.capacity.bo.input;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 输入基本参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月7日 下午6:55:51
 */
public class InputBaseBO <V extends InputBaseBO>{

	private String id;
	
	private CommonTsBO udp_ts;
	
	private CommonTsBO rtp_ts;
	
	private SourceUrlBO http_ts;
	
	private SrtTsBO srt_ts;
	
	private SourceUrlBO hls;
	
	private SourceUrlBO dash;
	
	private SourceUrlBO mss;
	
	private SourceUrlBO rtsp;
	
	private SourceUrlBO rtmp;
	
	private SourceUrlBO http_flv;
	
	private SdiBO sdi;
	
	private RtpEsBO rtp_es;
	
	private BackUpPassByBO back_up_passby;
	
	private BackUpEsAndRawBO back_up_es;
	
	private BackUpEsAndRawBO back_up_raw;
	
	private SourceUrlBO file;
	
	private CoverBO cover;
	
	private UdpPcmBO udp_pcm;
	
	private List<ProgramBO> program_array;
	
	/** 不做节目映射，由集群指定节目参数 */
	private JSONObject normal_map;
	
	/** 各种类型媒体最多一个，靠媒体类型自动映射 */
	private JSONObject media_type_once_map;

	public String getId() {
		return id;
	}

	public V setId(String id) {
		this.id = id;
		return (V)this;
	}

	public CommonTsBO getUdp_ts() {
		return udp_ts;
	}

	public V setUdp_ts(CommonTsBO udp_ts) {
		this.udp_ts = udp_ts;
		return (V)this;
	}

	public CommonTsBO getRtp_ts() {
		return rtp_ts;
	}

	public V setRtp_ts(CommonTsBO rtp_ts) {
		this.rtp_ts = rtp_ts;
		return (V)this;
	}

	public SourceUrlBO getHttp_ts() {
		return http_ts;
	}

	public V setHttp_ts(SourceUrlBO http_ts) {
		this.http_ts = http_ts;
		return (V)this;
	}

	public SrtTsBO getSrt_ts() {
		return srt_ts;
	}

	public V setSrt_ts(SrtTsBO srt_ts) {
		this.srt_ts = srt_ts;
		return (V)this;
	}

	public SourceUrlBO getHls() {
		return hls;
	}

	public V setHls(SourceUrlBO hls) {
		this.hls = hls;
		return (V)this;
	}

	public SourceUrlBO getDash() {
		return dash;
	}

	public V setDash(SourceUrlBO dash) {
		this.dash = dash;
		return (V)this;
	}

	public SourceUrlBO getMss() {
		return mss;
	}

	public V setMss(SourceUrlBO mss) {
		this.mss = mss;
		return (V)this;
	}

	public SourceUrlBO getRtsp() {
		return rtsp;
	}

	public V setRtsp(SourceUrlBO rtsp) {
		this.rtsp = rtsp;
		return (V)this;
	}

	public SourceUrlBO getRtmp() {
		return rtmp;
	}

	public V setRtmp(SourceUrlBO rtmp) {
		this.rtmp = rtmp;
		return (V)this;
	}

	public SourceUrlBO getHttp_flv() {
		return http_flv;
	}

	public V setHttp_flv(SourceUrlBO http_flv) {
		this.http_flv = http_flv;
		return (V)this;
	}

	public SdiBO getSdi() {
		return sdi;
	}

	public V setSdi(SdiBO sdi) {
		this.sdi = sdi;
		return (V)this;
	}

	public RtpEsBO getRtp_es() {
		return rtp_es;
	}

	public V setRtp_es(RtpEsBO rtp_es) {
		this.rtp_es = rtp_es;
		return (V)this;
	}

	public BackUpPassByBO getBack_up_passby() {
		return back_up_passby;
	}

	public V setBack_up_passby(BackUpPassByBO back_up_passby) {
		this.back_up_passby = back_up_passby;
		return (V)this;
	}

	public BackUpEsAndRawBO getBack_up_es() {
		return back_up_es;
	}

	public V setBack_up_es(BackUpEsAndRawBO back_up_es) {
		this.back_up_es = back_up_es;
		return (V)this;
	}

	public BackUpEsAndRawBO getBack_up_raw() {
		return back_up_raw;
	}

	public V setBack_up_raw(BackUpEsAndRawBO back_up_raw) {
		this.back_up_raw = back_up_raw;
		return (V)this;
	}

	public SourceUrlBO getFile() {
		return file;
	}

	public V setFile(SourceUrlBO file) {
		this.file = file;
		return (V)this;
	}

	public CoverBO getCover() {
		return cover;
	}

	public V setCover(CoverBO cover) {
		this.cover = cover;
		return (V)this;
	}

	public UdpPcmBO getUdp_pcm() {
		return udp_pcm;
	}

	public V setUdp_pcm(UdpPcmBO udp_pcm) {
		this.udp_pcm = udp_pcm;
		return (V)this;
	}

	public List<ProgramBO> getProgram_array() {
		return program_array;
	}

	public V setProgram_array(List<ProgramBO> program_array) {
		this.program_array = program_array;
		return (V)this;
	}

	public JSONObject getNormal_map() {
		return normal_map;
	}

	public V setNormal_map(JSONObject normal_map) {
		this.normal_map = normal_map;
		return (V)this;
	}

	public JSONObject getMedia_type_once_map() {
		return media_type_once_map;
	}

	public V setMedia_type_once_map(JSONObject media_type_once_map) {
		this.media_type_once_map = media_type_once_map;
		return (V)this;
	}
	
}
