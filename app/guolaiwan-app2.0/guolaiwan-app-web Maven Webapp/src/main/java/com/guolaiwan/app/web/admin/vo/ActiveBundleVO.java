package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.ActiveBundlePo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ActiveBundleVO extends AbstractBaseVO<ActiveBundleVO, ActiveBundlePo> {
	
	private String title;
	
    private String pic;

	public String getTitle() {
		return title;
	}

	public ActiveBundleVO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getPic() {
		return pic;
	}

	public ActiveBundleVO setPic(String pic) {
		this.pic = pic;
		return this;
	}

	@Override
	public ActiveBundleVO set(ActiveBundlePo entity) throws Exception {
		this.setId(entity.getId())
		.setUpdateTime(entity.getUpdateTime())
		.setUuid(entity.getUuid())
		.setPic(entity.getPic())
		.setTitle(entity.getTitle());
		return this;
	}
	
}