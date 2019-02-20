package com.guolaiwan.app.web.chapman.source.picture.vo;

import com.guolaiwan.bussiness.chapman.source.po.SourcePO;
import pub.caterpillar.commons.img.ImageUtil;
import pub.caterpillar.commons.util.binary.ByteUtil;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

/**
 * 图片
 * lvdeyang 2017年6月23日
 */
public class PictureVO extends AbstractBaseVO<PictureVO, SourcePO>{

	//图片名称
	private String name;
		
	//图片路径
	private String path;
	
	//图片大小
	private String size;

	public String getName() {
		return name;
	}

	public PictureVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getPath() {
		return path;
	}

	public PictureVO setPath(String path) {
		this.path = path;
		return this;
	}
	
	public String getSize() {
		return size;
	}

	public PictureVO setSize(String size) {
		this.size = size;
		return this;
	}

	@Override
	public PictureVO set(SourcePO entity) throws Exception{
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setName(entity.getIntroduction())
			.setPath(entity.getPath())
			.setSize(entity.getSize())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern));
		return this;
	}
		
}
