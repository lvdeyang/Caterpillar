package com.sumavision.tetris.capacity.bo.task;

import java.util.List;

/**
 * 图片osd参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月9日 下午3:36:55
 */
public class PictureOsdBO {

	private List<PictureOsdObjectBO> osds;

	public List<PictureOsdObjectBO> getOsds() {
		return osds;
	}

	public PictureOsdBO setOsds(List<PictureOsdObjectBO> osds) {
		this.osds = osds;
		return this;
	}
	
}
