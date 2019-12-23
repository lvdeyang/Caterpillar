package com.sumavision.tetris.capacity.bo.task;

/**
 * avs+编码参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 下午5:06:03
 */
public class AvsPlusBO extends BaseEncodeBO<AvsPlusBO>{

	private String mode;
	
	private AvsObjectBO avs;
	
	private AvsObjectBO avs_plus;

	public String getMode() {
		return mode;
	}

	public AvsPlusBO setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public AvsObjectBO getAvs() {
		return avs;
	}

	public AvsPlusBO setAvs(AvsObjectBO avs) {
		this.avs = avs;
		return this;
	}

	public AvsObjectBO getAvs_plus() {
		return avs_plus;
	}

	public AvsPlusBO setAvs_plus(AvsObjectBO avs_plus) {
		this.avs_plus = avs_plus;
		return this;
	}
	
}
