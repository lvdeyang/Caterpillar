package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;


@Entity
@Table(name="t_sys_professionallive_matplay_vedio")
public class ProfessionalLiveMatPlayVedioPO extends AbstractBasePO{

	private static final long serialVersionUID = -8668971705694355738L;
	private long liveId;
	private String vedioUrl;
	
	public long getLiveId() {
		return liveId;
	}
	public void setLiveId(long liveId) {
		this.liveId = liveId;
	}
	public String getVedioUrl() {
		return vedioUrl;
	}
	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}
	
	
	
	
	
	
}
