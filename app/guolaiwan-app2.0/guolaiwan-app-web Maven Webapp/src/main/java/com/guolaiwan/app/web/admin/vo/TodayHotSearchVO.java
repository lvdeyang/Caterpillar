package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.TodayHotSearchPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class TodayHotSearchVO extends AbstractBaseVO<TodayHotSearchVO, TodayHotSearchPO> {
	
	private String pic;
	
	private String title;

	public String getPic() {
		return pic;
	}

	public TodayHotSearchVO setPic(String pic) {
		this.pic = pic;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public TodayHotSearchVO setTitle(String title) {
		this.title = title;
		return this;
	}

	@Override
	public TodayHotSearchVO set(TodayHotSearchPO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setPic(entity.getPic())
		.setTitle(entity.getTitle());
		return this;
	}
	
}