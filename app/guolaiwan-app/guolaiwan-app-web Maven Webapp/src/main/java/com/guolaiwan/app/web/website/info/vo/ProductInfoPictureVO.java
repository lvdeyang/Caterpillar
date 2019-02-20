package com.guolaiwan.app.web.website.info.vo;

import com.guolaiwan.bussiness.chapman.source.dto.SourceMapDTO;
import pub.caterpillar.commons.img.ImageUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ProductInfoPictureVO extends AbstractBaseVO<ProductInfoPictureVO, SourceMapDTO> {

	//uri
	private String path;
	
	//图片数据
	private String image;
	
	public String getPath() {
		return path;
	}

	public ProductInfoPictureVO setPath(String path) {
		this.path = path;
		return this;
	}

	public String getImage() {
		return image;
	}

	public ProductInfoPictureVO setImage(String image) {
		this.image = image;
		return this;
	}

	@Override
	public ProductInfoPictureVO set(SourceMapDTO entity) throws Exception {
		this.setPath(entity.getPath())
			.setImage(entity.getImage()==null?null:ImageUtil.getUrlSchema(entity.getImage()));
		return this;
	}

}
