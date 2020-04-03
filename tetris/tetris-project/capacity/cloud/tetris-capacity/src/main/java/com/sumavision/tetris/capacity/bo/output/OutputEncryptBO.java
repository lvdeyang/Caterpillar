package com.sumavision.tetris.capacity.bo.output;

import com.alibaba.fastjson.JSONObject;

/**
 * 切片加密参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2020年2月20日 下午4:03:35
 */
public class OutputEncryptBO {

	private Aes128EncryptBO aes_128_encrypt;
	
	private JSONObject drm_self_produce;
	
	private DrmVerimatrixBO drm_verimatrix;

	public Aes128EncryptBO getAes_128_encrypt() {
		return aes_128_encrypt;
	}

	public OutputEncryptBO setAes_128_encrypt(Aes128EncryptBO aes_128_encrypt) {
		this.aes_128_encrypt = aes_128_encrypt;
		return this;
	}

	public JSONObject getDrm_self_produce() {
		return drm_self_produce;
	}

	public OutputEncryptBO setDrm_self_produce(JSONObject drm_self_produce) {
		this.drm_self_produce = drm_self_produce;
		return this;
	}

	public DrmVerimatrixBO getDrm_verimatrix() {
		return drm_verimatrix;
	}

	public OutputEncryptBO setDrm_verimatrix(DrmVerimatrixBO drm_verimatrix) {
		this.drm_verimatrix = drm_verimatrix;
		return this;
	}
	
}
