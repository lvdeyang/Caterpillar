package com.guolaiwan.app.sec.vo;

import java.util.ArrayList;
import java.util.List;

public class SecUserVo {
	private String name;
	private String comName;
	private String workstatus;
	private List<SecUserPointVo> secUserPointVos=new ArrayList<SecUserPointVo>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getWorkstatus() {
		return workstatus;
	}
	public void setWorkstatus(String workstatus) {
		this.workstatus = workstatus;
	}
	public List<SecUserPointVo> getSecUserPointVos() {
		return secUserPointVos;
	}
	public void setSecUserPointVos(List<SecUserPointVo> secUserPointVos) {
		this.secUserPointVos = secUserPointVos;
	}
	

}
