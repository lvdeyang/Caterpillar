package com.sumavision.tetris.capacity.bo.output;

import java.util.List;

/**
 * hls输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 下午5:35:18
 */
public class OutputHlsBO {

	private Integer group_count = 3;
	
	private Integer group_file_count = 3;
	
	private Integer chunk_span = 10;
	
	private String mode;
	
	private String enc_mode;
	
	private String IV_mode;
	
	private String drm_provider;
	
	private String IV_set;
	
	private Integer key_rotation_count;
	
	private String very_resource_id;
	
	private String key_server_addr;
	
	private String server_vod_name;
	
	private boolean subdir_flag;
	
	private String packet_type;
	
	private String primary_m3u8;
	
	private List<OutputVideoBO> video_array;
	
	private List<OutputAudioBO> audio_array;
	
	private List<OutputSubtitleBO> subtitle_array;
	
	private List<OutputMediaGroupBO> media_group;
	
	private List<OutputStorageBO> storage;

	public Integer getGroup_count() {
		return group_count;
	}

	public OutputHlsBO setGroup_count(Integer group_count) {
		this.group_count = group_count;
		return this;
	}

	public Integer getGroup_file_count() {
		return group_file_count;
	}

	public OutputHlsBO setGroup_file_count(Integer group_file_count) {
		this.group_file_count = group_file_count;
		return this;
	}

	public Integer getChunk_span() {
		return chunk_span;
	}

	public OutputHlsBO setChunk_span(Integer chunk_span) {
		this.chunk_span = chunk_span;
		return this;
	}

	public String getMode() {
		return mode;
	}

	public OutputHlsBO setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public String getEnc_mode() {
		return enc_mode;
	}

	public OutputHlsBO setEnc_mode(String enc_mode) {
		this.enc_mode = enc_mode;
		return this;
	}

	public String getIV_mode() {
		return IV_mode;
	}

	public OutputHlsBO setIV_mode(String iV_mode) {
		IV_mode = iV_mode;
		return this;
	}

	public String getDrm_provider() {
		return drm_provider;
	}

	public OutputHlsBO setDrm_provider(String drm_provider) {
		this.drm_provider = drm_provider;
		return this;
	}

	public String getIV_set() {
		return IV_set;
	}

	public OutputHlsBO setIV_set(String iV_set) {
		IV_set = iV_set;
		return this;
	}

	public Integer getKey_rotation_count() {
		return key_rotation_count;
	}

	public OutputHlsBO setKey_rotation_count(Integer key_rotation_count) {
		this.key_rotation_count = key_rotation_count;
		return this;
	}

	public String getVery_resource_id() {
		return very_resource_id;
	}

	public OutputHlsBO setVery_resource_id(String very_resource_id) {
		this.very_resource_id = very_resource_id;
		return this;
	}

	public String getKey_server_addr() {
		return key_server_addr;
	}

	public OutputHlsBO setKey_server_addr(String key_server_addr) {
		this.key_server_addr = key_server_addr;
		return this;
	}

	public String getServer_vod_name() {
		return server_vod_name;
	}

	public OutputHlsBO setServer_vod_name(String server_vod_name) {
		this.server_vod_name = server_vod_name;
		return this;
	}

	public boolean isSubdir_flag() {
		return subdir_flag;
	}

	public OutputHlsBO setSubdir_flag(boolean subdir_flag) {
		this.subdir_flag = subdir_flag;
		return this;
	}

	public String getPacket_type() {
		return packet_type;
	}

	public OutputHlsBO setPacket_type(String packet_type) {
		this.packet_type = packet_type;
		return this;
	}

	public String getPrimary_m3u8() {
		return primary_m3u8;
	}

	public OutputHlsBO setPrimary_m3u8(String primary_m3u8) {
		this.primary_m3u8 = primary_m3u8;
		return this;
	}

	public List<OutputVideoBO> getVideo_array() {
		return video_array;
	}

	public OutputHlsBO setVideo_array(List<OutputVideoBO> video_array) {
		this.video_array = video_array;
		return this;
	}

	public List<OutputAudioBO> getAudio_array() {
		return audio_array;
	}

	public OutputHlsBO setAudio_array(List<OutputAudioBO> audio_array) {
		this.audio_array = audio_array;
		return this;
	}

	public List<OutputSubtitleBO> getSubtitle_array() {
		return subtitle_array;
	}

	public OutputHlsBO setSubtitle_array(List<OutputSubtitleBO> subtitle_array) {
		this.subtitle_array = subtitle_array;
		return this;
	}

	public List<OutputMediaGroupBO> getMedia_group() {
		return media_group;
	}

	public OutputHlsBO setMedia_group(List<OutputMediaGroupBO> media_group) {
		this.media_group = media_group;
		return this;
	}

	public List<OutputStorageBO> getStorage() {
		return storage;
	}

	public OutputHlsBO setStorage(List<OutputStorageBO> storage) {
		this.storage = storage;
		return this;
	}
	
}
