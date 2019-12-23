package com.sumavision.tetris.capacity.management;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 转码能力授权--video<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月4日 上午11:52:30
 */
@Entity
@Table(name = "TETRIS_CAPACITY_AUTHORIZATION")
public class CapacityAuthorizationPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	private Long capacityId;
	
	/** 授权通道数 */
	private Long authNum;
	
	/** 授权占用数 */
	private Long authUse;
	
	/** 通道输入 */
	private String channelInput;
	
	/** 通道输出 */
	private String channelOutput;
	
	public Long getCapacityId() {
		return capacityId;
	}

	public void setCapacityId(Long capacityId) {
		this.capacityId = capacityId;
	}

	public Long getAuthNum() {
		return authNum;
	}

	public void setAuthNum(Long authNum) {
		this.authNum = authNum;
	}

	public Long getAuthUse() {
		return authUse;
	}

	public void setAuthUse(Long authUse) {
		this.authUse = authUse;
	}

	@Column(columnDefinition = "longtext")
	public String getChannelInput() {
		return channelInput;
	}

	public void setChannelInput(String channelInput) {
		this.channelInput = channelInput;
	}

	@Column(columnDefinition = "longtext")
	public String getChannelOutput() {
		return channelOutput;
	}

	public void setChannelOutput(String channelOutput) {
		this.channelOutput = channelOutput;
	}

}
