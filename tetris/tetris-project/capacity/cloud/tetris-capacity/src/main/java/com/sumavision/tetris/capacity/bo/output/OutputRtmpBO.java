package com.sumavision.tetris.capacity.bo.output;

import java.util.List;

/**
 * rtmp输出参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月4日 上午10:39:49
 */
public class OutputRtmpBO {

	private String server_url;
	
	private String pub_user;
	
	private String pub_password;
	
	private boolean vid_exist;
	
	private boolean aud_exist;
	
	private List<BaseMediaBO> media_array;

	public String getServer_url() {
		return server_url;
	}

	public OutputRtmpBO setServer_url(String server_url) {
		this.server_url = server_url;
		return this;
	}

	public String getPub_user() {
		return pub_user;
	}

	public OutputRtmpBO setPub_user(String pub_user) {
		this.pub_user = pub_user;
		return this;
	}

	public String getPub_password() {
		return pub_password;
	}

	public OutputRtmpBO setPub_password(String pub_password) {
		this.pub_password = pub_password;
		return this;
	}

	public boolean isVid_exist() {
		return vid_exist;
	}

	public OutputRtmpBO setVid_exist(boolean vid_exist) {
		this.vid_exist = vid_exist;
		return this;
	}

	public boolean isAud_exist() {
		return aud_exist;
	}

	public OutputRtmpBO setAud_exist(boolean aud_exist) {
		this.aud_exist = aud_exist;
		return this;
	}

	public List<BaseMediaBO> getMedia_array() {
		return media_array;
	}

	public OutputRtmpBO setMedia_array(List<BaseMediaBO> media_array) {
		this.media_array = media_array;
		return this;
	}
	
}
