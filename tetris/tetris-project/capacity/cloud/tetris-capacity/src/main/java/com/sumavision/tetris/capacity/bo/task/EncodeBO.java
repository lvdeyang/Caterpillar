package com.sumavision.tetris.capacity.bo.task;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 编码参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午9:58:08
 */
public class EncodeBO {

	/** 编码预处理 */
	private List<PreProcessingBO> process_array;
	
	private String encode_id;
	
	private H264BO h264;
	
	private H265BO hevc;
	
	private Mpeg2BO mpeg2;
	
	private Avs2BO avs2;
	
	@JSONField(name = "avs+")
	private AvsPlusBO avs_plus;
	
	private AacBO aac;
	
	private DolbyBO dolby;
	
	private G711BO g711a;
	
	private G711BO g711u;
	
	private MpegBO mp3;
	
	private MpegBO mp2;
	
	private JSONObject passby;

	public List<PreProcessingBO> getProcess_array() {
		return process_array;
	}

	public EncodeBO setProcess_array(List<PreProcessingBO> process_array) {
		this.process_array = process_array;
		return this;
	}

	public String getEncode_id() {
		return encode_id;
	}

	public EncodeBO setEncode_id(String encode_id) {
		this.encode_id = encode_id;
		return this;
	}

	public H264BO getH264() {
		return h264;
	}

	public EncodeBO setH264(H264BO h264) {
		this.h264 = h264;
		return this;
	}

	public H265BO getHevc() {
		return hevc;
	}

	public EncodeBO setHevc(H265BO hevc) {
		this.hevc = hevc;
		return this;
	}

	public Mpeg2BO getMpeg2() {
		return mpeg2;
	}

	public EncodeBO setMpeg2(Mpeg2BO mpeg2) {
		this.mpeg2 = mpeg2;
		return this;
	}

	public AvsPlusBO getAvs_plus() {
		return avs_plus;
	}

	public EncodeBO setAvs_plus(AvsPlusBO avs_plus) {
		this.avs_plus = avs_plus;
		return this;
	}

	public AacBO getAac() {
		return aac;
	}

	public EncodeBO setAac(AacBO aac) {
		this.aac = aac;
		return this;
	}

	public DolbyBO getDolby() {
		return dolby;
	}

	public EncodeBO setDolby(DolbyBO dolby) {
		this.dolby = dolby;
		return this;
	}

	public G711BO getG711a() {
		return g711a;
	}

	public EncodeBO setG711a(G711BO g711a) {
		this.g711a = g711a;
		return this;
	}

	public G711BO getG711u() {
		return g711u;
	}

	public EncodeBO setG711u(G711BO g711u) {
		this.g711u = g711u;
		return this;
	}

	public MpegBO getMp3() {
		return mp3;
	}

	public EncodeBO setMp3(MpegBO mp3) {
		this.mp3 = mp3;
		return this;
	}

	public MpegBO getMp2() {
		return mp2;
	}

	public EncodeBO setMp2(MpegBO mp2) {
		this.mp2 = mp2;
		return this;
	}

	public JSONObject getPassby() {
		return passby;
	}

	public EncodeBO setPassby(JSONObject passby) {
		this.passby = passby;
		return this;
	}

	public Avs2BO getAvs2() {
		return avs2;
	}

	public EncodeBO setAvs2(Avs2BO avs2) {
		this.avs2 = avs2;
		return this;
	}
	
}
