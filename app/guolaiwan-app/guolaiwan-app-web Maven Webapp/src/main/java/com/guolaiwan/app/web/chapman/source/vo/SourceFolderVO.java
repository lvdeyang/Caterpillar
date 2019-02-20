package com.guolaiwan.app.web.chapman.source.vo;

import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

/**
 * 文件夹
 * lvdeyang 2017年6月23日
 */
public class SourceFolderVO extends AbstractBaseVO<SourceFolderVO, SourceFolderPO>{

	//文件夹名称
	private String name;

	public String getName() {
		return name;
	}

	public SourceFolderVO setName(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public SourceFolderVO set(SourceFolderPO entity) throws Exception{
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setName(entity.getFoldername())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern));
		return this;
	}
	
}
