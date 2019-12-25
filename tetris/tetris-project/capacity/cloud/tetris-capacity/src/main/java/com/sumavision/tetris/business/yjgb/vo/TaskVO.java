package com.sumavision.tetris.business.yjgb.vo;

import java.util.List;

public class TaskVO {
	/** 输出封装 */
	private Integer esType;
	/** 输出编码参数 */
	private CodecParamVO codecParam;
	/** 输出参数 */
	private List<OutParamVO> outParam;
	
	public Integer getEsType() {
		return esType;
	}
	
	public void setEsType(Integer esType) {
		this.esType = esType;
	}
	
	public CodecParamVO getCodecParam() {
		return codecParam;
	}
	
	public void setCodecParam(CodecParamVO codecParam) {
		this.codecParam = codecParam;
	}
	
	public List<OutParamVO> getOutParam() {
		return outParam;
	}
	
	public void setOutParam(List<OutParamVO> outParam) {
		this.outParam = outParam;
	}
}
