package com.sumavision.tetris.capacity.bo.output;

public class CommonTsOutputBO extends BaseTsOutputBO<CommonTsOutputBO> {

	public CommonTsOutputBO setUdp_ts(){
		this.setRate_ctrl("VBR")
			.setBitrate(8000000);
		return this;
	}
	
}
