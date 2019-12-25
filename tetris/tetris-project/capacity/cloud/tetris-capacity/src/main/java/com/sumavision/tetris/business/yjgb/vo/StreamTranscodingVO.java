package com.sumavision.tetris.business.yjgb.vo;

public class StreamTranscodingVO {
	/** 是否转码 */
	private boolean isTranscoding;
	/** 是否添加收录输出 */
	private boolean needRecordOutput;
	/** 转码源地址 */
	private String assetUrl;
	/** 媒体类型 */
	private String mediaType;
	/** 节目号 */
	private Integer progNum;
	/** 音频源pcm 0-udp_ts;1-udp_pcm;3-rtp_es */
	private Integer bePCM;
	/** 任务信息 */
	private TaskVO taskVO;
	/** 输入参数 */
	private InputParamVO inputParam;
	
	public boolean isTranscoding() {
		return isTranscoding;
	}
	
	public void setTranscoding(boolean isTranscoding) {
		this.isTranscoding = isTranscoding;
	}
	
	public boolean isNeedRecordOutput() {
		return needRecordOutput;
	}

	public void setNeedRecordOutput(boolean needRecordOutput) {
		this.needRecordOutput = needRecordOutput;
	}

	public String getAssetUrl() {
		return assetUrl;
	}

	public void setAssetUrl(String assetUrl) {
		this.assetUrl = assetUrl;
	}

	public Integer getBePCM() {
		return bePCM;
	}

	public void setBePCM(Integer bePCM) {
		this.bePCM = bePCM;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public Integer getProgNum() {
		return progNum;
	}

	public void setProgNum(Integer progNum) {
		this.progNum = progNum;
	}

	public TaskVO getTaskVO() {
		return taskVO;
	}

	public void setTaskVO(TaskVO taskVO) {
		this.taskVO = taskVO;
	}

	public InputParamVO getInputParam() {
		return inputParam;
	}

	public void setInputParam(InputParamVO inputParam) {
		this.inputParam = inputParam;
	}
}
