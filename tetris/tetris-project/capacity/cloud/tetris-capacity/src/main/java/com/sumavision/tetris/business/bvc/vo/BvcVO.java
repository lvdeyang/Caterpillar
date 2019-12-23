package com.sumavision.tetris.business.bvc.vo;

/**
 * bvc参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月17日 下午5:19:36
 */
public class BvcVO {

	private String ip;
	
	private String port;
	
	private String udp;
	
	private String bundleId;
	
	private BvcVideoParamVO video;
	
	private BvcAudioParamVO audio;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUdp() {
		return udp;
	}

	public void setUdp(String udp) {
		this.udp = udp;
	}

	public String getBundleId() {
		return bundleId;
	}

	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}

	public BvcVideoParamVO getVideo() {
		return video;
	}

	public void setVideo(BvcVideoParamVO video) {
		this.video = video;
	}

	public BvcAudioParamVO getAudio() {
		return audio;
	}

	public void setAudio(BvcAudioParamVO audio) {
		this.audio = audio;
	}
	
}
