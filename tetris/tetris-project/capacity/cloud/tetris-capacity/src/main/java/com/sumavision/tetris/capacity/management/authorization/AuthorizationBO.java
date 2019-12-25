package com.sumavision.tetris.capacity.management.authorization;

/**
 * 授权内容<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月5日 下午2:43:20
 */
public class AuthorizationBO {

	private EncapsulateBO encapsulate_auth;
	
	private TranscodeBO transcode_auth;

	public EncapsulateBO getEncapsulate_auth() {
		return encapsulate_auth;
	}

	public void setEncapsulate_auth(EncapsulateBO encapsulate_auth) {
		this.encapsulate_auth = encapsulate_auth;
	}

	public TranscodeBO getTranscode_auth() {
		return transcode_auth;
	}

	public void setTranscode_auth(TranscodeBO transcode_auth) {
		this.transcode_auth = transcode_auth;
	}
}
