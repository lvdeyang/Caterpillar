package com.sumavision.tetris.capacity.bo.task;

import java.util.List;

/**
 * 文本osd参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月9日 下午2:28:33
 */
public class TextOsdBO {

	private List<OsdBO> osds;

	public List<OsdBO> getOsds() {
		return osds;
	}

	public TextOsdBO setOsds(List<OsdBO> osds) {
		this.osds = osds;
		return this;
	}
	
}
