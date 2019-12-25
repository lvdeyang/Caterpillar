package com.sumavision.tetris.capacity.management;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 能力管理<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月4日 上午11:47:22
 */
@Entity
@Table(name = "TETRIS_CAPACITY_MANAGEMENT", uniqueConstraints = {@UniqueConstraint(columnNames = {"ip", "type"})})
public class CapacityManagementPO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;

	/** 能力所在服务器ip */
	private String ip;
	
	/** http协议请求端口 */
	private Long port = 5656l;
	
	/** 能力类型 */
	private CapacityType type;
	
	/** 授权信息 */
	private String authorizationInfo;
	
	/** 封装通道数 */
	private Long encapsulate_num;
	
	/** 封装通道占用数 */
	private Long encapsulate_use;
	
	/** 转码音频通道数 */
	private Long transcode_audio_num;
	
	/** 转码音频占用数 */
	private Long transcode_audio_use;
	
	/** 转码 通道数看另一个PO */
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getPort() {
		return port;
	}

	public void setPort(Long port) {
		this.port = port;
	}

	@Enumerated(EnumType.STRING)
	public CapacityType getType() {
		return type;
	}

	public void setType(CapacityType type) {
		this.type = type;
	}

	@Column(name = "AUTHORIZATION_INFO", columnDefinition = "longtext")
	public String getAuthorizationInfo() {
		return authorizationInfo;
	}

	public void setAuthorizationInfo(String authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}

	public Long getEncapsulate_num() {
		return encapsulate_num;
	}

	public void setEncapsulate_num(Long encapsulate_num) {
		this.encapsulate_num = encapsulate_num;
	}

	public Long getEncapsulate_use() {
		return encapsulate_use;
	}

	public void setEncapsulate_use(Long encapsulate_use) {
		this.encapsulate_use = encapsulate_use;
	}

	public Long getTranscode_audio_num() {
		return transcode_audio_num;
	}

	public void setTranscode_audio_num(Long transcode_audio_num) {
		this.transcode_audio_num = transcode_audio_num;
	}

	public Long getTranscode_audio_use() {
		return transcode_audio_use;
	}

	public void setTranscode_audio_use(Long transcode_audio_use) {
		this.transcode_audio_use = transcode_audio_use;
	}
	
}
