package com.sumavision.tetris.business.yjgb.vo;

public class RecordVO {
	/** 是否收录 */
	private boolean isRecord;
	/** 输入源地址 */
	private String assetIp;
	/** 输入源端口 */
	private String assetPort;
	/** 录制结束回调地址 */
	private String recordCallback;
	
	public boolean isRecord() {
		return isRecord;
	}
	
	public void setRecord(boolean isRecord) {
		this.isRecord = isRecord;
	}

	public String getAssetIp() {
		return assetIp;
	}

	public void setAssetIp(String assetIp) {
		this.assetIp = assetIp;
	}

	public String getAssetPort() {
		return assetPort;
	}

	public void setAssetPort(String assetPort) {
		this.assetPort = assetPort;
	}

	public String getRecordCallback() {
		return recordCallback;
	}

	public void setRecordCallback(String recordCallback) {
		this.recordCallback = recordCallback;
	}
}
