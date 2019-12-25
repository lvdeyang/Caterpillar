package com.sumavision.tetris.business.yjgb.vo;

public class CodecParamVO {
	/** 视频码率 */
	private Long vbitrate;
	/** 视频编码 */
	private String vcodec;
	/** 视频分辨率 */
	private String vresolution;
	/** 视频宽高比 */
	private String vratio;
	
	/** 音频码率 */
	private Long abitrate;
	/** 音频编码 */
	private String acodec;
	/** 音频采样 */
	private Long asample;
	/** 通道数 */
	private Long achannel;
	/** 音频输出声道 */
	private String chLayout;
	
	public Long getVbitrate() {
		return vbitrate;
	}
	
	public void setVbitrate(Long vbitrate) {
		this.vbitrate = vbitrate;
	}
	
	public String getVcodec() {
		return vcodec;
	}
	
	public void setVcodec(String vcodec) {
		this.vcodec = vcodec;
	}
	
	public String getVresolution() {
		return vresolution;
	}
	
	public void setVresolution(String vresolution) {
		this.vresolution = vresolution;
	}
	
	public String getVratio() {
		return vratio;
	}
	
	public void setVratio(String vratio) {
		this.vratio = vratio;
	}
	
	public Long getAbitrate() {
		return abitrate;
	}
	
	public void setAbitrate(Long abitrate) {
		this.abitrate = abitrate;
	}
	
	public String getAcodec() {
		return acodec;
	}
	
	public void setAcodec(String acodec) {
		this.acodec = acodec;
	}
	
	public Long getAsample() {
		return asample;
	}
	
	public void setAsample(Long asample) {
		this.asample = asample;
	}
	
	public Long getAchannel() {
		return achannel;
	}

	public void setAchannel(Long achannel) {
		this.achannel = achannel;
	}

	public String getChLayout() {
		return chLayout;
	}
	
	public void setChLayout(String chLayout) {
		this.chLayout = chLayout;
	}
}
