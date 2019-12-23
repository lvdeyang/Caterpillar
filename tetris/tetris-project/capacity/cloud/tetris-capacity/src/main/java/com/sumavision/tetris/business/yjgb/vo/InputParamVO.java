package com.sumavision.tetris.business.yjgb.vo;

public class InputParamVO {

	private Long sourSample;
	
	/** 音频采样类型 8对应u8; 16对应s16 */
	private Long sourPrecision;
	
	/** 声道 1单声道mono; 2双声道stereo; */
	private Long sourChannel;

	public Long getSourSample() {
		return sourSample;
	}

	public InputParamVO setSourSample(Long sourSample) {
		this.sourSample = sourSample;
		return this;
	}

	public Long getSourPrecision() {
		return sourPrecision;
	}

	public InputParamVO setSourPrecision(Long sourPrecision) {
		this.sourPrecision = sourPrecision;
		return this;
	}

	public Long getSourChannel() {
		return sourChannel;
	}

	public InputParamVO setSourChannel(Long sourChannel) {
		this.sourChannel = sourChannel;
		return this;
	}
	
}
