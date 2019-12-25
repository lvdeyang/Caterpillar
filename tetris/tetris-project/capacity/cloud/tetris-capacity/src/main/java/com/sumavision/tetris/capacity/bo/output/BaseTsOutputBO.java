package com.sumavision.tetris.capacity.bo.output;

import java.util.List;

/**
 * ts输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 下午5:19:41
 */
public class BaseTsOutputBO <V extends BaseTsOutputBO>{

	private String ip;
	
	private Integer port;
	
	private String local_ip;
	
	private Integer pcr_int;
	
	private Integer pat_int;
	
	private Integer pmt_int;
	
	private Integer sdt_int;
	
	private Integer tsid_pid;
	
	private String rate_ctrl;
	
	private Integer bitrate;
	
	private String av_mode;
	
	private Integer buf_init;
	
	private String ts_mode;
	
	private Integer Integererlace_depth;
	
	private String ajust_mode;
	
	private Integer ts_send_cnt;
	
	private Integer target_cnt;
	
	private Integer pad_pid;
	
	private Integer pat_version;
	
	private Integer cut_allow;
	
	private String const_output;
	
	private String send_control;
	
	private Integer send_gap_min;
	
	private List<OutputProgramBO> program_array;

	public String getIp() {
		return ip;
	}

	public V setIp(String ip) {
		this.ip = ip;
		return (V)this;
	}

	public Integer getPort() {
		return port;
	}

	public V setPort(Integer port) {
		this.port = port;
		return (V)this;
	}

	public String getLocal_ip() {
		return local_ip;
	}

	public V setLocal_ip(String local_ip) {
		this.local_ip = local_ip;
		return (V)this;
	}

	public Integer getPcr_int() {
		return pcr_int;
	}

	public V setPcr_int(Integer pcr_int) {
		this.pcr_int = pcr_int;
		return (V)this;
	}

	public Integer getPat_int() {
		return pat_int;
	}

	public V setPat_int(Integer pat_int) {
		this.pat_int = pat_int;
		return (V)this;
	}

	public Integer getPmt_int() {
		return pmt_int;
	}

	public V setPmt_int(Integer pmt_int) {
		this.pmt_int = pmt_int;
		return (V)this;
	}

	public Integer getSdt_int() {
		return sdt_int;
	}

	public V setSdt_int(Integer sdt_int) {
		this.sdt_int = sdt_int;
		return (V)this;
	}

	public Integer getTsid_pid() {
		return tsid_pid;
	}

	public V setTsid_pid(Integer tsid_pid) {
		this.tsid_pid = tsid_pid;
		return (V)this;
	}

	public String getRate_ctrl() {
		return rate_ctrl;
	}

	public V setRate_ctrl(String rate_ctrl) {
		this.rate_ctrl = rate_ctrl;
		return (V)this;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	public V setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
		return (V)this;
	}

	public String getAv_mode() {
		return av_mode;
	}

	public V setAv_mode(String av_mode) {
		this.av_mode = av_mode;
		return (V)this;
	}

	public Integer getBuf_init() {
		return buf_init;
	}

	public V setBuf_init(Integer buf_init) {
		this.buf_init = buf_init;
		return (V)this;
	}

	public String getTs_mode() {
		return ts_mode;
	}

	public V setTs_mode(String ts_mode) {
		this.ts_mode = ts_mode;
		return (V)this;
	}

	public Integer getIntegererlace_depth() {
		return Integererlace_depth;
	}

	public V setIntegererlace_depth(Integer Integererlace_depth) {
		this.Integererlace_depth = Integererlace_depth;
		return (V)this;
	}

	public String getAjust_mode() {
		return ajust_mode;
	}

	public V setAjust_mode(String ajust_mode) {
		this.ajust_mode = ajust_mode;
		return (V)this;
	}

	public Integer getTs_send_cnt() {
		return ts_send_cnt;
	}

	public V setTs_send_cnt(Integer ts_send_cnt) {
		this.ts_send_cnt = ts_send_cnt;
		return (V)this;
	}

	public Integer getTarget_cnt() {
		return target_cnt;
	}

	public V setTarget_cnt(Integer target_cnt) {
		this.target_cnt = target_cnt;
		return (V)this;
	}

	public Integer getPad_pid() {
		return pad_pid;
	}

	public V setPad_pid(Integer pad_pid) {
		this.pad_pid = pad_pid;
		return (V)this;
	}

	public Integer getPat_version() {
		return pat_version;
	}

	public V setPat_version(Integer pat_version) {
		this.pat_version = pat_version;
		return (V)this;
	}

	public Integer getCut_allow() {
		return cut_allow;
	}

	public V setCut_allow(Integer cut_allow) {
		this.cut_allow = cut_allow;
		return (V)this;
	}

	public String getConst_output() {
		return const_output;
	}

	public V setConst_output(String const_output) {
		this.const_output = const_output;
		return (V)this;
	}

	public String getSend_control() {
		return send_control;
	}

	public V setSend_control(String send_control) {
		this.send_control = send_control;
		return (V)this;
	}

	public Integer getSend_gap_min() {
		return send_gap_min;
	}

	public V setSend_gap_min(Integer send_gap_min) {
		this.send_gap_min = send_gap_min;
		return (V)this;
	}

	public List<OutputProgramBO> getProgram_array() {
		return program_array;
	}

	public V setProgram_array(List<OutputProgramBO> program_array) {
		this.program_array = program_array;
		return (V)this;
	}
	
}
