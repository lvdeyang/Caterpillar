package com.sumavision.tetris.capacity.bo.output;

import java.util.List;

/**
 * dash输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午9:27:23
 */
public class OutputDashBO {

	private String playlist_name;
	
	private Integer playlist_seg_count;
	
	private Integer total_seg_count;
	
	private Integer max_seg_duration;
	
	private List<OutputMediaGroupBO> media_group_array;
	
	private OutputStorageBO storage;

	public String getPlaylist_name() {
		return playlist_name;
	}

	public OutputDashBO setPlaylist_name(String playlist_name) {
		this.playlist_name = playlist_name;
		return this;
	}

	public Integer getPlaylist_seg_count() {
		return playlist_seg_count;
	}

	public OutputDashBO setPlaylist_seg_count(Integer playlist_seg_count) {
		this.playlist_seg_count = playlist_seg_count;
		return this;
	}

	public Integer getTotal_seg_count() {
		return total_seg_count;
	}

	public OutputDashBO setTotal_seg_count(Integer total_seg_count) {
		this.total_seg_count = total_seg_count;
		return this;
	}

	public Integer getMax_seg_duration() {
		return max_seg_duration;
	}

	public OutputDashBO setMax_seg_duration(Integer max_seg_duration) {
		this.max_seg_duration = max_seg_duration;
		return this;
	}

	public List<OutputMediaGroupBO> getMedia_group_array() {
		return media_group_array;
	}

	public OutputDashBO setMedia_group_array(List<OutputMediaGroupBO> media_group_array) {
		this.media_group_array = media_group_array;
		return this;
	}

	public OutputStorageBO getStorage() {
		return storage;
	}

	public OutputDashBO setStorage(OutputStorageBO storage) {
		this.storage = storage;
		return this;
	}
	
}
