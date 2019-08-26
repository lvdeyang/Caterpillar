package com.sumavision.tetris.spring.eureka.heartbeat;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.commons.util.httprequest.HttpRequestUtil;

public class HeartbeatThread extends Thread{
	/**
	 * 获取隶属角色<br/>
	 * <b>作者:</b>lzp<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年5月29日 上午11:44:44
	 * 	status 是否发送心跳
	 *  rate 心跳频率
	 *  ip 心跳目标地址
	 *  port 心跳目标端口
	 *  keyName 心跳content字段名
	 *  keyValue 心跳content字段内容
	 */
	public Long fileModified;
	public String url;

	private Boolean status;
	private Long rate;
	private String ip;
	private String port;
	private String keyName;
	private String keyValue;
	
	@Override
	public void run() {
		while(true){
			try {
				this.startHeartBeat();
				synchronized (HeartbeatThread.class) {
					Thread.sleep(this.getRate());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void startHeartBeat() throws Exception {
		if (init() && this.getStatus()) {
			url = "http://" + this.getIp() + ":" + this.getPort()
					+ "/smartexpress-control-engine/Platform/RecHeartBeatRequest";
			JSONObject sendJson = new JSONObject();
			sendJson.put(this.getKeyName(), this.getKeyValue());
			HttpRequestUtil.httpPost(url, sendJson);
		}
		System.out.println("modified:" + fileModified + ";status:" + this.getStatus() + ";url:" + url);
	}
	
	private boolean init() throws Exception {
		File jsonFile = ResourceUtils.getFile("classpath:heartbeat.json");
		if (!checkKeys() || jsonFile.lastModified() != fileModified) {
			resetKeys();
		}

		return checkKeys();
	}

	private boolean checkKeys() {
		return !(
				this.getStatus() == null
				|| this.getRate() == null
				|| this.getIp() == null
				|| this.getPort() == null
				|| this.getKeyName() == null
				|| this.getKeyValue() == null
				);
	}

	private void resetKeys() throws Exception {
		File jsonFile = ResourceUtils.getFile("classpath:heartbeat.json");

		fileModified = jsonFile.lastModified();

		String json = FileUtils.readFileToString(jsonFile);
		JSONObject jsonObject = JSONObject.parseObject(json);

		this.setStatus(jsonObject.containsKey("status") ? jsonObject.getBoolean("status") : null);
		this.setRate(jsonObject.containsKey("rate") ? jsonObject.getLong("rate") : null);
		this.setIp(jsonObject.containsKey("ip") ? jsonObject.getString("ip") : null);
		this.setPort(jsonObject.containsKey("port") ? jsonObject.getString("port") : null);
		this.setKeyName(jsonObject.containsKey("keyName") ? jsonObject.getString("keyName") : null);
		this.setKeyValue(jsonObject.containsKey("keyValue") ? jsonObject.getString("keyValue") : null);
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}

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

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
}
