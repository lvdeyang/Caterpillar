package com.sumavision.tetris.capacity.bo.output;

import java.util.List;

/**
 * hls输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月1日 下午5:35:18
 */
public class OutputHlsBO {

	private String playlist_name;
	
	private Integer playlist_seg_count;
	
	private Integer total_seg_count;
	
	private Integer max_seg_duration;
	
	private List<OutputMediaGroupBO> media_group_array;
	
	private OutputStorageBO storage;
	
	private OutputEncryptBO encrypt;

	public String getPlaylist_name() {
		return playlist_name;
	}

	public OutputHlsBO setPlaylist_name(String playlist_name) {
		this.playlist_name = playlist_name;
		return this;
	}

	public Integer getPlaylist_seg_count() {
		return playlist_seg_count;
	}

	public OutputHlsBO setPlaylist_seg_count(Integer playlist_seg_count) {
		this.playlist_seg_count = playlist_seg_count;
		return this;
	}

	public Integer getTotal_seg_count() {
		return total_seg_count;
	}

	public OutputHlsBO setTotal_seg_count(Integer total_seg_count) {
		this.total_seg_count = total_seg_count;
		return this;
	}

	public Integer getMax_seg_duration() {
		return max_seg_duration;
	}

	public OutputHlsBO setMax_seg_duration(Integer max_seg_duration) {
		this.max_seg_duration = max_seg_duration;
		return this;
	}

	public List<OutputMediaGroupBO> getMedia_group_array() {
		return media_group_array;
	}

	public OutputHlsBO setMedia_group_array(List<OutputMediaGroupBO> media_group_array) {
		this.media_group_array = media_group_array;
		return this;
	}

	public OutputStorageBO getStorage() {
		return storage;
	}

	public OutputHlsBO setStorage(OutputStorageBO storage) {
		this.storage = storage;
		return this;
	}

	public OutputEncryptBO getEncrypt() {
		return encrypt;
	}

	public OutputHlsBO setEncrypt(OutputEncryptBO encrypt) {
		this.encrypt = encrypt;
		return this;
	}
	
}
