package com.sumavision.tetris.capacity.bo.request;

/**
 * 修改指定节目解码配置<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月5日 下午1:22:22
 */
public class PatchDecodeRequest {

	private String msg_id;
	
	private String decode_mode;

	public String getMsg_id() {
		return msg_id;
	}

	public PatchDecodeRequest setMsg_id(String msg_id) {
		this.msg_id = msg_id;
		return this;
	}

	public String getDecode_mode() {
		return decode_mode;
	}

	public PatchDecodeRequest setDecode_mode(String decode_mode) {
		this.decode_mode = decode_mode;
		return this;
	}
	
}
