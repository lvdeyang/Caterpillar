package com.sumavision.tetris.capacity.bo.output;

/**
 * aes-128加密参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2020年2月20日 下午4:05:31
 */
public class Aes128EncryptBO {

	private String IV;
	
	private Integer key_rotation_count;

	public String getIV() {
		return IV;
	}

	public Aes128EncryptBO setIV(String iV) {
		IV = iV;
		return this;
	}

	public Integer getKey_rotation_count() {
		return key_rotation_count;
	}

	public Aes128EncryptBO setKey_rotation_count(Integer key_rotation_count) {
		this.key_rotation_count = key_rotation_count;
		return this;
	}
	
}
