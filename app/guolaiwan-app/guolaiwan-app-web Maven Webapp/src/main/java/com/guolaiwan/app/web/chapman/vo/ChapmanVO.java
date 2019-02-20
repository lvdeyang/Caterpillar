package com.guolaiwan.app.web.chapman.vo;

import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ChapmanVO extends AbstractBaseVO<ChapmanVO, ChapmanPO>{

	//商户名称
	private String chapmanname;
	
	//商户用户名
	private String username;
	
	public String getChapmanname() {
		return chapmanname;
	}

	public ChapmanVO setChapmanname(String chapmanname) {
		this.chapmanname = chapmanname;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public ChapmanVO setUsername(String username) {
		this.username = username;
		return this;
	}

	@Override
	public ChapmanVO set(ChapmanPO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setChapmanname(entity.getChapmanname())
			.setUsername(entity.getUsername());
		return this;
	}

}
