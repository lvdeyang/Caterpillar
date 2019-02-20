package com.guolaiwan.app.web.chapman.source.picture.vo;

import com.guolaiwan.bussiness.chapman.source.po.FilePO;
import pub.caterpillar.commons.img.ImageUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class PictureFileVO extends AbstractBaseVO<PictureFileVO, FilePO>{

	//图片base64编码
	private String img;
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public PictureFileVO set(FilePO entity) throws Exception {
		this.setImg(ImageUtil.getUrlSchema(entity.getContent()));
		return this;
	}

}
