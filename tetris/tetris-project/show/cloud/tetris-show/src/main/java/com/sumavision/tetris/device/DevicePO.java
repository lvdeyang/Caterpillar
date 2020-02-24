package com.sumavision.tetris.device;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.sumavision.tetris.orm.po.AbstractBasePO;


@Entity
@Table(name="TETRIS_DEVICE")
public class DevicePO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String ip;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	
}
